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
        // int ans = bridges();
        int[][] arr = { { 4, 6, 7 }, { 1, 2, 3 }, { 4, 5, 6 }, { 10, 12, 32 } };
        // int ans = boxStacking(arr);
        int ans = boxStackingOpt(arr);
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

    // RUSSIAN DOLL ENVELOPE
    public int maxEnvelopes(int[][] nums) {
        // SORT ON SECOND
        Arrays.sort(nums, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            } else {
                return a[1] - b[1];
            }
        });

        // LIS ON FIRST
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            // int cidx = binarySearch(nums, 0, ans - 1, nums[i]);// ceil index
            int cidx = binarySearchRussian(nums, 0, ans - 1, nums[i][0]);// ceil index
            if (cidx == ans) {
                ans++;
            }
            nums[cidx][0] = nums[i][0];
        }
        return ans;
    }

    public static int binarySearchRussian(int[][] nums, int si, int ei, int val) {
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid][0] == val) {
                return mid;
            } else if (nums[mid][0] < val) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }

    // BOX STACKING
    public static int boxStacking(int[][] box) {
        int len = box.length;
        int[][] arr = new int[6 * len][3];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                arr[6 * i + 2 * j][0] = box[i][j];
                arr[6 * i + 2 * j][1] = box[i][(j + 1) % 3];
                arr[6 * i + 2 * j][2] = box[i][(j + 2) % 3];
                arr[6 * i + 2 * j + 1][0] = box[i][j];
                arr[6 * i + 2 * j + 1][1] = box[i][(j + 2) % 3];
                arr[6 * i + 2 * j + 1][2] = box[i][(j + 1) % 3];
            }
        }
        // SORT ON SECOND

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return b[2] - a[2];
            } else {
                return a[1] - b[1];
            }
        });

        // LIS ON THIRD AND ANSWER ON FIRST
        int ans = lisBoxStacking(arr);
        return ans;
    }

    public static int lisBoxStacking(int[][] arr) {
        int len = arr.length;
        int[] dp = new int[len];
        int ans = arr[0][0];
        dp[0] = arr[0][0];
        for (int i = 1; i < len; i++) {
            dp[i] = arr[i][0];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i][2] > arr[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i][0]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int boxStackingOpt(int[][] box) {
        int len = box.length;
        int[][] arr = new int[3 * len][3];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                arr[3 * i + j][0] = box[i][j];
                arr[3 * i + j][1] = Math.min(box[i][(j + 1) % 3], box[i][(j + 2) % 3]);
                arr[3 * i + j][2] = Math.max(box[i][(j + 1) % 3], box[i][(j + 2) % 3]);

            }
        }
        // SORT ON SECOND

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return b[2] - a[2];
            } else {
                return a[1] - b[1];
            }
        });

        // LIS ON THIRD AND ANSWER ON FIRST
        int ans = lisBoxStacking(arr);
        return ans;
    }

    // MAX LENGTH PAIR
    // public static int maxChainLength(Pair[] nums, int n) {
    // Arrays.sort(nums, (a, b) -> (a.x - b.x));
    // int len = nums.length;
    // int[] dp = new int[len];
    // int ans = 1;
    // dp[0] = 1;
    // for (int i = 1; i < nums.length; i++) {
    // dp[i] = 1;
    // for (int j = i - 1; j >= 0; j--) {
    // if (nums[j].y < nums[i].x)
    // dp[i] = Math.max(dp[i], dp[j] + 1);
    // }
    // ans = Math.max(ans, dp[i]);
    // }
    // return ans;
    // }

}