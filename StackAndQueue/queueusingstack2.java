import java.util.*;

//pop efficient
public class queueusingstack2 {
    LinkedList<Integer> st1 = new LinkedList<>();
    LinkedList<Integer> st2 = new LinkedList<>();

    public queueusingstack2() {

    }

    public void push(int x) {
        while (st1.size() > 0) {
            st2.addLast(st1.removeLast());
        }
        st1.addLast(x);
        while (st2.size() > 0) {
            st1.addLast(st2.removeLast());
        }
    }

    public int pop() {
        return st1.removeLast();
    }

    public int peek() {
        return st1.getLast();
    }

    public boolean empty() {
        return st1.size() == 0;
    }
}