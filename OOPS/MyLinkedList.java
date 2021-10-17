
public class MyLinkedList implements myqueue {
    public class ListNode {
        int val = 0;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    int size = 0;
    ListNode head = null;

    public MyLinkedList() {

    }

    public int get(int idx) {
        if (idx < 0 || idx >= size)
            return -1;
        ListNode temp = head;
        while (idx-- > 0) {
            temp = temp.next;
        }
        return temp.val;
    }

    public void addAtHead(int x) {
        ListNode node = new ListNode(x);
        node.next = head;
        head = node;
        size++;
    }

    public void addAtTail(int x) {
        ListNode node = new ListNode(x);
        if (head == null) {
            head = node;
        } else {
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    public void add(int val) {
        addAtTail(val);
    }

    public void addAtIndex(int idx, int x) {
        if (idx == 0)
            addAtHead(x);
        else if (idx == size)
            addAtTail(x);
        else {
            ListNode temp = head;
            while (idx-- > 1) {
                temp = temp.next;
            }
            ListNode node = new ListNode(x);
            node.next = temp.next;
            temp.next = node;
            size++;
        }
    }

    public void deleteAtIndex(int idx) {
        if (idx < 0 || idx >= size)
            return;
        if (size == 0 || size == 1 || idx == 0) {
            removeFirst();
            return;
        }
        if (idx == size - 1) {
            removeLast();
            return;
        }
        size--;
        ListNode temp = head;
        while (idx-- > 1) {
            temp = temp.next;
        }
        ListNode rem = temp.next;
        temp.next = temp.next.next;
        rem.next = null;
        return;
    }

    public int removeFirst() {
        if (size == 0)
            return -1;
        else if (size == 1) {
            size--;
            int tr = head.val;
            head = null;
            return tr;
        } else {
            size--;
            int tr = head.val;
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            return tr;
        }
    }

    public void poll() {
        removeFirst();
    }

    public int removeLast() {
        if (size == 0 || size == 1)
            return removeFirst();
        size--;
        ListNode temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        int tr = temp.next.val;
        temp.next = null;
        return tr;
    }
}
