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
}