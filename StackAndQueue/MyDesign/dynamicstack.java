
public class dynamicstack extends stack {
    public dynamicstack() {
        super();
    }

    public dynamicstack(int cap) {
        super(cap);
    }

    @Override
    // push at last
    public boolean push(int val) {

        if (super.top == super.st.length - 1) {
            int[] temp = st;
            st = new int[super.st.length * 2];
            for (int i = 0; i < temp.length; i++)
                super.st[i] = temp[i];
        }
        super.st[top + 1] = val;
        top++;
        return true;
    }

}
