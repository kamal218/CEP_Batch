public class heap {
    private int[] arr;
    int nidx = 0;
    boolean isMax = true;

    public heap() {
        arr = new int[10];
        nidx = 0;
    }

    public heap(int[] arr) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        nidx = arr.length;
        // convertIntoHeapUsingDownheapify(arr);
        convertIntoHeapUsingUpheapify(arr);
    }

    public heap(int[] arr, boolean isMax) {
        this.arr = new int[arr.length];
        this.isMax = isMax;
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        nidx = arr.length;
        // convertIntoHeapUsingDownheapify(arr);
        convertIntoHeapUsingUpheapify(arr);
    }

    public heap(int isize) {

    }

    private void convertIntoHeapUsingDownheapify(int[] arr) {// (O(n))
        for (int i = nidx - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    private void downHeapify(int pidx) {
        int lc = pidx * 2 + 1;
        int rc = pidx * 2 + 2;
        int sidx = pidx;
        if (lc < nidx && needToswap(pidx, lc)) {
            sidx = lc;
        }
        if (rc < nidx && needToswap(pidx, rc)) {
            sidx = rc;
        }

        if (sidx != pidx) {
            swap(arr, sidx, pidx);
            downHeapify(sidx);
        }
    }

    public void convertIntoHeapUsingUpheapify(int[] arr) {// (O(nlogn))
        for (int i = 0; i < nidx; i++) {
            upHeapify(i);
        }
    }

    public void upHeapify(int cidx) {
        int pidx = (cidx - 1) / 2;
        if (pidx >= 0 && needToswap(pidx, cidx)) {
            swap(arr, cidx, pidx);
            upHeapify(pidx);
        }
    }

    public boolean needToswap(int pidx, int cidx) {
        if (isMax) {
            return arr[pidx] < arr[cidx];
        } else {
            return arr[pidx] > arr[cidx];
        }
    }

    public void add(int val) {// O(logn)
        if (nidx == arr.length) {
            int[] temp = this.arr;
            this.arr = new int[2 * temp.length];
            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }
        }
        arr[nidx] = val;
        upHeapify(nidx);
        nidx++;
    }

    public int update(int idx, int val) {// O(logn)
        int tr = arr[idx];
        this.arr[idx] = val;
        upHeapify(idx);
        downHeapify(idx);
        return tr;
    }

    public int poll() {// O(logn)
        int tr = 0;
        swap(arr, 0, nidx - 1);
        nidx--;
        downHeapify(0);
        return tr;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public void display() {
        int ct = 1;
        int i = 0;
        while (i < arr.length) {
            for (int j = 0; j < ct; j++) {
                if (i + j < nidx) {
                    System.out.print(arr[i + j] + " ");
                }
            }
            System.out.println();
            i += ct;
            ct = ct * 2;
        }
    }

}