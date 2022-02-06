public class EggDrop {
    public static void main(String[] args) {
        int k = 2;
        int n = 17;
        int[][] dp = new int[k + 1][n + 1];
        int ans = eggDrop(k, n, dp);
        System.out.println(ans);
    }

    public static int eggDrop(int k, int n, int[][] dp) {// k eggs, n floors
        if (k == 1 || n == 1) {
            return n;
        }
        if (dp[k][n] != 0) {
            return dp[k][n];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int esurvive = eggDrop(k, n - i, dp);
            int ebreak = eggDrop(k - 1, i - 1, dp);
            int worst = Math.max(esurvive, ebreak);
            ans = Math.min(ans, worst);
        }
        return dp[k][n] = (ans + 1);
    }

    public static int eggDrop(int k, int n) {// k eggs, n floors
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    dp[i][j] = j;
                    continue;
                }
                int ans = Integer.MAX_VALUE;
                for (int l = 1; l <= j; l++) {
                    int esurvive = dp[i][j - l];
                    int ebreak = dp[i - 1][l - 1];
                    int worst = Math.max(esurvive, ebreak);
                    ans = Math.min(ans, worst);
                }
                dp[i][j] = ans + 1;
            }
        }
        return dp[k][n];
    }

    public static int eggDrop(int k, int n) {// k eggs, n floors
        int[][] dp = new int[n + 1][k + 1];// moves vs eggs
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int esurvive = dp[i - 1][j];
                int ebreak = dp[i - 1][j - 1];
                dp[i][j] = esurvive + ebreak + 1;
                if (dp[i][j] >= n) {
                    return i;
                }
            }
        }
        return 0;
    }

}