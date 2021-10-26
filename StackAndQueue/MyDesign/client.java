import java.rmi.server.SocketSecurityException;

public class client {
    public static void main(String[] args) {
        // stack mstack = new stack();
        // System.out.println(mstack.isEmpty());
        // mstack.push(1);
        // mstack.push(2);
        // mstack.push(3);
        // mstack.pop();
        // mstack.push(4);
        // mstack.push(5);
        // mstack.push(6);
        // mstack.display();
        // dynamicstack mstack = new dynamicstack(5);
        // mstack.push(1);
        // mstack.push(2);
        // mstack.push(3);
        // mstack.push(4);
        // mstack.push(5);
        // mstack.push(6);
        // mstack.display();

        // queue mqueue = new queue(5);
        // mqueue.add(1);
        // mqueue.add(2);
        // mqueue.add(3);
        // mqueue.add(4);
        // mqueue.add(5);
        // mqueue.add(6);
        // mqueue.add(6);
        // mqueue.poll();
        // mqueue.poll();
        // mqueue.add(6);
        // mqueue.add(7);
        // mqueue.display();

        dynamicqueue mqueue = new dynamicqueue(5);
        mqueue.add(1);
        mqueue.add(2);
        mqueue.add(3);
        mqueue.add(4);
        mqueue.add(5);
        mqueue.add(6);
        mqueue.display();

    }
}