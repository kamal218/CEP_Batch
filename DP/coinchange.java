public class coinchange {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 5, 7 };
        int tar = 10;
        // int ans = permuteInfy(nums, tar);
        // int ans = permuteInfyMemo(nums, tar, new int[tar + 1]);
        // int ans = permuteInfyTab(nums, tar, new int[tar + 1]);
        // int ans = combinationInfy(nums, tar, 0);
        // int ans = combinationInfyMemo(nums, tar, 0, new int[nums.length][tar + 1]);
        // int ans = combinationInfyTab(nums, tar, 0, new int[nums.length][tar + 1]);
        // int ans = combinationInfyTab_(nums, tar, 0, new int[nums.length][tar + 1]);
        // int ans = combinationInfyTab(nums, tar, 0, new int[nums.length][tar + 1]);
        // int ans = combinationSingleTab(nums, tar, 0, new int[nums.length][tar + 1]);
        // int ans = combinationSingleTabBase(nums, tar, 0);
        // int ans = combinationSingleTabBaseSubseq(nums, tar, 0);
        // int ans = combinationSingleTabBaseSubseq1D(nums, tar, 0);
        int ans = permutationInfyTabBaseSubseq1D(nums, tar, 0);
        System.out.println(ans);
    }

    public static int permuteInfy(int[] nums, int tar) {
        if (tar == 0) {
            return 1;
        }
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                ans += permuteInfy(nums, tar - nums[i]);
            }
        }
        return ans;
    }

    public static int permuteInfyMemo(int[] nums, int tar, int[] dp) {
        if (tar == 0) {
            return 1;
        }
        int ans = 0;
        // checking
        if (dp[tar] != 0) {
            return dp[tar];
        }
        for (int i = 0; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                ans += permuteInfyMemo(nums, tar - nums[i], dp);
            }
        }
        // storing
        dp[tar] = ans;
        return ans;
    }

    public static int permuteInfyTab(int[] nums, int tar, int[] dp) {
        dp[0] = 1;
        for (int j = 1; j <= tar; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[tar];
    }

    public static int combinationInfy(int[] nums, int tar, int idx) {
        if (tar == 0) {
            return 1;
        }
        int ans = 0;
        // checking
        for (int i = idx; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                ans += combinationInfy(nums, tar - nums[i], i);
            }
        }
        // updating
        return ans;
    }

    public static int combinationInfyMemo(int[] nums, int tar, int idx, int[][] dp) {
        if (tar == 0) {
            return 1;
        }
        if (dp[idx][tar] != 0) {
            return dp[idx][tar];
        }
        int ans = 0;
        for (int i = idx; i < nums.length; i++) {
            if (tar - nums[i] >= 0) {
                ans += combinationInfyMemo(nums, tar - nums[i], i, dp);
            }
        }
        dp[idx][tar] = ans;
        return ans;
    }

    public static int combinationInfyTab(int[] nums, int tar, int idx, int[][] dp) {
        for (int j = 0; j <= tar; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j == 0) {
                    dp[i][0] = 1;
                    continue;
                }
                // prev answer storing
                dp[i][j] += i == 0 ? 0 : dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][tar];
    }

    public static int combinationSingleTab(int[] nums, int tar, int idx, int[][] dp) {
        for (int j = 0; j <= tar; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j == 0) {
                    dp[i][0] = 1;
                    continue;
                }
                // prev answer storing
                dp[i][j] += i == 0 ? 0 : dp[i - 1][j];
                if (j - nums[i] > 0) {
                    dp[i][j] += i == 0 ? 0 : dp[i - 1][j - nums[i]];
                }
                if (j == nums[i]) {
                    dp[i][j]++;
                }
            }
        }
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + "  ");
            }
            System.out.println();
        }
        return dp[nums.length - 1][tar];
    }

    public static int combinationSingleTabBase(int[] nums, int tar, int idx) {
        int[][] dp = new int[nums.length + 1][tar + 1];
        dp[0][0] = 1;
        for (int j = 0; j <= tar; j++) {
            for (int i = 1; i <= nums.length; i++) {
                dp[i][j] += dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][tar];
    }

    public static int combinationSingleTabBaseSubseq(int[] nums, int tar, int idx) {
        int[][] dp = new int[nums.length + 1][tar + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= tar; j++) {
                dp[i][j] += dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][tar];
    }

    public static int combinationSingleTabBaseSubseq1D(int[] nums, int tar, int idx) {
        int[] dp = new int[tar + 1];
        int[] prev = new int[tar + 1];
        prev[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= tar; j++) {
                dp[j] += prev[j];
                if (j - nums[i - 1] >= 0) {
                    dp[j] += prev[j - nums[i - 1]];
                }
            }
            prev = dp;
            dp = new int[tar + 1];
        }
        return prev[tar];
    }

    public static int combinationInfyTabBaseSubseq1D(int[] nums, int tar, int idx) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= tar; j++) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[tar];
    }

    public static int permutationInfyTabBaseSubseq1D(int[] nums, int tar, int idx) {
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        for (int j = 0; j <= tar; j++) {
            for (int i = 1; i <= nums.length; i++) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[tar];
    }

    public static int ZOKnapSack(int[] weight, int[] values, int max) {
        int[] dp = new int[max + 1];
        int[] prev = new int[max + 1];
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 0; j <= max; j++) {
                dp[j] = prev[j];
                if (j - weight[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j], prev[j - weight[i - 1]] + values[i - 1]);
                }
            }
            prev = dp;
            dp = new int[max + 1];
        }
        return prev[max];
    }

    public static int unboundedKnapsack(int[] weight, int[] values, int max) {
        int[] dp = new int[max + 1];
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 0; j <= max; j++) {
                if (j - weight[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[max];
    }

    public static int minCoins(int[] nums, int tar, int idx) {
        int[] dp = new int[tar + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= tar; j++) {
                if (j - nums[i] >= 0 && dp[j - nums[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
                }
            }
        }
        return dp[tar] == Integer.MAX_VALUE ? -1 : dp[tar];
    }

}