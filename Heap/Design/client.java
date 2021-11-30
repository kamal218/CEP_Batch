public class client {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        heap mheap = new heap(arr);
        // mheap.add(-1);
        // mheap.poll();
        mheap.update(2,20);
        mheap.display();
    }
}