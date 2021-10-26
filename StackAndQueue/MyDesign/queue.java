public class queue {
    int[] que;
    int h = 0;
    int t = -1;
    int size = 0;

    public queue() {
        que = new int[10];
    }

    public queue(int cap) {
        que = new int[cap];
    }

    // add
    public boolean add(int val) {
        if (size == que.length ) {
            System.out.println("Queue Overflow");
            return false;
        }
        que[(t + 1) % que.length] = val;
        t = (t + 1) % que.length;
        size++;
        return true;
    }

    // poll
    public int poll() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return -1;
        }
        int tr = que[h];
        h = (h + 1) % que.length;
        size--;
        return tr;
    }

    // peek
    public int peek() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return -1;
        }
        int tr = que[h];
        return tr;
    }

    // size
    public int size() {
        return size;
    }
    // is empty

    public boolean isEmpty() {
        return size == 0;
    }

    // display
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(que[(i + h) % que.length]);
        }
        System.out.println();
    }
}