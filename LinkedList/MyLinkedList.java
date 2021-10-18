public class MyLinkedList {
    public class ListNode {
        int val = 0;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "My value" + this.val + "\n";
        }
    }

    ListNode head = null;
    int size = 0;

    // add first
    public void addFirst(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    // add last
    public void addLast(int val) {
        ListNode node = new ListNode(val);
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

    // get at index
    public int getAtIndex(int idx) {
        if (idx < 0 || idx >= size) {
            return -1;
        }
        ListNode curr = head;
        while (idx-- > 0) {
            curr = curr.next;
        }
        return curr.val;
    }

    // add at index
    public void addAtIndex(int idx, int val) {
        if (idx < 0 || idx > size) {
            return;
        }
        if (idx == 0) {
            addFirst(val);
            return;
        }
        if (idx == size) {
            addLast(val);
            return;
        }
        ListNode curr = head;// 2 val node
        while (idx-- > 1) {
            curr = curr.next;
        }
        ListNode node = new ListNode(val);// 10 vali node
        node.next = curr.next;
        curr.next = node;
        size++;
    }
    // removefirst

    public int removeFirst() {
        if (head == null) {
            return -1;
        } else if (size == 1) {// head.next==null
            int tr = head.val;// tr-> to return
            head = head.next;
            size--;
            return tr;
        } else {
            int tr = head.val;
            ListNode t = head.next;
            head.next = null;
            head = t;
            size--;
            return tr;
        }
    }

    // remove last
    public int removeLast() {
        if (head == null) {
            return -1;
        } else if (size == 1) {// head.next==null
            int tr = head.val;// tr-> to return
            head = head.next;
            size--;
            return tr;
        } else {
            ListNode curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            int tr = curr.next.val;
            curr.next = null;
            size--;
            return tr;
        }
    }

    // removeatindex
    public int removeAtIndex(int idx) {
        if (idx < 0 || idx >= size) {
            return -1;
        }
        if (idx == 0) {
            return removeFirst();
        }
        if (idx == size - 1) {
            return removeLast();
        }
        ListNode curr = head;
        while (idx-- > 1) {
            curr = curr.next;
        }
        int tr = curr.next.val;
        ListNode rem = curr.next;
        curr.next = curr.next.next;
        rem.next = null;
        return tr;
    }

    public void display() {
        ListNode curr = head;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
    }
}
