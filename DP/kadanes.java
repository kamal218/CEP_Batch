import java.util.PriorityQueue;

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

    // MAX SUM OF 3 NON OVERLAPPING

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[3];
        int[] ps = new int[len];
        ps[0] = nums[0];
        for (int i = 1; i < len; i++) {
            ps[i] = ps[i - 1] + nums[i];
        }

        int[] left = new int[len];
        int sum = ps[k - 1];
        for (int i = k; i <= len - 2 * k - 1; i++) {// need to change
            sum += nums[i] - nums[i - k];
            int st = left[i - 1];
            int prev = ps[st + k - 1] - (st == 0 ? 0 : ps[st - 1]);
            if (sum > prev) {
                left[i] = i - k + 1;
            } else {
                left[i] = left[i - 1];
            }
        }

        int[] right = new int[len];
        right[len - k] = len - k;
        sum = ps[len - 1] - ps[len - k - 1];
        for (int i = len - k - 1; i >= k; i--) {
            sum += nums[i] - nums[i + k];
            int st = right[i + 1];
            int prev = ps[st + k - 1] - ps[st - 1];
            if (sum >= prev) {
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }
        int anssum = Integer.MIN_VALUE;
        for (int i = k; i <= len - (2 * k); i++) {
            sum = ps[i + k - 1] - ps[i - 1];
            int lst = left[i - 1];
            int rst = right[i + k];
            int lsum = ps[lst + k - 1] - (lst == 0 ? 0 : ps[lst - 1]);
            int rsum = ps[rst + k - 1] - ps[rst - 1];
            int totsum = sum + lsum + rsum;
            if (totsum > anssum) {
                ans[0] = lst;
                ans[1] = i;
                ans[2] = rst;
                anssum = totsum;
            }
        }

        return ans;
    }

    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int two = 0;
        int three = 0;
        int five = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int twoval = 2 * dp[two];
            int threeval = 3 * dp[three];
            int fiveval = 5 * dp[five];
            int min = Math.min(twoval, Math.min(threeval, fiveval));
            dp[i] = min;
            if (min == threeval) {
                three++;
            }
            if (min == twoval) {
                two++;
            }
            if (min == fiveval) {
                five++;
            }

        }
        return dp[n - 1];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] ptr = new int[len];
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < len; j++) {
                min = Math.min(min, primes[j] * dp[ptr[j]]);
            }

            dp[i] = min;

            for (int j = 0; j < len; j++) {
                if (primes[j] * dp[ptr[j]] == min) {
                    ptr[j]++;
                }
            }

        }
        return dp[n - 1];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] dp = new int[n];
        dp[0] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] * dp[a[1]] - b[0] * dp[b[1]]));// int[]->{primes[i],ptr[i]}
        for (int i = 0; i < len; i++) {
            pq.add(new int[] { primes[i], 0 });
        }
        for (int i = 1; i < dp.length; i++) {
            int[] min = pq.poll();// primes[i],ptr(logn)
            int val = min[0] * dp[min[1]];
            pq.add(new int[] { min[0], min[1] + 1 });
            if (val == dp[i - 1]) {
                i--;
                continue;
            }
            dp[i] = val;
        }
        return dp[n - 1];
    }
}