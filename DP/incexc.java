public class incexc {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int ans = binaryStringNo2Same(4);
        int ans = binaryStringNo3Same(3);
        System.out.println(ans);
    }

    public static int binaryStringNo2Same(int n) {
        int one = 1;
        int zero = 1;
        for (int i = 1; i < n; i++) {
            int t = zero;
            zero = zero + one;
            one = t;
        }
        return zero + one;
    }

    public static int binaryStringNo3Same(int n) {
        int one = 1;
        int zero = 1;
        int oneone = 0;
        for (int i = 1; i < n; i++) {
            int none = zero;
            int nzero = one + zero + oneone;
            int noneone = one;

            one = none;
            zero = nzero;
            oneone = noneone;
        }
        return zero + one + oneone;
    }

    public int numberOfUniqueGoodSubsequences(String binary) {
        long zero = 0;
        long one = 0;
        boolean iszp = false;// is zero present
        long mod = 1000000007;
        for (Char ch : binary.toCharArray()) {
            if (ch == '0') {
                zero = (zero + one) % mod;
                iszp = true;
            } else {
                one = (zero + one + 1) % mod;
            }
        }
        return (int) (iszp ? (zero + one + 1) % mod : (zero + one) % mod);
    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int ans = rob(nums, 0, dp);
        return ans;
    }

    public static int rob(int[] nums, int idx, int[] dp) {
        if (idx >= nums.length) {
            return 0;
        }
        if (dp[idx] != -1) {
            return dp[idx];
        }
        // include
        int inc = rob(nums, idx + 2, dp) + nums[idx];
        // exclude
        int exc = rob(nums, idx + 1, dp);
        return dp[idx] = Math.max(inc, exc);
    }

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        int len = nums.length;
        dp[len - 1] = nums[len - 1];
        dp[len - 2] = Math.max(nums[len - 1], nums[len - 2]);
        for (int i = len - 3; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        return dp[0];
    }

    public static int rob(int[] nums) {
        int len = nums.length;
        int rright = nums[len - 1];
        int right = Math.max(nums[len - 1], nums[len - 2]);
        for (int i = len - 3; i >= 0; i--) {
            int curr = Math.max(right, rright + nums[i]);
            rright = right;
            right = curr;
        }
        return right;
    }

    // HOUSE ROBBER 2

    public int rob2(int[] nums) {
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public static int rob(int[] nums, int si, int ei) {
        int len = nums.length;
        if (si == ei) {
            return nums[si];
        }
        int rright = nums[ei];
        int right = Math.max(nums[ei], nums[ei - 1]);
        for (int i = ei - 2; i >= si; i--) {
            int curr = Math.max(right, rright + nums[i]);
            rright = right;
            right = curr;
        }
        return right;
    }

    public int maxSizeSlices(int[] slices) {
        int len = slices.length;
        int[][] dp = new int[slices.length][len / 3 + 1];
        int ans1 = pizzaSlices(slices, 0, len - 2, len / 3, dp);
        dp = new int[slices.length][len / 3 + 1];
        int ans2 = pizzaSlices(slices, 1, len - 1, len / 3, dp);
        return Math.max(ans1, ans2);
    }

    public static int pizzaSlices(int[] slices, int si, int ei, int totslices, int[][] dp) {
        if (si > ei || totslices == 0) {
            return 0;
        }
        if (dp[si][totslices] != 0) {
            return dp[si][totslices];
        }
        // include
        int inc = pizzaSlices(slices, si + 2, ei, totslices - 1, dp) + slices[si];
        // exclude
        int exc = pizzaSlices(slices, si + 1, ei, totslices, dp);
        return dp[si][totslices] = Math.max(inc, exc);
    }

    public int numWays(int n, int k) {
        int same = 0;
        int diff = k;
        for (int i = 1; i < n; i++) {
            int t = same;
            same = diff;
            diff = (t + diff) * (k - 1);
        }
        return same + diff;
    }

    public int minCost(int[][] costs) {
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        for (int i = 0; i < costs.length; i++) {
            int nc1 = Math.min(c2, c3) + costs[i][0];
            int nc2 = Math.min(c1, c3) + costs[i][1];
            int nc3 = Math.min(c2, c1) + costs[i][2];

            c1 = nc1;
            c2 = nc2;
            c3 = nc3;
        }
        return Math.min(Math.min(c1, c2), c3);
    }

}