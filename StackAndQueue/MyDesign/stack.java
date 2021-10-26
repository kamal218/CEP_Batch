
public class stack {
    int[] st;
    int top = -1;// last element in stack

    public stack() {
        st = new int[20];
    }

    public stack(int cap) {
        st = new int[cap];
    }

    // push at last
    public boolean push(int val) {
        if (top == st.length - 1) {
            System.out.println("Stack Overflow!");
            return false;
        }
        st[top + 1] = val;
        top++;
        return true;
    }

    // pop from last
    public int pop() {
        if (top == -1) {
            System.out.println("Empty Stack");
            return -1;
        }
        int tr = st[top];
        top--;
        return tr;
    }

    // size

    public int size() {
        return top + 1;
    }

    // top of stack

    public int top() {
        if (top == -1) {
            System.out.println("Empty stack!");
            return -1;
        }
        return st[top];
    }

    // is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // display
    public void display() {
        for (int i = 0; i <= top; i++) {
            System.out.print(st[i] + " ");
        }
        System.out.println();
    }
}
