public class client {
    public static void main(String[] args) {
        // int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        // heap mheap = new heap(arr, true);
        // // mheap.add(-1);
        // // mheap.poll();
        // // mheap.update(2,20);
        // mheap.display();
        int[] arr = { 2, 5, 3, 3, -1, 0, 90 };
        heapsort hs = new heapsort(arr, false);// (O(nlogn))
        hs.sort();
        hs.print();
    }
}