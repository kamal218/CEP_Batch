public class kadanes {
    public static void main(String[] args) {

    }

    public static int kConcatenationMaxSum(int[] nums, int k) {
        if (k == 1) {
            return kadane(nums);
        }
        long mod = ((long) (1e9) + 7);
        long ans = 0;
        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        int len = nums.length;
        int[] dbl = new int[len * 2];
        for (int i = 0; i < dbl.length; i++) {
            dbl[i] = nums[i % len];
        }
        if (sum < 0) {
            ans = kadane(dbl);
        } else {
            ans = (((k - 2) % mod * sum % mod) % mod + (kadane(dbl))) % mod;
        }
        if (ans < 0) {
            return 0;
        }
        return (int) ans;
    }

    public static int kadane(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int csum = 0;
        for (int ele : nums) {
            csum += ele;
            if (ans < csum) {
                ans = csum;
            }
            if (csum < 0) {
                csum = 0;
            }
        }
        return ans;
    }

    // MAXIMU SUM SUBARRAY WITH ONE DELETION
    public static int maximumSum(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr[0];
        }
        int ans = Integer.MIN_VALUE;
        int[] lkad = new int[len];
        lkad[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lkad[i] = Math.max(arr[i], lkad[i - 1] + arr[i]);
        }
        int[] rkad = new int[len];
        rkad[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rkad[i] = Math.max(arr[i], rkad[i + 1] + arr[i]);
        }
        for (int i = 1; i < len - 1; i++) {
            int lk = lkad[i - 1];
            int rk = rkad[i + 1];
            ans = Math.max(ans, lk);
            ans = Math.max(ans, rk);
            ans = Math.max(ans, lk + rk);
        }
        ans = Math.max(ans, lkad[len - 2]);
        ans = Math.max(ans, rkad[1]);
        ans = Math.max(ans, rkad[0]);
        return ans;
    }

    public int maxSumTwoNoOverlap(int[] nums, int n, int m) {
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        int mlen = nums[m - 1];
        int nlen = nums[n - 1];
        ans = nums[m + n - 1];
        for (int i = m + n; i < nums.length; i++) {
            mlen = Math.max(mlen, nums[i - n] - nums[i - m - n]);
            ans = Math.max(ans, nums[i] - nums[i - n] + mlen);
            nlen = Math.max(nlen, nums[i - m] - nums[i - m - n]);
            ans = Math.max(ans, nums[i] - nums[i - m] + nlen);
        }
        return ans;
    }
    
}