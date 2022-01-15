import java.util.*;

public class cutType {

    public static void main(String[] args) {
        // List<List<String>> ans = partition("aab");
        // System.out.println(ans);
        // mcm();
        // mcm_();
        // mcm__();
        char[] exp = { 'T', 'T', 'F', 'T' };
        char[] sym = { '|', '&', '^' };
        booleanParenthese(exp, sym);
    }

    // // PALINDROME PARTITION 1
    // public static List<List<String>> partition(String s) {
    // List<List<String>> ans = new ArrayList<>();
    // partition(s, 0, new ArrayList<>(), ans);
    // return ans;
    // }

    // public static void partition(String str, int st, List<String> sans,
    // List<List<String>> ans) {
    // if (st == str.length()) {
    // ans.add(new ArrayList(sans));
    // return;
    // }

    // for (int i = st; i < str.length(); i++) {
    // if (ispalin(str, st, i)) {
    // sans.add(str.substring(st, i + 1));
    // partition(str, i + 1, sans, ans);
    // sans.remove(sans.size() - 1);
    // }
    // }
    // }

    // public static boolean ispalin(String str, int st, int end) {
    // while (st < end) {
    // if (str.charAt(st) != str.charAt(end)) {
    // return false;
    // }
    // st++;
    // end--;
    // }
    // return true;
    // }

    // public static List<List<String>> partition(String s) {
    // List<List<String>> ans = new ArrayList<>();
    // boolean[][] palin = allpalin(s);
    // partition(s, 0, new ArrayList<>(), ans, palin);
    // return ans;
    // }

    // public static void partition(String str, int st, List<String> sans,
    // List<List<String>> ans, boolean[][] palin) {
    // if (st == str.length()) {
    // ans.add(new ArrayList(sans));
    // return;
    // }

    // for (int i = st; i < str.length(); i++) {
    // if (palin[st][i]) {
    // sans.add(str.substring(st, i + 1));
    // partition(str, i + 1, sans, ans, palin);
    // sans.remove(sans.size() - 1);
    // }
    // }
    // }

    // public static boolean[][] allpalin(String str) {
    // int len = str.length();
    // boolean[][] dp = new boolean[len][len];
    // for (int gap = 0; gap < dp.length; gap++) {
    // int si = 0;
    // int ei = gap;
    // while (ei < dp.length) {
    // // 1 length
    // if (gap == 0) {
    // dp[si][ei] = true;
    // }
    // // 2 length
    // else if (gap == 1) {
    // if (str.charAt(si) == str.charAt(ei)) {
    // dp[si][ei] = true;
    // }
    // }
    // // >2length
    // else {
    // if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1]) {
    // dp[si][ei] = true;
    // }
    // }
    // si++;
    // ei++;
    // }
    // }
    // return dp;
    // }

    // // USING ITERATIVE

    // public List<List<String>> partition(String s) {
    // int len = s.length();
    // List<List<String>>[] dp = new ArrayList[len];
    // boolean[][] palin = allpalin(s);
    // for (int i = len - 1; i >= 0; i--) {
    // dp[i] = new ArrayList<>();
    // // complete string is palindrome
    // if (palin[i][len - 1]) {
    // List<String> base = new ArrayList<>();
    // base.add(s.substring(i, len));
    // dp[i].add(base);
    // }
    // for (int j = i; j < len - 1; j++) {
    // if (palin[i][j]) {
    // List<List<String>> jpo = dp[j + 1];// list of j+1
    // for (List<String> list : jpo) {
    // List<String> base = new ArrayList<>(list);
    // base.add(s.substring(i, j + 1));
    // dp[i].add(base);
    // }
    // }
    // }
    // }
    // return dp[0];
    // }

    // public List<List<String>> partition(String s) {
    // int len = s.length();
    // List<List<String>>[] dp = new ArrayList[len];
    // boolean[][] palin = allpalin(s);
    // for (int i = 0; i < len; i++) {
    // dp[i] = new ArrayList<>();
    // // complete string is palindrome
    // if (palin[0][i]) {
    // List<String> base = new ArrayList<>();
    // base.add(s.substring(0, i + 1));
    // dp[i].add(base);
    // }
    // for (int j = i; j > 0; j--) {
    // if (palin[j][i]) {
    // List<List<String>> jpo = dp[j - 1];// list of j-1
    // for (List<String> list : jpo) {
    // List<String> base = new ArrayList<>(list);
    // base.add(s.substring(j, i + 1));
    // dp[i].add(base);
    // }
    // }
    // }
    // }
    // return dp[len - 1];
    // }

    // public int minCut(String s) {
    // int len = s.length();
    // int[] dp = new int[len];
    // boolean[][] palin = allpalin(s);
    // for (int i = len - 1; i >= 0; i--) {
    // if (palin[i][len - 1]) {
    // dp[i] = 0;
    // } else {
    // dp[i] = Integer.MAX_VALUE;
    // for (int j = i; j < len - 1; j++) {
    // if (palin[i][j]) {
    // dp[i] = Math.min(dp[i], dp[j + 1] + 1);
    // }
    // }
    // }
    // }
    // return dp[0];
    // }

    // // MIN CHANGES
    // public int palindromePartition(String s, int k) {
    // int[][] mincost = mincost(s);
    // Integer[][] dp = new Integer[k + 1][s.length()];

    // return palindromePartition(s, 0, k, mincost, dp);
    // }

    // public static int palindromePartition(String str, int st, int k, int[][]
    // mincost, Integer[][] dp) {
    // if (st == str.length()) {
    // return 0;
    // }
    // if (k == 1) {
    // return mincost[st][str.length() - 1];
    // }
    // if (dp[k][st] != null) {
    // return dp[k][st];
    // }
    // int ans = Integer.MAX_VALUE;
    // for (int i = st; i <= str.length() - k; i++) {
    // ans = Math.min(ans, palindromePartition(str, i + 1, k - 1, mincost, dp) +
    // mincost[st][i]);
    // }
    // return dp[k][st] = ans;
    // }

    // public static int[][] mincost(String str) {
    // int len = str.length();
    // int[][] dp = new int[len][len];
    // for (int gap = 1; gap < dp.length; gap++) {
    // int si = 0;
    // int ei = gap;
    // while (ei < dp.length) {
    // if (gap == 1) {
    // if (str.charAt(si) != str.charAt(ei)) {
    // dp[si][ei] = 1;
    // }
    // }
    // // >2length
    // else {
    // if (str.charAt(si) == str.charAt(ei)) {
    // dp[si][ei] = dp[si + 1][ei - 1];
    // } else {
    // dp[si][ei] = dp[si + 1][ei - 1] + 1;
    // }
    // }
    // si++;
    // ei++;
    // }
    // }
    // return dp;
    // }

    public static void mcm() {
        int[] arr = { 10, 30, 5, 60, 20 };
        int len = arr.length;
        int[][] dp = new int[len][len];
        int ans = mcm(arr, 0, arr.length - 1, dp);
        System.out.println(ans);
    }

    public static int mcm(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return 0;
        }
        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }
        int ans = Integer.MAX_VALUE;
        for (int cut = si + 1; cut < ei; cut++) {
            int left = mcm(arr, si, cut, dp);
            int right = mcm(arr, cut, ei, dp);
            ans = Math.min(ans, left + right + arr[si] * arr[ei] * arr[cut]);
        }
        return dp[si][ei] = ans;
    }

    public static int mcm_() {
        int[] arr = { 10, 30, 5, 60, 20 };
        int len = arr.length;
        int[][] dp = new int[len][len];
        for (int gap = 2; gap < arr.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < arr.length) {
                dp[si][ei] = Integer.MAX_VALUE;
                for (int cut = si + 1; cut < ei; cut++) {
                    int left = dp[si][cut];
                    int right = dp[cut][ei];
                    int own = arr[si] * arr[ei] * arr[cut];
                    dp[si][ei] = Math.min(dp[si][ei], left + right + own);
                }
                si++;
                ei++;
            }
        }
        System.out.println(dp[0][len - 1]);
        return dp[0][len - 1];
    }

    public static int mcm__() {
        int[] arr = { 10, 30, 5, 60, 20 };
        int len = arr.length;
        int[][] dp = new int[len][len];
        String[][] sdp = new String[len][len];
        for (int gap = 1; gap < arr.length; gap++) {
            int si = 0;
            int ei = gap;
            while (ei < arr.length) {
                dp[si][ei] = Integer.MAX_VALUE;
                if (gap == 1) {
                    sdp[si][ei] = (char) ('A' + si) + "";
                    dp[si][ei] = 0;
                } else {
                    for (int cut = si + 1; cut < ei; cut++) {
                        int left = dp[si][cut];
                        int right = dp[cut][ei];
                        int own = arr[si] * arr[ei] * arr[cut];
                        int curr = left + right + own;
                        if (curr < dp[si][ei]) {
                            dp[si][ei] = curr;
                            sdp[si][ei] = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                        }
                    }
                }
                si++;
                ei++;
            }
        }
        System.out.println(dp[0][len - 1]);
        System.out.println(sdp[0][len - 1]);
        return dp[0][len - 1];
    }

    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 2][len + 2];
        return burst(nums, -1, nums.length, dp);
    }

    public int burst(int[] nums, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return 0;
        }
        if (dp[si + 1][ei + 1] != 0) {
            return dp[si + 1][ei + 1];
        }
        int ans = Integer.MIN_VALUE;
        for (int b = si + 1; b < ei; b++) {
            int l = burst(nums, si, b, dp);
            int r = burst(nums, b, ei, dp);
            int left = si == -1 ? 1 : nums[si];
            int right = ei == nums.length ? 1 : nums[ei];
            ans = Math.max(ans, l + r + left * right * nums[b]);
        }
        return dp[si + 1][ei + 1] = ans;
    }

    public static int booleanParenthese(char[] exp, char[] sym) {
        int[][][] dp = new int[exp.length][exp.length][2];
        int[] ans = booleanParenthese(exp, sym, 0, exp.length - 1, dp);
        // System.out.println(ans[0]);
        return ans[0];
    }

    public static int[] booleanParenthese(char[] exp, char[] sym, int si, int ei, int[][][] dp) {
        if (si == ei) {
            return exp[si] == 'T' ? new int[] { 1, 0 } : new int[] { 0, 1 };
        }
        if (dp[si][ei][0] != 0) {
            return dp[si][ei];
        }

        int[] ans = new int[2];// true-0, false->1
        for (int p = si; p < ei; p++) {
            int[] left = booleanParenthese(exp, sym, si, p, dp);
            int[] right = booleanParenthese(exp, sym, p + 1, ei, dp);
            char csym = sym[p];
            int lt = left[0];
            int lf = left[1];
            int rt = right[0];
            int rf = right[1];
            if (csym == '&') {
                ans[0] += lt * rt;
                ans[1] += lt * rf + lf * rt + lf * rf;
            } else if (csym == '|') {
                ans[0] += lt * rt + lt * rf + lf * rt;
                ans[1] += lf * rf;
            } else {
                ans[0] += lt * rf + lf * rt;
                ans[1] += lf * rf + lt * rt;
            }
        }
        dp[si][ei][0] = ans[0];
        dp[si][ei][1] = ans[1];
        return ans;
    }

}