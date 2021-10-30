package Adapters;
import java.util.*;

//push efficient

public class stackusingqueue2 {
    Queue<Integer> que1 = new LinkedList<>();

    public stackusingqueue2() {

    }

    public void push(int x) {
        que1.add(x);
    }

    public int pop() {
        int size = que1.size();
        while (size-- > 1) {
            que1.add(que1.poll());
        }
        int tr = que1.peek();
        return tr;
    }

    public int top() {
        int size = que1.size();
        while (size-- > 1) {
            que1.add(que1.poll());
        }
        int tr = que1.peek();
        que1.add(que1.poll());
        return tr;
    }

    public boolean empty() {
        return que1.size() == 0;
    }
}