public class buysell {
    public static void main(String[] args) {

    }

    // 3D array
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len + 1][2][2];
        for (int i = 0; i <= len; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        dp[0][1][1] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
        }
        return dp[len][1][0];
    }

    // 1D array
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int[] sell = new int[len + 1];
        int[] buy = new int[len + 1];

        buy[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
            buy[i] = Math.max(buy[i - 1], -prices[i - 1]);
        }
        return sell[len];
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        for (int ele : prices) {
            sell = Math.max(sell, buy + ele);
            buy = Math.max(buy, -ele);
        }
        return sell;
    }

    // BUY SELL 2
    public int maxProfit(int[] prices) {
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        for (int ele : prices) {
            int t = sell;
            sell = Math.max(sell, buy + ele);
            buy = Math.max(buy, t - ele);
        }
        return sell;
    }

    // BUY SELL with fees
    public int maxProfit(int[] prices, int fee) {
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        for (int ele : prices) {
            int t = sell;
            sell = Math.max(sell, buy + ele);
            buy = Math.max(buy, t - ele - fee);
        }
        return sell;
    }

    // BUY SELL with cooldown
    public int maxProfit(int[] prices) {
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        int pot = 0;// previous of temp
        for (int ele : prices) {
            int t = sell;
            sell = Math.max(sell, buy + ele);
            buy = Math.max(buy, pot - ele);
            pot = t;
        }
        return sell;
    }

    // 2 TRANSATION
    public int maxProfit(int[] prices) {
        int dp20 = 0;
        int dp10 = 0;
        int dp21 = Integer.MIN_VALUE;
        int dp11 = Integer.MIN_VALUE;
        for (int ele : prices) {
            dp20 = Math.max(dp20, dp21 + ele);
            dp21 = Math.max(dp21, dp10 - ele);
            dp10 = Math.max(dp10, dp11 + ele);
            dp11 = Math.max(dp11, -ele);
        }
        return dp20;
    }

}