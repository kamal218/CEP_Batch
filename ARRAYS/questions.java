import java.util.LinkedList;

public class questions {
    // Long pressed name
    public boolean isLongPressedName(String name, String typed) {
        int p1 = 0;
        int p2 = 0;
        while (p1 < name.length() && p2 < typed.length()) {
            if (name.charAt(p1) == typed.charAt(p2)) {
                p1++;
                p2++;
            } else {
                if (p2 == 0 || typed.charAt(p2) != typed.charAt(p2 - 1)) {
                    return false;
                }
                while (p2 < typed.length() - 1 && typed.charAt(p2) == typed.charAt(p2 + 1)) {
                    p2++;
                }
                p2++;
            }
        }
        if (p2 == typed.length() && p1 != name.length()) {
            return false;
        }
        if (p1 == name.length()) {
            while (p2 < typed.length() && typed.charAt(p2 - 1) == typed.charAt(p2)) {
                p2++;
            }
        }
        return p2 == typed.length();
    }

    // CONTAINER WITH MOST WATER

    public int maxArea(int[] height) {
        int ans = 0;
        int si = 0;
        int ei = height.length - 1;
        while (si < ei) {
            ans = Math.max(ans, Math.min(height[si], height[ei]) * (ei - si));
            if (height[si] < height[ei]) {
                si++;
            } else {
                ei--;
            }
        }
        return ans;
    }

    // SQUARE
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int p1 = 0;
        int p2 = len - 1;
        int p = len - 1;
        while (p1 <= p2) {
            int s1 = nums[p1] * nums[p1];
            int s2 = nums[p2] * nums[p2];
            if (s2 >= s1) {
                ans[p] = s2;
                p2--;
            } else {
                ans[p] = s1;
                p1++;
            }
            p--;
        }
        return ans;
    }

    // NEXT PERMUTATION

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i == -1) {
            reverse(nums, 0, len - 1);
            return;
        }

        int j = len - 1;
        while (j > i && nums[j] <= nums[i]) {
            j--;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, len - 1);
    }

    public void swap(int[] nums, int i, int j) {
        nums[j] = ((nums[i] + nums[j]) - (nums[i] = nums[j]));
    }

    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    // PREV PERMUTATION WITH ONE SWAP

    public int[] prevPermOpt1(int[] nums) {
        int len = nums.length;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] <= nums[i + 1]) {
            i--;
        }
        if (i == -1) {
            return nums;
        }

        int j = len - 1;
        while (j > i && (nums[j] >= nums[i] || nums[j - 1] == nums[j])) {
            j--;
        }
        swap(nums, i, j);
        return nums;
    }

    // MAXIMUM SWAP

    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] digmap = new int[10];
        for (int i = 0; i < arr.length; i++) {
            digmap[arr[i] - '0'] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int dig = arr[i] - '0';
            for (int j = 9; j > dig; j--) {
                if (digmap[j] > i) {
                    int idx = digmap[j];
                    char t = arr[i];
                    arr[i] = arr[idx];
                    arr[idx] = t;
                    return Integer.parseInt(String.valueOf(arr));
                }
            }
        }
        return num;
    }

    // MAX CHUNK 1
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int maxchunk = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i > maxchunk) {
                ans++;
                maxchunk = arr[i];
            } else {
                maxchunk = Math.max(maxchunk, arr[i]);
            }
        }
        return ans;
    }

    // MAX CHUNK 2
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] min = new int[len];
        min[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            min[i] = Math.min(arr[i], min[i + 1]);
        }
        int[] max = new int[len];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(arr[i], max[i - 1]);
        }
        int ans = 1;
        for (int i = 0; i < len - 1; i++) {
            if (max[i] <= min[i + 1]) {
                ans++;
            }
        }
        return ans;
    }

    // WIGGLE SORT
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0) {// smaller
                if (nums[i - 1] < nums[i]) {
                    swap(nums, i, i - 1);
                }
            } else {// greater
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }

    // public void swap(int[] nums, int i, int j) {
    // nums[j] = ((nums[i] + nums[j]) - (nums[i] = nums[j]));
    // }

    // BOUNDEX MAX
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int ans = 0;
        int pcount = 0;
        int st = 0;
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            if (ele <= right && ele >= left) {
                ans += (i - st + 1);
                pcount = (i - st + 1);
            } else if (ele > right) {
                pcount = 0;
                st = i + 1;
            } else {
                ans += pcount;
            }
        }
        return ans;
    }

    // MAX VALUE OF ARR[I]*I

    public int maxvalue(int[] arr) {
        int ans = 0;
        int csum = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            csum += (i * arr[i]);
            sum = arr[i];
        }
        ans = csum;
        for (int i = 1; i < arr.length; i++) {
            int gain = (arr[i - 1] * arr.length - 1);
            int lose = (sum - arr[i - 1]);
            csum = csum + (gain) - (lose);
            if (csum > ans) {
                ans = csum;
            }
        }
        return ans;
    }

    // PRODUCT OF ARRAY EXCEPT ITSELF

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int right = 1;

        for (int i = len - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right = right * nums[i];
        }
        return ans;
    }

    // MAX DISTANCE FROM 1S

    public int maxDistToClosest(int[] seats) {
        int st = 0;
        while (seats[st] != 1) {
            st++;
        }
        int end = st + 1;
        int ans = st;
        while (end < seats.length) {
            if (seats[end] == 1) {
                ans = Math.max(ans, (end - st) / 2);
                st = end;
            }
            end++;
        }
        ans = Math.max(ans, seats.length - st - 1);
        return ans;
    }

    // RANGE QUERY
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] q : updates) {
            int st = q[0];
            int end = q[1];
            int val = q[2];
            ans[st] += val;
            if (end != length - 1) {
                ans[end + 1] -= val;
            }
        }
        for (int i = 1; i < length; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }

    // MAX RANGE QUERY

    public static int maxRangeQuery(int k, int[][] query) {
        int[] arr = new int[100001];
        for (int[] q : query) {
            int st = q[0];
            int end = q[1];
            arr[st]++;
            arr[end + 1]--;
        }
        int[] kc = new int[100001];// k count
        int[] kpoc = new int[100001];// k plus one count
        if (arr[0] == k)
            kc[0] = 1;
        if (arr[0] == k + 1)
            kpoc[0] = 1;
        // prefix sum array
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
            kc[i] = kc[i - 1];
            kpoc[i] = kpoc[i - 1];
            if (arr[i] == k) {
                kc[i]++;
            }
            if (arr[i] == k + 1) {
                kpoc[i]++;
            }
        }
        int totalk = kc[100000];
        int ans = 0;
        for (int[] q : query) {
            int st = q[0];
            int end = q[1];
            int gain = 0;
            int lose = 0;
            if (st == 0) {
                gain = kpoc[end];
                lose = kc[end];
            } else {
                gain = kpoc[end] - kpoc[st - 1];
                lose = kc[end] - kc[st - 1];
            }
            ans = Math.max(ans, totalk + gain - lose);
        }
        return ans;

    }

    // MIN BOATS

    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        int st = 0;
        Arrays.sort(people);
        int end = people.length - 1;
        while (st <= end) {
            if (people[st] + people[end] <= limit) {
                st++;
            }
            ans++;
            end--;
        }
        return ans;
    }

    // SMALLEST MULTIPLIER

    public String smallestmult(int n) {
        if (n == 1) {
            return "1";
        }
        long mult = 9;
        StringBuilder ans = new StringBuilder();
        while (n != 1 && mult != 1) {
            if (n % mult == 0) {
                ans.append(mult + "");
                n /= mult;
            } else {
                mult--;
            }
        }
        if (mult == 1) {
            return "-1";
        }
        return ans.reverse().toString();
    }

    // MIN MOVES TO MAKE EQUAL 1

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int ele : nums) {
            if (min > ele) {
                min = ele;
            }
        }
        int ans = 0;
        for (int ele : nums) {
            ans += ele - min;
        }
        return ans;
    }

    // MIN MOVES 2
    public int minMoves2(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length / 2; i++) {
            ans += (nums[nums.length - i - 1] - nums[i]);
        }
        return ans;
    }

    // CONSECUTIVE SUM

    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        for (int k = 1; k <= n; k++) {
            int rhs = (n - (k * (k + 1) / 2));
            if (rhs % k == 0) {
                ans++;
            }
            if (rhs < 0) {
                break;
            }
        }
        return ans;
    }

    // WITH K LIMIT

    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        int k_limit = (int) (Math.sqrt(2 * (long) n + 0.25) - 0.5);
        for (int k = 1; k <= k_limit; k++) {
            int rhs = (n - (k * (k + 1) / 2));
            if (rhs % k == 0) {
                ans++;
            }
        }
        return ans;
    }

    // MIN DOMINO ROTATION
    public int minDominoRotations(int[] t, int[] b) {
        int ans = Integer.MAX_VALUE;
        int l1v1 = minRotation(t, b, true, t[0]);// true-. firtst line amd false -> secod line
        int l1v2 = minRotation(t, b, true, b[0]);
        int l2v1 = minRotation(t, b, false, t[0]);
        int l2v2 = minRotation(t, b, false, b[0]);
        ans = Math.min(Math.min(Math.min(l1v1, l1v2), l2v1), l2v2);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int minRotation(int[] t, int[] b, boolean line, int val) {
        int swap = 0;
        for (int i = 0; i < t.length; i++) {
            if (line) {// same first line
                if (t[i] == val) {

                } else if (b[i] == val) {
                    swap++;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                if (b[i] == val) {

                } else if (t[i] == val) {
                    swap++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return swap;
    }

    // PUSH DOMINOES
    public String pushDominoes(String dominoes) {
        StringBuilder sb = new StringBuilder();
        sb.append("L");// left base case
        for (char ch : dominoes.toCharArray()) {
            sb.append(ch);
        }
        sb.append("R");// right base case
        int st = 0;
        int end = 1;
        while (end < sb.length()) {
            while (end < sb.length() && sb.charAt(end) == '.') {
                end++;
            }
            char l = sb.charAt(st);
            char r = sb.charAt(end);
            if (l == 'L' && r == 'L') {
                int k = end - 1;
                while (k > st) {
                    sb.setCharAt(k, 'L');
                    k--;
                }
            } else if (l == 'R' && r == 'R') {
                int k = end - 1;
                while (k > st) {
                    sb.setCharAt(k, 'R');
                    k--;
                }
            } else if (l == 'R' && r == 'L') {
                int p1 = st + 1;
                int p2 = end - 1;
                while (p1 < p2) {
                    sb.setCharAt(p1, 'R');
                    sb.setCharAt(p2, 'L');
                    p1++;
                    p2--;
                }
            }
            st = end;
            end++;
        }
        return sb.toString().substring(1, sb.length() - 1);
    }

    // MAX PRODUCT SUBARRAY

    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int cp = 1;
        for (int i = 0; i < nums.length; i++) {
            cp *= nums[i];
            if (ans < cp) {
                ans = cp;
            }
            if (cp == 0) {
                cp = 1;
            }
        }
        cp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            cp *= nums[i];
            if (ans < cp) {
                ans = cp;
            }
            if (cp == 0) {
                cp = 1;
            }
        }
        return ans;
    }

    // SUM OF SUBSEQ WIDTH
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long ans = 0;
        long mod = 1000000007;
        long[] pow = new long[nums.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }
        for (int i = 0; i < nums.length; i++) {
            ans = (ans + ((pow[i] - pow[nums.length - i - 1]) * nums[i]) % mod) % mod;
        }
        return (int) (ans % mod);
    }

    // SLIDING WINDOW
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        int pt = 0;
        for (int i = 0; i < nums.length; i++) {
            // exclusion (left)
            if (list.size() > 0 && list.getFirst() == i - k) {
                list.pollFirst();
            }
            // inclusion
            while (list.size() > 0 && nums[list.getLast()] < nums[i]) {
                list.pollLast();
            }
            list.add(i);
            if (i >= k - 1) {
                ans[pt] = nums[list.getFirst()];
                pt++;
            }
        }
        return ans;
    }

    // MIN ARROWS

    public int findMinArrowShots(int[][] arr) {
        int ans = 1;
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));
        int end = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] > end) {
                end = arr[i][1];
                ans++;
            }
        }
        return ans;
    }

    // ROTATING BOX

    public char[][] rotateTheBox(char[][] box) {
        int r = box.length;
        int c = box[0].length;
        char[][] ans = new char[c][r];
        int[] last = new int[r];// last occupied space for each row
        Arrays.fill(last, c);
        for (int j = c - 1; j >= 0; j--) {
            for (int i = 0; i < r; i++) {
                if (box[i][j] == '#') {// ball
                    int lidx = last[i] - 1;
                    ans[j][r - i - 1] = '.';
                    ans[lidx][r - i - 1] = '#';
                    last[i]--;
                } else if (box[i][j] == '*') {// obstacle
                    ans[j][r - i - 1] = '*';
                    last[i] = j;
                } else {// empty
                    ans[j][r - i - 1] = '.';
                    // do nothing
                }
            }
        }
        return ans;
    }

    // GAS STATION
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int diff = 0;
        int cg = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            diff += gas[i] - cost[i];
            cg += gas[i] - cost[i];
            if (cg < 0) {
                start = i + 1;
                cg = 0;
            }
        }
        return diff >= 0 ? start : -1;
    }

    // FLIP COLUMN TO MAKE ROWS SAME

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int ans = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < r; i++) {
            int cans = 1;
            for (int j = i + 1; j < r; j++) {
                int issame = sameRow(matrix, i, j);
                int isflip = flipRow(matrix, i, j);
                cans += issame + isflip;
            }
            if (ans < cans) {
                ans = cans;
            }
        }
        return ans;
    }

    public int sameRow(int[][] mat, int i, int j) {
        for (int pt = 0; pt < mat[0].length; pt++) {
            if (mat[i][pt] != mat[j][pt]) {
                return 0;
            }
        }
        return 1;
    }

    public int flipRow(int[][] mat, int i, int j) {
        for (int pt = 0; pt < mat[0].length; pt++) {
            if (mat[i][pt] == mat[j][pt]) {
                return 0;
            }
        }
        return 1;
    }

    // SET MATRIX 0

    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean refrow = false;
        boolean refcol = false;
        // marking
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
                if (i == 0 && matrix[i][j] == 0) {
                    refrow = true;// ref row must be '0'
                }
                if (j == 0 && matrix[i][j] == 0) {
                    refcol = true;
                }
            }
        }
        // update 11 matrix
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (refcol) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
        if (refrow) {
            for (int i = 0; i < c; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    // MULTIPLY STRINGS
    public String multiply(String str1, String str2) {
        if (str1.equals("0") || str2.equals("0")) {
            return "0";
        }
        int n = str1.length();
        int m = str2.length();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        char[] ans = new char[n + m];
        Arrays.fill(ans, (char) (0 + '0'));
        int carry = 0;
        int mul = 0;
        int k = n + m;
        int p = n + m;
        for (int i = m - 1; i >= 0; i--) {
            int down = arr2[i] - '0';
            p--;
            k = p;
            carry = 0;
            for (int j = n - 1; j >= 0; j--) {
                int up = arr1[j] - '0';
                mul = (up * down) + carry + (ans[k] - '0');

                carry = mul / 10;
                ans[k] = (char) ((mul % 10) + '0');
                k--;
            }
            if (carry != 0) {
                ans[k] = (char) (carry + '0');
            }
        }
        int pt = 0;
        while (ans[pt] == '0') {
            pt++;
        }
        return String.valueOf(ans).substring(pt);
    }

    // SIEVE
    // print prime upto n
    public void sieve(int n) {
        boolean[] arr = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (arr[i] == false) {// ith number is prime
                // mark table of i as o prime
                for (int j = 2 * i; j <= n; j += i) {
                    arr[j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!arr[i]) {
                System.out.println(i);
            }
        }
    }

}