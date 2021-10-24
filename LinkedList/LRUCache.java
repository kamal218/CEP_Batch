import java.util.HashMap;

public class LRUCache {
    int cap = 0;
    int size = 0;

    public class ListNode {
        int key = 0;
        int value = 0;
        ListNode next = null;
        ListNode prev = null;

        public ListNode(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    ListNode head = null;
    ListNode tail = null;
    HashMap<Integer, ListNode> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        // exist
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            removeGiven(node);
            addFirst(node);
            return node.value;
        }
        // doesnot exist
        else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // exist
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            removeGiven(node);
            addFirst(node);
        }
        // doesnot exist
        else {
            // we will have to put new node
            // completely
            if (size == cap) {
                map.remove(tail.key);
                removeGiven(tail);
                ListNode node = new ListNode(key, value);
                addFirst(node);
                map.put(key, node);
            }
            // space available
            else {
                ListNode node = new ListNode(key, value);
                addFirst(node);
                map.put(key, node);
            }
        }
    }

    public void addFirst(ListNode node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void removeGiven(ListNode node) {
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        if (node == head) {
            ListNode f = head.next;
            head.next = null;
            f.prev = null;
            head = f;
        } else if (node == tail) {
            ListNode b = tail.prev;
            b.next = null;
            tail.prev = null;
            tail = b;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
        size--;
    }
}
