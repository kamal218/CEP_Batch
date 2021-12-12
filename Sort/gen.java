import java.awt.List;
import java.lang.reflect.Array;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class gen {
    public static void main(String[] args) {

    }

    public static void mergeSort(int[] nums, int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = (si + ei) / 2;
        mergeSort(nums, si, mid);
        mergeSort(nums, mid + 1, ei);
        merge2ArraysOutplace(nums, si, ei, mid);
    }

    public static void merge2ArraysOutplace(int[] nums, int si, int ei, int mid) {
        int s1 = si;
        int e1 = mid;
        int s2 = mid + 1;
        int e2 = ei;
        int[] left = new int[e1 - s1 + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = nums[s1 + i];
        }
        int[] right = new int[e2 - s2 + 1];
        for (int i = 0; i < right.length; i++) {
            right[i] = nums[s2 + i];
        }
        int p1 = 0;// left
        int p2 = 0;// right
        int p = s1;// own
        while (p1 != left.length && p2 != right.length) {
            if (left[p1] < right[p2]) {
                nums[p] = left[p1];
                p1++;
            } else {
                nums[p] = right[p2];
                p2++;
            }
            p++;
        }
        while (p1 < left.length) {
            nums[p] = left[p1];
            p1++;
            p++;
        }
        while (p2 < right.length) {
            nums[p] = right[p2];
            p2++;
            p++;
        }
    }

    // MERGE 2 ARRAYYS INPLACE
    public static void merge2ArraysInplace(int[] nums, int si, int ei, int mid) {
        int p1 = si;
        int p2 = mid + 1;
        while (p1 < p2 && p2 <= ei) {
            if (nums[p1] < nums[p2]) {
                p1++;
            } else {
                int val = nums[p2];
                int i = p2;
                while (i > p1) {
                    nums[i] = nums[i - 1];
                    i--;
                }
                nums[p1] = val;
                p1++;
                p2++;
            }
        }
    }

    // QUICK SORT

    public void quickSort(int[] nums, int si, int ei) {
        if (si >= ei) {
            return;
        }
        int pidx = partition(nums, si, ei, nums[ei]);
        quickSort(nums, si, pidx - 1);
        quickSort(nums, pidx + 1, ei);
    }

    public int partition(int[] nums, int si, int ei, int pval) {
        int i = si, j = si;
        while (j <= ei) {
            if (nums[i] <= pval) {
                swap(nums, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    // COUNT INVERSION
    public static long mergeSort(long[] nums, int si, int ei) {
        if (si >= ei) {
            return 0;
        }
        int mid = (si + ei) / 2;
        long ans = 0;
        ans += mergeSort(nums, si, mid);
        ans += mergeSort(nums, mid + 1, ei);
        ans += mergeArrays(nums, si, ei, mid);
        return ans;
    }

    public static long mergeArrays(long[] nums, int si, int ei, int mid) {
        int s1 = si;
        int e1 = mid;
        int s2 = mid + 1;
        int e2 = ei;
        long ans = 0;
        long[] left = new long[e1 - s1 + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = nums[s1 + i];
        }
        long[] right = new long[e2 - s2 + 1];
        for (int i = 0; i < right.length; i++) {
            right[i] = nums[s2 + i];
        }
        int p1 = 0;// left
        int p2 = 0;// right
        int p = s1;// own
        while (p1 != left.length && p2 != right.length) {
            if (left[p1] <= right[p2]) {
                nums[p] = left[p1];
                p1++;
            } else {
                nums[p] = right[p2];
                p2++;
                ans += (left.length - p1);
            }
            p++;
        }
        while (p1 < left.length) {
            nums[p] = left[p1];
            p1++;
            p++;
        }
        while (p2 < right.length) {
            nums[p] = right[p2];
            p2++;
            p++;
        }
        return ans;
    }

    // KTH LARGEST USING QUICK SELECT

    public int findKthLargest(int[] nums, int k) {
        int pos = nums.length - k;
        int si = 0;
        int ei = nums.length - 1;
        while (si <= ei) {
            int pidx = quickPartition(nums, si, ei, nums[ei]);
            if (pidx == pos) {
                return nums[pos];
            } else if (pidx > pos) {
                ei = pidx - 1;
            } else {
                si = pidx + 1;
            }
        }
        return -1;
    }

    public int quickPartition(int[] nums, int si, int ei, int pval) {
        int i = si, j = si;
        while (j <= ei) {
            if (nums[j] <= pval) {
                swap(nums, i, j);
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i - 1;
    }

    // SORT DIAGONALLY
    public int[][] diagonalSort(int[][] mat) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int r = mat.length;
        int c = mat[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int d = i - j;
                if (!map.containsKey(d)) {
                    map.put(d, new ArrayList<>());
                }
                map.get(d).add(mat[i][j]);
            }
        }
        for (int key : map.keySet()) {
            ArrayList<Integer> list = map.get(key);
            Collections.sort(list, Collections.reverseOrder());
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int d = i - j;
                ArrayList<Integer> list = map.get(d);
                mat[i][j] = list.remove(list.size() - 1);
            }
        }
        return mat;
    }

    // SORT DIAGONNALLY USING COUNT SORT
    public int[][] diagonalSort(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        for (int i = 0; i < r; i++) {
            countsort(mat, i, 0);
        }
        for (int i = 1; i < c; i++) {
            countsort(mat, 0, i);
        }
        return mat;
    }

    public void countsort(int[][] mat, int i, int j) {
        int[] fmap = new int[102];
        int ii = i;
        int jj = j;
        while (ii < mat.length & jj < mat[0].length) {
            fmap[mat[i][j]]++;
            ii++;
            jj++;
        }
        ii = i;
        jj = j;
        int pt = 0;
        while (ii < mat.length & jj < mat[0].length) {
            while (fmap[pt] == 0) {
                pt++;
            }
            mat[ii][jj] = pt;
            fmap[pt]--;
            ii++;
            jj++;
        }
    }

    // MERGE INTERVALS

    public int[][] merge(int[][] arr) {
        ArrayList<int[]> stack = new ArrayList<>();
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        stack.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int[] lint = stack.get(stack.size() - 1);
            int[] mint = arr[i];
            if (mint[0] <= lint[1]) {
                stack.remove(stack.size() - 1);
                int[] temp = new int[2];
                temp[0] = lint[0];
                temp[1] = Math.max(lint[1], mint[1]);
                stack.add(temp);
            } else {
                stack.add(mint);
            }
        }
        int[][] ans = new int[stack.size()][2];
        for (int i = 0; i < stack.size(); i++) {
            ans[i] = stack.get(i);
        }
        return ans;
    }

    // INSERT INTERVALS

    public int[][] insert(int[][] arr, int[] nint) {
        int[][] arrcopy = new int[arr.length + 1][2];
        for (int i = 0; i < arr.length; i++) {
            arrcopy[i] = arr[i];
        }
        arrcopy[arr.length] = nint;
        return merge(arrcopy);
    }

    // INSERT INTERVAL (o(n)+O(n))
    public int[][] insert(int[][] arr, int[] nint) {
        List<int[]> stack = new ArrayList<>();
        // put all intervals before starting of new interval
        int i = 0;
        while (i < arr.length && arr[i][0] < nint[0]) {
            stack.add(arr[i]);
            i++;
        }
        // put new interval
        if (stack.size() == 0 || nint[0] > stack.get(stack.size() - 1)[1]) {// no merging
            stack.add(nint);
        } else {
            int[] temp = new int[2];
            temp[0] = stack.get(stack.size() - 1)[0];
            temp[1] = Math.max(nint[1], stack.get(stack.size() - 1)[1]);
            stack.remove(stack.size() - 1);
            stack.add(temp);
        }
        // put remaining intervals
        while (i < arr.length) {
            if (arr[i][0] > stack.get(stack.size() - 1)[1]) {
                stack.add(arr[i]);
            } else {
                int[] temp = new int[2];
                temp[0] = stack.get(stack.size() - 1)[0];
                temp[1] = Math.max(arr[i][1], stack.get(stack.size() - 1)[1]);
                stack.remove(stack.size() - 1);
                stack.add(temp);
            }
            i++;
        }
        int[][] ans = new int[stack.size()][2];
        i = 0;
        while (i < stack.size()) {
            ans[i] = stack.get(i);
            i++;
        }
        return ans;
    }

    // MIN REMOVAL FOR NON OVERLAPPING
    public int eraseOverlapIntervals(int[][] arr) {
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        int p = 0;
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[p][1] <= arr[i][0]) {
                p = i;
            } else {
                if (arr[i][1] < arr[p][1]) {
                    p = i;
                }
                ans++;
            }
        }
        return ans;
    }

    // MEETING ROOM 2

    // PRIORITY QUEUE

    public int minMeetingRooms(int[][] arr) {
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();// for min end time
        pq.add(arr[0][1]);
        for (int i = 1; i < arr.length; i++) {
            int st = arr[i][0];
            int end = arr[i][1];
            int lmend = pq.peek();// last meeting end
            if (lmend <= st) {
                pq.poll();
                pq.add(end);
            } else {
                pq.add(end);
            }
        }
        return pq.size();
    }

    // USING SORTING
    public int minMeetingRooms(int[][] arr) {
        int[] st = new int[arr.length];
        int[] end = new int[arr.length];
        int i = 0;
        for (int[] ar : arr) {
            st[i] = ar[0];
            end[i] = ar[1];
            i++;
        }
        Arrays.sort(st);
        Arrays.sort(end);
        int ans = 0;
        i = 0;
        int j = 0;
        while (i < arr.length) {
            if (st[i] >= end[j]) {
                i++;
                j++;
            } else {
                i++;
                ans++;
            }
        }
        return ans;
    }

    // ORDERLY QUEUE
    public String orderlyQueue(String s, int k) {
        char[] arr = s.toCharArray();
        if (k > 1) {
            Arrays.sort(arr);
            s = String.valueOf(arr);
            return s;
        }
        String ans = s;
        for (int i = 0; i < s.length(); i++) {
            rotate(arr);
            String ns = String.valueOf(arr);
            if (ans.compareTo(ns) > 0) {
                ans = ns;
            }
        }
        return ans;
    }

    public void rotate(char[] arr) {
        char t = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = t;
    }

    public void swap(int[] nums, int i, int j) {
        nums[j] = ((nums[i] + nums[j]) - (nums[i] = nums[j]));
    }

}