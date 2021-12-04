import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Questions {
    // TRAPPING RAIN WATER
    public int trap(int[] height) {
        int water = 0;
        int lmax = 0;
        int rmax = 0;
        int st = 0;
        int end = height.length - 1;
        while (st < end) {
            if (height[st] < height[end]) {// resolve st
                if (lmax > height[st]) {
                    water += lmax - height[st];
                } else {
                    lmax = height[st];
                }
                st++;
            } else {
                if (rmax > height[end]) {
                    water += rmax - height[end];
                } else {
                    rmax = height[end];
                }
                end--;
            }
        }
        return water;
    }

    // TRAPPING RAIN WATER 2
    public class triple {
        int r = 0;
        int c = 0;
        int ht = 0;

        public triple(int i, int j, int h) {
            r = i;
            c = j;
            ht = h;
        }
    }

    public int trapRainWater(int[][] height) {
        PriorityQueue<triple> pq = new PriorityQueue<>((a, b) -> (a.ht - b.ht));
        int m = height.length;
        int n = height[0].length;
        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    vis[i][j] = true;
                    pq.add(new triple(i, j, height[i][j]));
                }
            }
        }
        int water = 0;
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (pq.size() > 0) {
            triple weak = pq.poll();// weakest person of boundary
            int r = weak.r;
            int c = weak.c;
            int ht = weak.ht;
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    triple npair = new triple(nr, nc, height[nr][nc]);
                    if (weak.ht > height[nr][nc]) {
                        water += (weak.ht - height[nr][nc]);
                        npair.ht = weak.ht;
                    }
                    pq.add(npair);
                }
            }
        }
        return water;
    }

    // KTH LARGEST IN ARRAY
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : nums) {
            pq.add(ele);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // KTH IN DATA STREAM
    // PriorityQueue<Integer> pq;
    // int k;

    // public KthLargest(int k, int[] nums) {
    // pq=new PriorityQueue<>();
    // this.k=k;
    // for(int ele:nums){
    // pq.add(ele);
    // if(pq.size()>k){
    // pq.poll();
    // }
    // }
    // }

    public int add(int val) {
        pq.add(val);
        if (pq.size() > this.k) {
            pq.poll();
        }
        return pq.peek();
    }

    // KTH SMALLEST IN MATRIX
    public class triple {
        int i = 0;
        int j = 0;
        int v = 0;

        public triple(int i, int j, int v) {
            this.i = i;
            this.j = j;
            this.v = v;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<triple> pq = new PriorityQueue<>((a, b) -> (a.v - b.v));
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            pq.add(new triple(i, 0, matrix[i][0]));
        }
        while (k-- > 1) {
            triple top = pq.poll();
            if (top.j + 1 < n) {
                pq.add(new triple(top.i, top.j + 1, matrix[top.i][top.j + 1]));
            }
        }
        return pq.poll().v;
    }

    // KTH SMALLEST PRIME FRACTION
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> ((arr[a[0]] * arr[b[1]]) - (arr[a[1]] * arr[b[0]])));
        int[] ans = new int[2];
        for (int i = 1; i < arr.length; i++) {
            pq.add(new int[] { 0, i });
        }
        while (k-- > 1) {
            int[] top = pq.poll();
            if (top[0] + 1 < top[1]) {
                pq.add(new int[] { top[0] + 1, top[1] });
            }
        }
        ans[0] = arr[pq.peek()[0]];
        ans[1] = arr[pq.peek()[1]];
        return ans;
    }

    // K CLOSEST POINT FROM ORIGIN
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (-(a[0] * a[0] + a[1] * a[1]) + (b[0] * b[0] + b[1] * b[1])));
        for (int[] ar : points) {
            int x = ar[0];
            int y = ar[1];
            pq.add(new int[] { x, y });
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[][] ans = new int[k][2];
        int i = 0;
        while (pq.size() > 0) {
            int[] cord = pq.poll();
            ans[i][0] = cord[0];
            ans[i][1] = cord[1];
            i++;
        }
        return ans;
    }

    // ARRANGE SUCH THAT NO 2 ARE SAME
    public class pair {
        char ch = ' ';
        int freq = 0;

        public pair(char c, int f) {
            ch = c;
            freq = f;
        }
    }

    public String reorganizeString(String str) {
        StringBuilder ans = new StringBuilder();
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        int[] map = new int[26];
        for (char ch : str.toCharArray()) {
            map[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.add(new pair((char) ('a' + i), map[i]));
            }
        }
        pair block = pq.poll();
        ans.append(block.ch);
        block.freq--;
        while (pq.size() > 0) {
            pair top = pq.poll();
            ans.append(top.ch);
            top.freq--;

            if (block.freq > 0) {
                pq.add(block);
            }
            block = top;
        }
        if (block.freq > 0) {
            return "-1";
        }
        return ans.toString();
    }

    // ARRANGE SUCH THAT NO K ARE ADJACENT
    public String noKAreSame(String str, int k) {
        StringBuilder ans = new StringBuilder();
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        int[] map = new int[26];
        for (char ch : str.toCharArray()) {
            map[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.add(new pair((char) ('a' + i), map[i]));
            }
        }
        Queue<pair> block = new LinkedList<>();
        for (int i = 0; i < k - 1; i++) {
            pair top = pq.poll();
            ans.append(top.ch);
            top.freq--;
            block.add(top);
        }
        while (pq.size() > 0) {
            pair pqtop = pq.poll();
            ans.append(pqtop.ch);
            pqtop.freq--;
            pair quetop = block.poll();
            if (quetop.freq > 0) {
                pq.add(quetop);
            }
            block.add(pqtop);
        }
        while (block.size() > 0) {
            if (block.poll().freq > 0) {
                return "";
            }
        }

        return ans.toString();
    }

    // MEDIA IN DATA STREAM
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

    public MedianFinder() {
        
    }

    public void addNum(int num) {
        max.add(num);
        min.add(max.poll());
        if (min.size() != max.size()) {
            max.add(min.poll());
        }
    }

    public double findMedian() {
        if (min.size() == max.size()) {
            return ((double) min.peek() + (double) max.peek()) / 2;
        } else {
            return max.peek();
        }
    }

}