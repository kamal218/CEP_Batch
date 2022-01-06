import java.util.*;

public class lis {
    public static void main(String[] args) {
        // int[] nums = { 1, 7, 8, 3, 2, 5, 9, 6, 4, 7, 9, 3 };
        // int[] nums = { 2, 2, 2, 2, 2 };
        // int ans = 1;
        // for (int i = nums.length - 1; i >= 0; i--) {
        // int recans = LIS(nums, i);
        // ans = Math.max(ans, recans);
        // }
        // int[] ans = new int[1];
        // LIS(nums, nums.length - 1, ans);
        // LISMemo(nums, nums.length - 1, ans, new int[nums.length]);
        // int ans = LIS(nums);
        // int ans = LISOptimized(nums);// can only give length
        // System.out.println(ans[0]);

        // int[] nums = { 1, 101, 2, 3, 4 };
        // int ans = maxSUmIncSubseq(nums);
        int ans = bridges();
        System.out.println(ans);
    }

    public static int LIS(int[] nums, int eidx) {
        int ans = 1;
        for (int j = eidx - 1; j >= 0; j--) {
            if (nums[j] < nums[eidx]) {
                int recans = LIS(nums, j);
                ans = Math.max(ans, recans + 1);
            }
        }
        return ans;
    }

    public static int LIS(int[] nums, int eidx, int[] oans) {
        // eidx==0
        int ans = 1;
        for (int j = eidx - 1; j >= 0; j--) {
            int recans = LIS(nums, j, oans);
            if (nums[j] < nums[eidx]) {
                ans = Math.max(ans, recans + 1);
            }
        }

        oans[0] = Math.max(oans[0], ans);
        return ans;
    }

    public static int LISMemo(int[] nums, int eidx, int[] oans, int[] dp) {
        int ans = 1;
        if (dp[eidx] != 0) {
            return dp[eidx];
        }
        for (int j = eidx - 1; j >= 0; j--) {
            int recans = LISMemo(nums, j, oans, dp);
            if (nums[j] < nums[eidx]) {
                ans = Math.max(ans, recans + 1);
            }
        }
        dp[eidx] = ans;
        oans[0] = Math.max(oans[0], ans);
        return ans;
    }

    public static int LIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // LIS IN NLOGN

    public static int LISOptimized(int[] nums) {
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            // int cidx = binarySearch(nums, 0, ans - 1, nums[i]);// ceil index
            int cidx = binarySearchEquality(nums, 0, ans - 1, nums[i]);// ceil index
            if (cidx == ans) {
                ans++;
            }
            nums[cidx] = nums[i];
        }
        return ans;
    }

    public static int binarySearch(int[] nums, int si, int ei, int val) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }

    public static int binarySearchEquality(int[] nums, int si, int ei, int val) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] <= val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }

    // MAX SUM INCREASING SUBSEQ

    public static int maxSUmIncSubseq(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int ans = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // BUILDING BRIDGES
    public static int bridges() {
        int[][] nums = { { 2, 3 }, { 2, 4 }, { 3, 2 }, { 4, 5 }, { 4, 6 }, { 5, 3 } };
        // SORT ON SECOND
        Arrays.sort(nums, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });

        // LIS ON FIRST
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            // int cidx = binarySearch(nums, 0, ans - 1, nums[i]);// ceil index
            int cidx = binarySearchBride(nums, 0, ans - 1, nums[i][0]);// ceil index
            if (cidx == ans) {
                ans++;
            }
            nums[cidx][0] = nums[i][0];
        }
        return ans;
    }

    public static int binarySearchBride(int[][] nums, int si, int ei, int val) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid][0] <= val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }
}