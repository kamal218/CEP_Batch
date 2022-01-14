import java.util.*;

public class cutType {

    public static void main(String[] args) {
        List<List<String>> ans = partition("aab");
        System.out.println(ans);
    }

    // PALINDROME PARTITION 1
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partition(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void partition(String str, int st, List<String> sans, List<List<String>> ans) {
        if (st == str.length()) {
            ans.add(new ArrayList(sans));
            return;
        }

        for (int i = st; i < str.length(); i++) {
            if (ispalin(str, st, i)) {
                sans.add(str.substring(st, i + 1));
                partition(str, i + 1, sans, ans);
                sans.remove(sans.size() - 1);
            }
        }
    }

    public static boolean ispalin(String str, int st, int end) {
        while (st < end) {
            if (str.charAt(st) != str.charAt(end)) {
                return false;
            }
            st++;
            end--;
        }
        return true;
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        boolean[][] palin = allpalin(s);
        partition(s, 0, new ArrayList<>(), ans, palin);
        return ans;
    }

    public static void partition(String str, int st, List<String> sans, List<List<String>> ans, boolean[][] palin) {
        if (st == str.length()) {
            ans.add(new ArrayList(sans));
            return;
        }

        for (int i = st; i < str.length(); i++) {
            if (palin[st][i]) {
                sans.add(str.substring(st, i + 1));
                partition(str, i + 1, sans, ans, palin);
                sans.remove(sans.size() - 1);
            }
        }
    }

    public static boolean[][] allpalin(String str) {
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
        for (int gap = 0; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < dp.length) {
                // 1 length
                if (gap == 0) {
                    dp[si][ei] = true;
                }
                // 2 length
                else if (gap == 1) {
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = true;
                    }
                }
                // >2length
                else {
                    if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1]) {
                        dp[si][ei] = true;
                    }
                }
                si++;
                ei++;
            }
        }
        return dp;
    }

    // USING ITERATIVE

    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>>[] dp = new ArrayList[len];
        boolean[][] palin = allpalin(s);
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = new ArrayList<>();
            // complete string is palindrome
            if (palin[i][len - 1]) {
                List<String> base = new ArrayList<>();
                base.add(s.substring(i, len));
                dp[i].add(base);
            }
            for (int j = i; j < len - 1; j++) {
                if (palin[i][j]) {
                    List<List<String>> jpo = dp[j + 1];// list of j+1
                    for (List<String> list : jpo) {
                        List<String> base = new ArrayList<>(list);
                        base.add(s.substring(i, j + 1));
                        dp[i].add(base);
                    }
                }
            }
        }
        return dp[0];
    }

    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>>[] dp = new ArrayList[len];
        boolean[][] palin = allpalin(s);
        for (int i = 0; i < len; i++) {
            dp[i] = new ArrayList<>();
            // complete string is palindrome
            if (palin[0][i]) {
                List<String> base = new ArrayList<>();
                base.add(s.substring(0, i + 1));
                dp[i].add(base);
            }
            for (int j = i; j > 0; j--) {
                if (palin[j][i]) {
                    List<List<String>> jpo = dp[j - 1];// list of j-1
                    for (List<String> list : jpo) {
                        List<String> base = new ArrayList<>(list);
                        base.add(s.substring(j, i + 1));
                        dp[i].add(base);
                    }
                }
            }
        }
        return dp[len - 1];
    }

    public int minCut(String s) {
        int len = s.length();
        int[] dp = new int[len];
        boolean[][] palin = allpalin(s);
        for (int i = len - 1; i >= 0; i--) {
            if (palin[i][len - 1]) {
                dp[i] = 0;
            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i; j < len - 1; j++) {
                    if (palin[i][j]) {
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    }
                }
            }
        }
        return dp[0];
    }

    // MIN CHANGES
    public int palindromePartition(String s, int k) {
        int[][] mincost = mincost(s);
        Integer[][] dp = new Integer[k + 1][s.length()];

        return palindromePartition(s, 0, k, mincost, dp);
    }

    public static int palindromePartition(String str, int st, int k, int[][] mincost, Integer[][] dp) {
        if (st == str.length()) {
            return 0;
        }
        if (k == 1) {
            return mincost[st][str.length() - 1];
        }
        if (dp[k][st] != null) {
            return dp[k][st];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = st; i <= str.length() - k; i++) {
            ans = Math.min(ans, palindromePartition(str, i + 1, k - 1, mincost, dp) + mincost[st][i]);
        }
        return dp[k][st] = ans;
    }

    public static int[][] mincost(String str) {
        int len = str.length();
        int[][] dp = new int[len][len];
        for (int gap = 1; gap < dp.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < dp.length) {
                if (gap == 1) {
                    if (str.charAt(si) != str.charAt(ei)) {
                        dp[si][ei] = 1;
                    }
                }
                // >2length
                else {
                    if (str.charAt(si) == str.charAt(ei)) {
                        dp[si][ei] = dp[si + 1][ei - 1];
                    } else {
                        dp[si][ei] = dp[si + 1][ei - 1] + 1;
                    }
                }
                si++;
                ei++;
            }
        }
        return dp;
    }
}