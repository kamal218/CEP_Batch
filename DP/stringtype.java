public class stringtype {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // ALL PALINDROME
        // allPalinndrome("aabbaa");
        // allPalinndromeCount("aabbaa");
        countPalindromeSubseq("aab");
    }

    public static boolean[][] allPalinndrome(String str) {
        int len = str.length();
        boolean[][] dp = new boolean[len][len];

        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {// 1 length
                    dp[si][ei] = true;
                } else if (gap == 1) {// 2 length
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = true;
                    }
                } else {
                    if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1]) {
                        dp[si][ei] = true;
                    }
                }
                si++;
                ei++;
            }
        }

        for (boolean[] ar : dp) {
            for (boolean ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return dp;
    }

    public static void allPalinndromeCount(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];
        boolean[][] ispalin = allPalinndrome(str);

        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {// 1 length
                    dp[si][ei] = 1;
                } else if (gap == 1) {// 2 length
                    dp[si][ei] = 2;
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei]++;
                    }
                } else {
                    dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] - dp[si + 1][ei - 1];
                    if (str.charAt(si) == str.charAt(ei) && ispalin[si + 1][ei - 1]) {
                        dp[si][ei]++;
                    }
                }
                si++;
                ei++;
            }
        }
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }

    }

    public String allPalinndromeLongest(String str) {
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
        int s = 0;
        int e = 0;
        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {// 1 length
                    dp[si][ei] = true;
                } else if (gap == 1) {// 2 length
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = true;
                    }
                } else {
                    if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1]) {
                        dp[si][ei] = true;
                    }
                }
                if (dp[si][ei]) {
                    s = si;
                    e = ei;
                }
                si++;
                ei++;
            }

        }
        return str.substring(s, e + 1);
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        int st = 0;
        int end = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int left = ((i + 1) / 2) - 1;
            int right = (i % 2 == 0) ? left + 2 : left + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (end - st + 1 < right - left - 1) {
                st = left + 1;
                end = right - 1;
            }
        }
        return s.substring(st, end + 1);
    }

    public int countPalindrome(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int left = ((i + 1) / 2) - 1;
            int right = (i % 2 == 0) ? left + 2 : left + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
            if (i % 2 == 0) {// odd middle character
                ans++;
            }
        }
        return ans;
    }

    // PALINDROMIC SUBSEQ

    public static int countPalindromeSubseq(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];

        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {// 1 length
                    dp[si][ei] = 1;
                } else if (gap == 1) {// 2 length
                    dp[si][ei] = 2;
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei]++;
                    }
                } else {
                    dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] - dp[si + 1][ei - 1];
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] += dp[si + 1][ei - 1] + 1;
                    }
                }
                si++;
                ei++;
            }
        }
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return dp[0][len - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else {
                    if (s.charAt(si) == s.charAt(ei)) {
                        dp[si][ei] = dp[si + 1][ei - 1] + 2;
                    } else {
                        dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                    }
                }
                si++;
                ei++;
            }
        }
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return dp[0][len - 1];
    }

    public String longestPalindrome_(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int st = 0;
        int end = 0;
        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else {
                    if (s.charAt(si) == s.charAt(ei)) {
                        if (dp[si + 1][ei - 1] == gap - 1) {
                            st = si;
                            end = ei;
                            dp[si][ei] += dp[si + 1][ei - 1] + 2;
                        } else {
                            Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                        }
                    } else {
                        Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                    }
                }
                si++;
                ei++;
            }
        }
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        return s.substring(st, end + 1);
    }

}