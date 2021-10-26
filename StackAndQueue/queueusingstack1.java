import java.util.*;

public class queueusingstack1 {
    Stack<Integer> st1 = new Stack<>();
    Stack<Integer> st2 = new Stack<>();

    public queueusingstack1() {

    }

    public void push(int x) {
        st1.push(x);
    }

    public int pop() {
        while (st1.size() > 1) {
            st2.push(st1.pop());
        }
        int tr = st1.pop();
        while (st2.size() > 0) {
            st1.push(st2.pop());
        }
        return tr;
    }

    public int peek() {
        while (st1.size() > 1) {
            st2.push(st1.pop());
        }
        int tr = st1.peek();
        while (st2.size() > 0) {
            st1.push(st2.pop());
        }
        return tr;
    }

    public boolean empty() {
        return st1.size() == 0;
    }
}