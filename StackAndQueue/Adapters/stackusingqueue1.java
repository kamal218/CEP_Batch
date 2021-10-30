package Adapters;
import java.util.*;

//push efficient

public class stackusingqueue1 {
    Queue<Integer> que1 = new LinkedList<>();
    Queue<Integer> que2 = new LinkedList<>();

    public stackusingqueue1() {

    }

    public void push(int x) {
        que1.add(x);
    }

    public int pop() {
        while (que1.size() > 1) {
            que2.add(que1.poll());
        }
        int tr = que1.poll();
        que1 = que2;
        que2 = new LinkedList<>();
        return tr;
    }

    public int top() {
        while (que1.size() > 1) {
            que2.add(que1.poll());
        }
        int tr = que1.peek();
        que2.add(que1.poll());
        que1 = que2;
        que2 = new LinkedList<>();
        return tr;
    }

    public boolean empty() {
        return que1.size() == 0;
    }
}