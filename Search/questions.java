import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class questions {
    public static void main(String[] args) {

    }

    // search in rotated sorted array without duplicates (O(logn)+O(1))
    public static int search(int[] nums, int target) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int mid = ei - (ei - si) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[si] < nums[mid]) {// left is sorted
                if (target >= nums[si] && target < nums[mid]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else {// right is sorted
                if (target > nums[mid] && target <= nums[ei]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }
        return -1;
    }

    // search in rotated sorted array with duplicates (O(logn)+O(1))
    public static int search(int[] nums, int target) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            if (nums[si] == target) {
                return si;
            }
            int mid = ei - (ei - si) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[si] < nums[mid]) {// left is sorted
                if (target >= nums[si] && target < nums[mid]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if (nums[mid] < nums[ei]) {// right is sorted
                if (target > nums[mid] && target <= nums[ei]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            } else {
                si++;
            }
        }
        return -1;
    }

    // MIN IN ROTATED SORTED ARRAY
    public int findMin(int[] nums) {
        int si = 0;
        int ei = nums.length - 1;
        int last = nums[nums.length - 1];
        while (si < ei) {
            int mid = (si + ei) / 2;

            if (nums[mid] < last) {
                ei = mid;
            } else {
                si = mid + 1;
            }
        }
        return nums[si];
    }

    // MIN IN ROTATED SORTED ARRAY 2
    public int findMin(int[] nums) {
        int si = 0;
        int ei = nums.length - 1;
        int last = nums[nums.length - 1];
        while (si < ei) {
            int mid = (si + ei) / 2;

            if (nums[mid] < nums[ei]) {
                ei = mid;
            } else if (nums[mid] > nums[ei]) {
                si = mid + 1;
            } else {
                ei--;
            }
        }
        return nums[si];
    }
    // 2 SUM 1

    // nlogn+n

    public int[] twoSum(int[] nums, int target) {
        int[][] mix = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            mix[i][0] = nums[i];
            mix[i][1] = i;
        }
        Arrays.sort(mix, (a, b) -> (a[0] - b[0]));
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int sum = mix[si][0] + mix[ei][0];
            if (sum == target) {
                return new int[] { mix[si][1], mix[ei][1] };
            } else if (sum < target) {
                si++;
            } else {
                ei--;
            }
        }
        return null;
    }

    // using hashmap

    // n+n
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            if (map.containsKey(target - ele)) {
                return new int[] { map.get(target - ele), i };
            }
            map.put(ele, i);
        }
        return null;
    }

    // 2 SUM CLOSEST
    // maximize
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int si = 0;
        int ei = nums.length - 1;
        int ans = -1;
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (sum < k) {
                ans = Math.max(ans, nums[si] + nums[ei]);
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    // count
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int si = 0;
        int ei = nums.length - 1;
        int ans = 0;
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (sum < k) {
                ans += (ei - si);
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    // 3 SUM

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int tar = 0;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            twoSum(nums, tar - nums[i], i, ans);
        }
        return ans;
    }

    public void twoSum(int[] nums, int tar, int st, List<List<Integer>> ans) {
        int ei = nums.length - 1;
        int si = st + 1;
        while (si < ei) {
            if (ei < nums.length - 1 && nums[ei] == nums[ei + 1]) {
                ei--;
                continue;
            }
            if (si > st + 1 && nums[si] == nums[si - 1]) {
                si++;
                continue;
            }
            int sum = nums[si] + nums[ei];
            if (sum == tar) {
                ans.add(Arrays.asList(nums[st], nums[si], nums[ei]));
                ei--;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
    }

    // 4 SUM
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            threeSumforFourSum(nums, target - nums[i], i, ans);
        }
        return ans;
    }

    public void threeSumforFourSum(int[] nums, int tar, int st, List<List<Integer>> ans) {
        for (int i = st + 1; i < nums.length - 2; i++) {
            if (i > st + 1 && nums[i - 1] == nums[i]) {
                continue;
            }
            twoSumforFourSum(nums, tar - nums[i], i, ans, nums[st]);
        }
    }

    public void twoSumforFourSum(int[] nums, int tar, int st, List<List<Integer>> ans, int first) {
        int ei = nums.length - 1;
        int si = st + 1;
        while (si < ei) {
            if (ei < nums.length - 1 && nums[ei] == nums[ei + 1]) {
                ei--;
                continue;
            }
            if (si > st + 1 && nums[si] == nums[si - 1]) {
                si++;
                continue;
            }
            int sum = nums[si] + nums[ei];
            if (sum == tar) {
                ans.add(Arrays.asList(first, nums[st], nums[si], nums[ei]));
                ei--;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
    }

    // 3 SUM LESS THEN K (3 SUM SMALLER)
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            ans += twoSumLessThanK(nums, target - nums[i], i);
        }
        return ans;
    }

    public int twoSumLessThanK(int[] nums, int k, int st) {
        int si = st + 1;
        int ei = nums.length - 1;
        int ans = 0;
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (sum < k) {
                ans += (ei - si);
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    // 3 SUM CLOSEST

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int sum = nums[i] + twoSumClosest(nums, target - nums[i], i);
            if (ans == Integer.MIN_VALUE || Math.abs(sum - target) < Math.abs(ans - target)) {
                ans = sum;
            }
        }
        return ans;
    }

    public int twoSumClosest(int[] nums, int target, int st) {
        int ans = Integer.MIN_VALUE;
        int si = st + 1;
        int ei = nums.length - 1;
        while (si < ei) {
            int sum = nums[si] + nums[ei];
            if (ans == Integer.MIN_VALUE || Math.abs(sum - target) < Math.abs(ans - target)) {
                ans = sum;
            }
            if (sum == target) {
                return sum;
            } else if (sum < target) {
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    // k sum

    public void ksum(int[] nums, int k, int st, int tar, List<List<Integer>> ans, List<Integer> sans) {
        if (k == 2) {// 2 sum
            twoSum(nums, st, tar, ans, sans);
            return;
        }
        for (int i = st; i < nums.length; i++) {
            if (i > st && nums[i] == nums[i - 1]) {
                continue;
            }
            sans.add(nums[i]);
            ksum(nums, k - 1, i + 1, tar - nums[i], ans, sans);
            sans.remove(sans.size() - 1);
        }
    }

    public void twoSum(int[] nums, int st, int tar, List<List<Integer>> ans, List<Integer> sans) {
        int si = st;
        int ei = nums.length - 1;
        while (si < ei) {
            if (ei < nums.length - 1 && nums[ei] == nums[ei + 1]) {
                ei--;
                continue;
            }
            if (si > st && nums[si] == nums[si - 1]) {
                si++;
                continue;
            }
            int sum = nums[si] + nums[ei];
            if (sum == tar) {
                sans.add(nums[si]);
                sans.add(nums[ei]);
                ans.add(new ArrayList<>(sans));
                sans.remove(sans.size() - 1);
                sans.remove(sans.size() - 1);
                si++;
            } else if (sum < tar) {
                si++;
            } else {
                ei--;
            }
        }
    }

    // FIRST MISSING POSITIVE
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public void swap(int[] nums, int i, int j) {
        nums[j] = ((nums[i] + nums[j]) - (nums[i] = nums[j]));
    }

    // KTYH ISSING IN O(N)
    public int findKthPositive(int[] arr, int k) {
        int val = 1;
        int i = 0;
        while (i < arr.length && k != 0) {
            if (arr[i] == val) {
                i++;
                val++;
            } else {
                k--;
                val++;
            }
        }
        if (k == 0) {
            return val - 1;
        }
        return val + k - 1;
    }

    // missing in sorted o(logn)
    public int missingElement(int[] nums, int k) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            int miss = countMissing(nums, mid);
            if (miss >= k) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return nums[ei] + (k - countMissing(nums, ei));
    }

    public int countMissing(int[] nums, int i) {
        return nums[i] - nums[0] - i;
    }

    // MISSING AND REPEATING
    public int[] missingAndRep(int[] nums) {
        int miss = -1;
        int rep = -1;
        for (int i = 0; i < nums.length;) {
            int cl = nums[i] - 1;// correct location
            if (nums[cl] == cl + 1) {
                if (cl == i) {
                    i++;
                } else {
                    rep = nums[i];
                    miss = i + 1;
                    i++;
                }
            } else {
                swap(nums, i, cl);
            }
        }
        return new int[] { rep, miss };
    }

    public void swap(int[] nums, int i, int j) {
        nums[j] = ((nums[i] + nums[j]) - (nums[i] = nums[j]));
    }

    // K CLOSEST
    public List<Integer> findClosestElements(int[] nums, int k, int target) {
        int pl = perfectLocation(nums, target);
        // System.out.println(pl);
        int l = pl - 1;
        int r = pl;
        List<Integer> ans = new ArrayList<>();
        while (k > 0) {
            int lval = l == -1 ? Integer.MAX_VALUE : target - nums[l];
            int rval = r == nums.length ? Integer.MAX_VALUE : nums[r] - target;
            if (lval <= rval) {
                ans.add(nums[l]);
                l--;
            } else {
                ans.add(nums[r]);
                r++;
            }
            k--;
        }
        Collections.sort(ans);
        return ans;
    }

    public int perfectLocation(int[] nums, int tar) {
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            if (nums[mid] == tar) {
                return mid;
            } else if (nums[mid] < tar) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }
    // MAJORITY ELEMENT

    public int majorityElement(int[] nums) {
        int freq = 0;
        long val = Long.MIN_VALUE;
        for (int ele : nums) {
            if (ele == val) {
                freq++;
            } else if (freq == 0) {
                freq = 1;
                val = ele;
            } else {
                freq--;
            }
        }
        return (int) val;
    }

    // MAJORITY ELEMENT 2
    public List<Integer> majorityElement(int[] nums) {
        int f1 = 0;
        int val1 = Integer.MIN_VALUE;
        int f2 = 0;
        int val2 = Integer.MIN_VALUE;
        for (int ele : nums) {
            if (ele == val1) {
                f1++;
            } else if (ele == val2) {
                f2++;
            } else if (f1 == 0) {
                val1 = ele;
                f1 = 1;
            } else if (f2 == 0) {
                val2 = ele;
                f2 = 1;
            } else {
                f1--;
                f2--;
            }
        }
        // val1 and val2
        int v1f = 0;
        int v2f = 0;
        for (int ele : nums) {
            if (ele == val1) {
                v1f++;
            }
            if (ele == val2) {
                v2f++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (v1f > nums.length / 3) {
            ans.add(val1);
        }
        if (v2f > nums.length / 3) {
            ans.add(val2);
        }
        return ans;
    }

    // SEARCH IN SORTED MATRIX 1
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        int si = 0;
        int ei = r * c - 1;
        while (si <= ei) {
            int mid = (si + ei) / 2;
            int val = matrix[mid / c][mid % c];
            if (val == target) {
                return true;
            } else if (val < target) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return false;
    }

    // SERACH IN 2D SORTED 2

    public boolean searchMatrix(int[][] matrix, int target) {
        // top right
        int i = matrix[0].length - 1;
        int j = 0;
        while (i >= 0 && j < matrix.length) {
            int val = matrix[j][i];
            if (val == target) {
                return true;
            } else if (val < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    // CAPACITY TO SHIP WITHIN D DAYS

    public int shipWithinDays(int[] weights, int days) {
        int lo = 0;
        int hi = 0;
        for (int ele : weights) {
            lo = Math.max(ele, lo);
            hi += ele;
        }
        int ans = hi;
        while (lo <= hi) {
            int cap = (lo + (hi - lo) / 2);
            int neededdays = countdays(weights, cap);
            if (neededdays <= days) {
                ans = cap;
                hi = cap - 1;
            } else {
                lo = cap + 1;
            }
        }
        return ans;
    }

    public int countdays(int[] weights, int cap) {
        int load = 0;
        int days = 1;
        for (int size : weights) {
            load += size;
            if (load > cap) {
                days++;
                load = size;
            }
        }
        return days;
    }

    // KOKO EATING BANANAS
    public int minEatingSpeed(int[] piles, int h) {
        int ans = 0;
        int lo = 1;
        int hi = 0;
        for (int ele : piles) {
            hi = Math.max(hi, ele);
        }
        while (lo <= hi) {
            int cap = (lo + (hi - lo) / 2);
            int neededhrs = countHours(piles, cap);
            if (neededhrs <= h) {
                ans = cap;
                hi = cap - 1;
            } else {
                lo = cap + 1;
            }
        }
        return ans;
    }

    public int countHours(int[] piles, int cap) {
        int hrs = 0;
        for (int ele : piles) {
            // hrs += (ele / cap);
            // hrs += (ele % cap == 0 ? 0 : 1);
            hrs += Math.ceil(ele * 1.0 / cap);
        }
        return hrs;
    }

    // MAGNETIC FORCE

    public int maxDistance(int[] pos, int m) {
        Arrays.sort(pos);
        int len = pos.length;
        int lo = 1;
        int hi = pos[len - 1] - pos[0];
        int ans = 0;
        while (lo <= hi) {
            int force = (lo + (hi - lo) / 2);
            int balls = countBalls(pos, force);
            if (balls >= m) {
                ans = force;
                lo = force + 1;
            } else {
                hi = force - 1;
            }
        }
        return ans;
    }

    public int countBalls(int[] pos, int maxforce) {
        int balls = 1;
        int j = pos[0];
        for (int i : pos) {
            if (i - j >= maxforce) {
                balls++;
                j = i;
            }
        }
        return balls;
    }

    // SQUARE ROOT
    public int mySqrt(int x) {
        long lo = 0;
        long hi = x;

        while (lo <= hi) {
            long mid = (lo + (hi - lo) / 2);
            long sqr = mid * mid;
            if (sqr == x) {
                return (int) mid;
            } else if (sqr < x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) hi;
    }

    // PEAK ELEMENT

    public int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + (hi - lo) / 2);
            long l = mid == 0 ? Long.MIN_VALUE : nums[mid - 1];
            long r = mid == nums.length - 1 ? Long.MIN_VALUE : nums[mid + 1];
            if (nums[mid] > l && nums[mid] > r) {
                return mid;
            } else if (nums[mid] < r) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // KTH SMALLEST PRIME FRACTION

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double lo = 0.0;
        double hi = 1;
        int[] ans = new int[2];
        while (true) {
            double mid = (lo + (hi - lo) / 2);
            int count = 0;
            int j = 1;
            double maxfrac = 0.0;
            // double cfrac = 0.0;
            for (int i = 0; i < arr.length; i++) {
                while (j < arr.length && (arr[i] * 1.0) > (mid * arr[j])) {
                    j++;
                }
                if (j == arr.length) {
                    break;
                }
                // arr[i] num
                // arr[j] den
                double cfrac = (double) (arr[i]) / (double) (arr[j]);
                count += (arr.length - j);
                if (maxfrac < cfrac) {
                    ans[0] = arr[i];
                    ans[1] = arr[j];
                    maxfrac = cfrac;
                }
            }
            if (count == k) {
                return ans;
            } else if (count < k) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
    }

}