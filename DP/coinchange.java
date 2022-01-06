import java.util.*;

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
        // int ans = permutationInfyTabBaseSubseq1D(nums, tar, 0);
        // int[] nums = { 2, 3, -5, 7 };
        // int tar = 2;
        // int ans = combiNeg(nums, tar, 0, 0);
        // int ans = combiNegMemo(nums, tar, 0, 0, new int[nums.length][tar + 1]);
        // int ans = combinationSingleTabNegCase(nums, tar, 0);
        // System.out.println(ans);
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
        createSingle(nums, nums.length - 1, tar, dp, "");
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
        for (int[] ar : dp) {
            for (int ele : ar) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        createSingle(nums, nums.length, tar, dp, "");
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

    // NEGATIVE CASES FOR SUBSET SUM

    public static int combiNeg(int[] nums, int tar, int idx, int count) {
        if (tar == 0 || idx == nums.length) {
            if (tar == 0 && count != 0) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        ans += combiNeg(nums, tar - nums[idx], idx + 1, count + 1);
        ans += combiNeg(nums, tar, idx + 1, count);
        return ans;
    }

    public static int combiNegMemo(int[] nums, int tar, int idx, int count, int[][] dp) {
        if (tar == 0 || idx == nums.length) {
            if (tar == 0 && count != 0) {
                return 1;
            }
            return 0;
        }
        if (dp[idx][tar] != 0) {
            return dp[idx][tar];
        }
        int ans = 0;
        ans += combiNegMemo(nums, tar - nums[idx], idx + 1, count + 1, dp);
        ans += combiNegMemo(nums, tar, idx + 1, count, dp);
        dp[idx][tar] = ans;
        return ans;
    }

    public static int combinationSingleTabNegCase(int[] nums, int tar, int idx) {
        int negsum = 0;
        for (int ele : nums) {
            if (ele < 0) {
                negsum += (-ele);
            }
        }
        int[] prev = new int[tar + 2 * negsum + 1];
        int[] dp = new int[tar + 2 * negsum + 1];
        prev[negsum] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[j] += prev[j];
                if (j - nums[i - 1] >= 0 && j - nums[i - 1] < dp.length) {
                    dp[j] += prev[j - nums[i - 1]];
                }
            }
            prev = dp;
            dp = new int[tar + 2 * negsum + 1];
        }
        return prev[tar + negsum];
    }

    // TARGET SUM LEETCODE
    public static int findTargetSumWays(int[] nums, int tar) {
        int negsum = 0;
        for (int ele : nums) {
            negsum += ele;
        }
        int[] dp = new int[Math.abs(tar) + 2 * negsum + 1];
        int[] prev = new int[Math.abs(tar) + 2 * negsum + 1];
        prev[negsum] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                // +ve
                int pos = j - nums[i];
                if (pos >= 0 && pos < dp.length) {
                    dp[j] += prev[pos];
                }
                // -ve
                int neg = j + nums[i];
                if (neg >= 0 && neg < dp.length) {
                    dp[j] += prev[neg];
                }
            }
            prev = dp;
            dp = new int[Math.abs(tar) + 2 * negsum + 1];
        }
        return prev[Math.abs(tar) + negsum];
    }
    // CREATE ANSWER FOR MULTIPLE COMBINATION

    public static void createMultiple(int[] nums, int idx, int tar, int[][] dp, String ans) {
        if (tar == 0 || idx == -1) {
            if (tar == 0) {
                System.out.println(ans);
            }
            return;
        }
        createMultiple(nums, idx - 1, tar, dp, ans);
        if (tar - nums[idx] >= 0)
        createMultiple(nums, idx, tar - nums[idx], dp, nums[idx] + " " + ans);
    }
}