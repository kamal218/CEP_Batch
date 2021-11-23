import java.util.*;

public class Questions {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // CLASS 01
        // int[] arr = { 10, 2, -2, 2, 8, 2, 4, 4 };
        // List<List<Integer>> ans = subarraySum01(arr, 10);
        // List<Integer> ans = subarraySum02(arr, 10);
        // int[] arr = { 1, 2, 3, 4, 5, 6 };
        // int ans = subarraySumDIvvByK(arr, 2);
        int[] arr = { 0, 1, 0, 2, 0, 1, 0 };
        int ans = count012Same(arr);
        System.out.println(ans);
    }

    // count
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();// psum-> count
        int csum = 0;
        map.put(0, 1);
        int ans = 0;
        for (int ele : nums) {
            csum += ele;
            ans += map.getOrDefault(csum - k, 0);
            map.put(csum, map.getOrDefault(csum, 0) + 1);
        }
        return ans;
    }

    // ALL INDICES
    public static List<List<Integer>> subarraySum01(int[] nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();// psum-> indices
        int csum = 0;
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            List<Integer> list = map.get(csum - k);
            if (list != null) {
                for (int st : list) {
                    ans.add(Arrays.asList(st + 1, i));
                }
            }
            if (!map.containsKey(csum)) {
                map.put(csum, new ArrayList<>());
            }
            map.get(csum).add(i);
        }
        return ans;
    }

    // LONGEST LENGTH
    public static List<Integer> subarraySum02(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();// psum-> first index
        int csum = 0;
        map.put(0, -1);
        List<Integer> ans = new ArrayList<>();
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            if (map.containsKey(csum - k) && i - map.get(csum - k) > len) {
                len = i - map.get(csum - k);
                ans = Arrays.asList(map.get(csum - k) + 1, i);
            }
            if (!map.containsKey(csum)) {
                map.put(csum, i);
            }
        }
        return ans;
    }

    // SUBARRY WITH SUM DIVISIBLE BY k
    public static int subarraySumDIvvByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int csum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            csum += nums[i];
            int r = (csum % k);
            if (r < 0) {
                r += k;
            }
            ans += map.getOrDefault(r, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return ans;
    }

    // COUNT 0 1 2
    public static int count012Same(int[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        int ans = 0;
        int z = 0;
        int o = 0;
        int t = 0;
        map.put(0 + "#" + 0, 1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                z++;
            } else if (arr[i] == 1) {
                o++;
            } else {
                t++;
            }
            String str = (z - o) + "#" + (o - t);
            ans += map.getOrDefault(str, 0);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        return ans;
    }

    // NUMBER F RABITS

    public static int numRabbits(int[] arr) {
        int[] map = new int[1001];
        int ans = 0;
        for (int ele : arr) {
            map[ele]++;
        }
        for (int i = 0; i < map.length; i++) {
            int gsize = i + 1;
            int freq = map[i];
            int count = (freq / gsize);
            count = (freq % gsize == 0) ? count : count + 1;
            ans = count * gsize;
        }
        return ans;
    }

    // LONGEST CONSECUTIVE SEQUENCE

    public static int longestConsecutive(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums) {
            if (map.containsKey(ele)) {
                continue;
            }
            Integer left = map.get(ele - 1);
            Integer right = map.get(ele + 1);
            if (left != null && right != null) {
                int cval = left + right + 1;
                map.put(ele, cval);
                map.put(ele + right, cval);
                map.put(ele - left, cval);
            } else if (left != null) {
                int cval = left + 1;
                map.put(ele, cval);
                map.put(ele - left, cval);
            } else if (right != null) {
                int cval = right + 1;
                map.put(ele, cval);
                map.put(ele + right, cval);
            } else {
                map.put(ele, 1);
            }
            ans=Math.max(ans,map.get(ele));
        }
        return ans;
    }
}