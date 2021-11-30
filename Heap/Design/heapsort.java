
public class heapsort {
    private int[] arr;
    boolean isMax = true;

    public heapsort(int[] arr, boolean isMax) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        this.isMax = isMax;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(i, arr.length);
        }
    }

    private void heapify(int pidx, int lidx/* last inndex */ ) {
        int lc = pidx * 2 + 1;
        int rc = pidx * 2 + 2;
        int sidx = pidx;
        if (lc < lidx && needToswap(sidx, lc)) {
            sidx = lc;
        }
        if (rc < lidx && needToswap(sidx, rc)) {
            sidx = rc;
        }

        if (sidx != pidx) {
            swap(arr, sidx, pidx);
            heapify(sidx, lidx);
        }
    }

    public boolean needToswap(int pidx, int cidx) {
        if (isMax) {
            return arr[pidx] < arr[cidx];
        } else {
            return arr[pidx] > arr[cidx];
        }
    }

    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - i - 1);
            heapify(0, arr.length - i - 1);
        }
    }
    public void print(){
        for(int ele:arr){
            System.out.print(ele+ " ");
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}