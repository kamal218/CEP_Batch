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

    // LONGEST COMMON SUBSEQ
    public int longestCommonSubsequence(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // LONGEST COMMON SUBSTRING

    public int longestCommonSubstring(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        int ans = 0;
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public String longestCommonSubsequenceWithString(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        String[] dp = new String[l2 + 1];
        Arrays.fill(dp, "");
        String[] help = new String[l2 + 1];
        for (int i = l1 - 1; i >= 0; i--) {
            help[l2] = "";
            for (int j = l2 - 1; j >= 0; j--) {
                help[j] = "";
                if (s1.charAt(i) == s2.charAt(j)) {
                    help[j] = (s1.charAt(i)) + dp[j + 1];
                } else {
                    String d = dp[j];
                    String r = help[j + 1];
                    if (r.length() > d.length()) {
                        help[j] = r;
                    } else {
                        help[j] = d;
                    }
                }
            }
            dp = help;
            help = new String[l2 + 1];
        }
        return dp[0];
    }

    static int ans = 0;

    public static int help(String s1, String s2, int i, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        int tr = 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            tr = help(s1, s2, i + 1, j + 1) + 1;
        }
        help(s1, s2, i + 1, j);
        help(s1, s2, i, j + 1);
        ans = Math.max(ans, tr);
        return tr;
    }

    // LCS OF TRIPLET

    int LCSof3(String s1, String s2, String s3, int n1, int n2, int n3) {
        int[][][] dp = new int[n1 + 1][n2 + 1][n3 + 1];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                for (int k = n3 - 1; k >= 0; k--) {
                    if (s1.charAt(i) == s2.charAt(j) && s2.charAt(j) == s3.charAt(k)) {
                        dp[i][j][k] = dp[i + 1][j + 1][k + 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i + 1][j][k], Math.max(dp[i][j + 1][k], dp[i][j][k + 1]));
                    }
                }
            }
        }
        return dp[0][0][0];
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = longestCommonSubsequenceWithString(str1, str2);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        for (char ch : lcs.toCharArray()) {
            while (str1.charAt(i) != ch) {
                sb.append(str1.charAt(i));
                i++;
            }

            while (str2.charAt(j) != ch) {
                sb.append(str2.charAt(j));
                j++;
            }
            // lcs character
            sb.append(ch);
            i++;
            j++;
        }

        while (i < str1.length()) {
            sb.append(str1.charAt(i));
            i++;
        }

        while (j < str2.length()) {
            sb.append(str2.charAt(j));
            j++;
        }

        return sb.toString();
    }

    // DISTINCT SUBSQ 1
    public int numDistinct(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        int[] dp = new int[slen];
        dp[slen - 1] = s.charAt(slen - 1) == t.charAt(tlen - 1) ? 1 : 0;
        for (int i = slen - 2; i >= 0; i--) {
            dp[i] += dp[i + 1];
            if (s.charAt(i) == t.charAt(tlen - 1)) {
                dp[i]++;
            }
        }
        int d = 2;
        int[] help = new int[slen];
        for (int i = tlen - 2; i >= 0; i--) {
            for (int j = slen - d; j >= 0; j--) {
                help[j] = help[j + 1];// right value
                if (t.charAt(i) == s.charAt(j)) {
                    help[j] += dp[j + 1];
                }
            }
            d++;
            dp = help;
            help = new int[slen];
        }
        return dp[0];
    }

    public int distinctSubseqII(String s) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;// for empty string
        int i = 1;
        long mod = 1000000007;
        for (char ch : s.toCharArray()) {
            dp[i] = (dp[i - 1] * 2) % mod;
            if (map[ch - 'a'] != -1) {
                dp[i] = (dp[i] % mod - dp[map[ch - 'a'] - 1] % mod) % mod;
            }
            if (dp[i] < 0) {
                dp[i] += mod;
            }
            map[ch - 'a'] = i;
            i++;
        }
        return (int) ((dp[s.length()] - 1) % mod);
    }

    public int longestRepeatingSubsequence(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j) && i != j) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    public static String longestSubstring(String S, int N) {
        String s1 = S;
        String s2 = S;
        int l1 = s1.length();
        int l2 = s2.length();
        int st = 0;
        int end = -1;
        int len = 0;
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j) && i != j) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                if (len <= dp[i][j]) {
                    st = j;
                    len = dp[i][j];
                }
            }
        }
        if (len == 0) {
            return "-1";
        }
        return S.substring(st, st + len);
    }

    // ALL DISTINCT SUBSEQ

    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[] left = new int[len];
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            left[i] = map[ch - 'a'];
            map[ch - 'a'] = i;
        }

        int[] right = new int[len];
        Arrays.fill(map, len);
        for (int i = len - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            right[i] = map[ch - 'a'];
            map[ch - 'a'] = i;
        }

        long[][] dp = new long[len][len];
        long mod = (int) (1e9) + 7;
        for (int gap = 0; gap < len; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < len) {
                if (gap == 0) {
                    dp[si][ei] = 1;
                } else if (gap == 1) {
                    dp[si][ei] = 2;
                } else {
                    if (s.charAt(si) == s.charAt(ei)) {
                        int lidx = right[si];
                        int ridx = left[ei];
                        // no same in middle
                        if (lidx > ridx) {
                            dp[si][ei] = (2 * dp[si + 1][ei - 1] % mod) + 2;
                        }
                        // single occurrence middle
                        else if (lidx == ridx) {
                            dp[si][ei] = (2 * dp[si + 1][ei - 1] % mod) + 1;
                        }
                        // multiple occurrence middle
                        else {
                            dp[si][ei] = ((2 * dp[si + 1][ei - 1] % mod) - dp[lidx + 1][ridx - 1] % mod) % mod;
                        }
                    } else {
                        dp[si][ei] = (dp[si][ei] % mod + dp[si][ei - 1] % mod + dp[si + 1][ei] % mod
                                - dp[si + 1][ei - 1] % mod) % mod;
                    }
                    if (dp[si][ei] < 0) {
                        dp[si][ei] += mod;
                    }
                }
                si++;
                ei++;
            }
        }
        return (int) dp[0][len - 1];

    }

}