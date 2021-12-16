import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = { -1, -1, -1, 0, 1, 2, 2 };
        List<List<Integer>> ans = threeSum(nums);
        System.out.println(ans);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
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

    public static void twoSum(int[] nums, int tar, int st, List<List<Integer>> ans) {
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

}