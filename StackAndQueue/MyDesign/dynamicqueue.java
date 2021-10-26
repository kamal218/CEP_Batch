
public class dynamicqueue extends queue {
    public dynamicqueue() {
        super();
    }

    public dynamicqueue(int cap) {
        super(cap);
    }

    @Override
    // push at last
    public boolean add(int val) {
        if (super.size == que.length) {
            int[] t = super.que;
            super.que = new int[super.que.length * 2];
            for (int i = 0; i < super.size; i++) {
                super.que[i] = t[(h + i) % t.length];
            }
            super.h = 0;
            super.t = t.length - 1;
        }
        super.que[(super.t + 1) % super.que.length] = val;
        t = (super.t + 1) % super.que.length;
        super.size++;
        return true;
    }

}
