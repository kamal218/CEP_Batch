public class Client {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        // list.addLast(1);
        // list.addLast(2);
        // list.addLast(3);
        // list.addLast(4);
        // list.addLast(5);
        // list.addAtIndex(6, 0);
        // System.out.println(list.getAtIndex(4));
        // list.removeFirst();
        // list.removeLast();
        // list.removeAtIndex(2);
        // list.display();
        list.addFirst(1);
        list.addLast(3);
        list.addAtIndex(1, 2);
        list.display();
        System.out.println(list.getAtIndex(1));
        list.removeAtIndex(1);
        System.out.println(list.getAtIndex(1));
    }
}