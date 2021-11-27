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
        // int[] arr = { 0, 1, 0, 2, 0, 1, 0 };
        // int ans = count012Same(arr);
        int[] arr = { 2, 4, 6, 8, 10 };
        boolean ans = checkForAP(arr);
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

    // NUMBER OF RABITS

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

    // LONGEST CONSECUTIVE SEQUENCESONGLE TRAVEL

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
            ans = Math.max(ans, map.get(ele));
        }
        return ans;
    }

    // MORNING ASSEMBLY

    public static int morningAssembly(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int lcs = 0;
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele - 1, 0) + 1);
            lcs = Math.max(lcs, map.get(ele));
        }
        return arr.length - lcs;
    }

    // BRICK WALL
    public static int leastBricks(List<List<Integer>> wall) {
        int brakes = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < wall.size(); i++) {
            int cbreak = 0;
            for (int j = 0; j < wall.get(i).size() - 1; j++) {
                cbreak += wall.get(i).get(j);
                map.put(cbreak, map.getOrDefault(cbreak, 0) + 1);
            }
        }
        for (int key : map.keySet()) {
            brakes = Math.max(brakes, map.get(key));
        }
        return wall.size() - brakes;
    }

    // GRID ILLUMINATION
    HashMap<Integer, Integer> bulb = new HashMap<>();
    HashMap<Integer, Integer> row = new HashMap<>();
    HashMap<Integer, Integer> col = new HashMap<>();
    HashMap<Integer, Integer> diag1 = new HashMap<>();
    HashMap<Integer, Integer> diag2 = new HashMap<>();

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        for (int i = 0; i < lamps.length; i++) {
            int r = lamps[i][0];
            int c = lamps[i][1];
            int bidx = (r * N) + c;
            if (bulb.getOrDefault(bidx, 0) == 0) {
                bulb.put(bidx, 1);
                row.put(r, row.getOrDefault(r, 0) + 1);
                col.put(c, col.getOrDefault(c, 0) + 1);
                diag1.put(r + c, diag1.getOrDefault(r + c, 0) + 1);
                diag2.put(r - c, diag2.getOrDefault(r - c, 0) + 1);
            }
        }
        int[] ans = new int[queries.length];
        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0];
            int c = queries[i][1];
            int idx = (r * N) + c;
            if (row.getOrDefault(r, 0) != 0 || col.getOrDefault(c, 0) != 0 || diag1.getOrDefault(r + c, 0) != 0
                    || diag2.getOrDefault(r - c, 0) != 0) {
                ans[i] = 1; // Update answer
                // update grid
                if (bulb.getOrDefault(idx, 0) != 0) { // own check
                    updateGrid(N, r, c, idx);
                }
                for (int k = 0; k < dir.length; k++) { // adjacent check
                    int nr = r + dir[k][0];
                    int nc = c + dir[k][1];
                    int nidx = (nr * N) + nc;
                    updateGrid(N, nr, nc, nidx);
                }
            }
        }
        return ans;
    }

    public void updateGrid(int N, int nr, int nc, int nidx) {
        if (nr >= 0 && nc >= 0 && nr < N && nc < N && bulb.getOrDefault(nidx, 0) != 0) {
            bulb.put(nidx, bulb.get(nidx) - 1);
            row.put(nr, row.get(nr) - 1);
            col.put(nc, col.get(nc) - 1);
            diag1.put(nr + nc, diag1.get(nr + nc) - 1);
            diag2.put(nr - nc, diag2.get(nr - nc) - 1);
        }
    }

    // ISOMORPHIC STRING
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        int i = 0;
        while (i < s.length()) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (map.containsKey(ch1)) {
                if (map.get(ch1) != ch2) {
                    return false;
                }
            } else {
                if (set.contains(ch2)) {
                    return false;
                }
                map.put(ch1, ch2);
                set.add(ch2);
            }
            i++;
        }
        return true;
    }

    // ARRAY OF DOUBLED PAIRS
    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        Arrays.sort(arr);
        for (int ele : arr) {
            if (map.getOrDefault(ele, 0) == 0) {
                continue;
            }
            int cv = ele < 0 ? (ele / 2) : ele * 2;
            if (ele < 0 && ele % 2 != 0) {
                return false;
            }
            if (map.getOrDefault(cv, 0) > 0) {
                map.put(ele, map.get(ele) - 1);
                map.put(cv, map.get(cv) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    // CHECK FOR AP SEQUENNCE

    public static boolean checkForAP(int[] arr) {
        int a = Integer.MAX_VALUE;
        int an = Integer.MIN_VALUE;
        for (int ele : arr) {
            an = Math.max(an, ele);
            a = Math.min(a, ele);
        }
        int n = arr.length;
        int d = (an - a) / (n - 1);
        if ((an - a) % (n - 1) != 0) {
            return false;
        }
        if (a == an) {// d==0
            return true;
        }
        int i = 0;
        while (i < n) {
            int idx = ((arr[i] - a) / (d));
            if ((arr[i] - a) % (d) != 0) {
                return false;
            }
            if (arr[idx] == arr[i]) {
                if (i == idx) {
                    i++;
                } else {
                    return false;
                }
            } else {
                swap(arr, i, idx);
            }
        }
        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        arr[j] = ((arr[i] + arr[j]) - (arr[i] = arr[j]));
    }

    // NON COINCIDING POINTS
    public int nonCoincingPairs(int[][] points) {
        int ans = 0;
        HashMap<Integer, Integer> x = new HashMap<>();
        HashMap<Integer, Integer> y = new HashMap<>();
        HashMap<String, Integer> xy = new HashMap<>();
        for (int[] ar : points) {
            int xc = x.getOrDefault(ar[0], 0);
            int yc = y.getOrDefault(ar[1], 0);
            int xyc = xy.getOrDefault(ar[0] + "#" + ar[1], 0);
            ans += (xc + yc - 2 * xyc);
            x.put(ar[0], x.getOrDefault(ar[0], 0) + 1);
            y.put(ar[1], y.getOrDefault(ar[1], 0) + 1);
            xy.put(ar[0] + "#" + ar[1], xy.getOrDefault(ar[0] + "#" + ar[1], 0) + 1);

        }
        return ans;
    }

    // INSERT DELETE GET RANDOM
    class RandomizedCollection {
        HashMap<Integer, HashSet<Integer>> ds = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        public RandomizedCollection() {

        }

        public boolean insert(int val) {
            boolean res = false;
            if (!ds.containsKey(val) || ds.get(val).size() == 0) {
                res = true;
                ds.put(val, new HashSet<>());
            }
            list.add(val);
            ds.get(val).add(list.size() - 1);
            return res;
        }

        public boolean remove(int val) {
            Set<Integer> set = ds.get(val);
            if (set == null || set.size() == 0) {
                return false;
            }
            Iterator it = set.iterator();
            int idx = it.next();
            list.set(idx, list.get(list.size() - 1));
            ds.get(list.get(idx)).add(idx);
            list.remove(list.size() - 1);
            ds.get(list.get(idx)).remove(list.size());
            ds.get(val).remove(idx);
        }

        public int getRandom() {
            return list.get((int) (Math.floor(Math.random() * list.size())));
        }
    }

    // /CHECK FOR SAME freq
    public boolean sameFreq(String str) {
        int[] map = new int[26];
        for (char ch : str.toCharArray()) {
            map[ch - 'a']++;
        }
        Arrays.sort(map);
        int i = 24;
        int d = 0;
        while (i >= 0) {
            int diff = map[i + 1] - map[i];
            if (diff > 1) {
                if (map[i] != 0 && d > 0) {
                    return false;
                }
                if (map[i] == 1 && (i == 0 || map[i - 1] == 0)) {
                    return true;
                }
                return false;
            } else if (diff == 1) {
                if (d > 0) {
                    return false;
                } else {
                    d++;
                    i--;
                }
            } else {
                i--;
            }
        }
        return true;
    }

    // LINE REFLECTIOn
    public boolean isReflected(int[][] points) {
        HashMap<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] ar : points) {
            String str = ar[0] + "#" + ar[1];
            min = Math.min(min, ar[0]);
            max = Math.max(max, ar[0]);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int sum = min + max;
        for (int[] ar : points) {
            int x1 = ar[0];
            int y1 = ar[1];
            String op = x1 + "#" + y1;
            int x2 = sum - x1;
            int y2 = y1;
            String mir = x2 + "#" + y2;
            if (!map.containsKey(mir)) {
                return false;
            }
            map.put(op, map.get(op) - 1);
            map.put(mir, map.get(mir) - 1);
        }
        return true;
    }

    // ANAGRAM GROUPING
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] fmap = new int[26];
            for (char ch : str.toCharArray()) {
                fmap[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fmap.length; i++) {
                sb.append(fmap[i]);
                sb.append("#");
            }
            String ss = sb.toString();
            if (!map.containsKey(ss)) {
                map.put(ss, new ArrayList<>());
            }
            map.get(ss).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

}