import org.graalvm.compiler.core.common.util.ReversedList;

public class Questions {
    public class ListNode {
        ListNode next;
        int val = 0;

        public ListNode(int v) {
            val = v;
        }
    }

    // reverse using addfirst
    public ListNode reverseList(ListNode head) {
        ListNode ans = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode f = curr.next;
            curr.next = null;
            ans = addFirst(ans, curr);
            curr = f;
        }
        head.next = null;
        return ans;
    }

    public ListNode addFirst(ListNode ans, ListNode node) {
        if (ans == null) {
            return node;
        } else {
            node.next = ans;
            return node;
        }
    }

    // usin 3 pointers
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null, forw = null;
        while (curr != null) {
            forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }
        return prev;
    }

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode n = head.next;
        head.next = null;
        ListNode tr = reverseList(head.next);
        n.next = head;
        return tr;
    }

    ListNode th = null, tt = null;

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode p = d;
        ListNode c = d.next;
        int i = 1;
        while (true) {
            // middle range
            while (i >= left && i <= right) {
                ListNode f = c.next;
                c.next = null;
                addFirst(c);
                c = f;
                i++;
            }
            // right range
            if (i > right) {
                p.next = th;
                tt.next = c;
                break;
            }
            // left range
            p = c;
            c = c.next;
            i++;
        }
    }

    public void addFirst(ListNode c) {
        if (th == null) {
            th = tt = c;
        } else {
            c.next = th;
            th = c;
        }
    }

    // Reverse in groups of k
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        // set current and fhead
        ListNode curr = head;
        int t = k;
        while (t-- > 1) {
            curr = curr.next;
            if (curr == null) {
                return head;
            }
        }
        ListNode fhead = curr.next;
        curr.next = null;
        ListNode recAns = reverseKGroup(fhead, k);
        ListNode revAns = reverseList(head);
        head.next = recAns;
        return revAns;
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null, forw = null;
        while (curr != null) {
            forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }
        return prev;
    }

    // using Add first and length
    ListNode ah = null, at = null, th = null, tt = null;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        int len = size(head);
        ListNode curr = head;
        while (len >= k) {
            int t = k;
            while (t-- > 0) {
                ListNode f = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = f;
            }
            // we have k nodes in th and tt
            if (ah == null) {
                ah = th;
                at = tt;
            } else {
                at.next = th;
                at = tt;
            }
            th = tt = null;
            len -= k;
        }
        return ah;
    }

    public int size(ListNode c) {
        int s = 0;
        while (c != null) {
            c = c.next;
            s++;
        }
        return s;
    }

    public void addFirst(ListNode curr) {
        if (th == null) {
            th = tt = curr;
        } else {
            curr.next = th;
            th = curr;
        }
    }

    // index based middle
    public ListNode mid(ListNode head) {
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next;
            f = f.next;
        }
        return s;
    }

    // size based middle
    public ListNode middleNode(ListNode head) {
        ListNode s = head, f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next;
            f = f.next;
        }
        return s;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode m = mid(head);
        ListNode fhead = m.next;
        m.next = null;
        fhead = reverseList(fhead);
        ListNode p1 = head, p2 = fhead;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        m.next = reverseList(fhead);
        return true;
    }

    // FOLD OF LINKED LIST
    public static void fold(ListNode head) {
        ListNode m = mid(head);
        ListNode fhead = m.next;
        m.next = null;
        fhead = reverseList(fhead);
        ListNode c1 = head, c2 = fhead;
        ListNode f1 = null, f2 = null;
        while (c2 != null) {
            f1 = c1.next;
            f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }
    }

    // UNFOLD
    public static void unfold(ListNode head) {
        ListNode d1 = new ListNode(-1);
        ListNode d2 = new ListNode(-1);
        ListNode c1 = d1;
        ListNode c2 = d2;
        ListNode c = head;
        while (c != null) {
            c1.next = c;
            c2.next = c.next;

            c1 = c1.next;
            c2 = c2.next;

            c = c.next;
            if (c != null)
                c = c.next;
        }
        c1.next = null;
        ListNode rev = reverseList(d2.next);
        c1.next = rev;
        return d1.next;
    }

    public static ListNode segregate01(ListNode head) {
        ListNode zero = new ListNode(-1);
        ListNode one = new ListNode(-1);
        ListNode zp = zero, op = one;
        ListNode c = head;
        while (c != null) {
            if (c.val == 0) {
                zp.next = c;
                zp = zp.next;
            } else {
                op.next = c;
                op = op.next;
            }
            c = c.next;
        }
        zp.next = null;
        op.next = null;

        zp.next = one.next;
        return zero.next;
    }

    // REMOVE DUPLICAES 1
    public ListNode deleteDuplicates(ListNode head) {
        ListNode c = head;
        ListNode f = null;
        while (c != null) {
            f = c.next;
            while (f.val == c.val) {
                f = f.next;
            }
            c.next = f;
            c = c.next;
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode p = d, c = head;
        while (c != null) {
            while (c.val == c.next.val) {
                c = c.next;
            }
            if (c == p.next) {// not duplicate
                p = c;
                c = c.next;
            } else {
                p.next = c.next;
                c = c.next;
            }
        }
        return d.next;
    }

    // REMOVE DUPLICSTES 1 USING 2
    public ListNode deleteDuplicates(ListNode head) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode p = d, c = head;
        while (c != null) {
            while (c.val == c.next.val) {
                c = c.next;
            }
            if (c == p.next) {// not duplicate
                p = c;
                c = c.next;
            } else {
                p.next = c;
                c = c.next;
                p = c;
            }
        }
        return d.next;
    }

    // REMOVE NTH FROM END

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fix = head, var = head;
        while (n-- > 0) {
            var = var.next;
        }
        if (var == null) {
            return head.next;
        }
        while (var.next != null) {
            var = var.next;
            fix = fix.next;
        }
        fix.next = fix.next.next;
        return head;
    }

    // MERGGE 2 LISTS
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode d = new ListNode(-1);
        ListNode c = d, c1 = l1, c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val < c2.val) {
                c.next = c1;
                c1 = c1.next;
            } else {
                c.next = c2;
                c2 = c2.next;
            }
            c = c.next;
        }
        if (c1 == null) {
            c.next = c2;
        } else {
            c.next = c1;
        }
        return d.next;
    }

    // MERGE SORT

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode m = mid(head);
        ListNode nhead = m.next;
        m.next = null;
        head = sortList(head);
        nhead = sortList(nhead);
        return mergeTwoLists(head, nhead);
    }

    // MERGE K SORTED LISTS
    // USING MERGE SORT
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode sl = lists[0];
        int i = 0;
        for (i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                sl = lists[i];
                break;
            }
        }
        i++;
        int lnn = i - 1;
        for (; i < lists.length; i++) {
            if (lists[i] != null) {
                ListNode tail = getTail(lists[lnn]);
                tail.next = lists[i];
                lnn = i;
            }
        }
        return sortList(sl);
    }

    public ListNode getTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    // MERGE K SORTED USING LINEAR SEARCH AND MERGE 2 LIST
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = new ListNode(-10005);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                ans = mergeTwoLists(ans, lists[i]);
            }
        }
        return ans.next;
    }

    // MERGE K SORTED USING BINARY SEARCH METHOD AND MERGE 2 LIST
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei) {
            return lists[si];
        }
        int mid = (si + ei) / 2;
        ListNode l1 = mergeKLists(lists, si, mid - 1);
        ListNode l2 = mergeKLists(lists, mid, ei);
        ListNode ans = mergeTwoLists(l1, l2);
        return ans;
    }

    // FLATTEN MULTILEVEL DOUBLY LINKED LIST
    /*
     * // Definition for a Node. class Node { public int val; public Node prev;
     * public Node next; public Node child; };
     */
    public Node flatten(Node head) {
        flat(head);
        return head;
    }

    public Node flat(Node head) {
        if (head == null) {
            return null;
        }
        Node c = head;
        Node p = null;
        while (c != null) {
            if (c.child == null) {// no need to flatten
                p = c;
                c = c.next;
            } else {
                Node tail = flat(c.child);
                tail.next = c.next;
                if (c.next != null)
                    c.next.prev = tail;
                c.next = c.child;
                c.child.prev = c;
                c.child = null;
                p = c.prev;
                c = tail.next;
            }
        }
        return p;
    }

    // CLONE WITH RANDOM POINTER
    public Node copyRandomList(Node head) {
        // set only next pointer
        Node dummy = new Node(-1);
        Node d = dummy;
        Node c = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (c != null) {
            Node nnode = new Node(c.val);
            d.next = nnode;
            d = d.next;
            map.put(c, nnode);
        }

        // set random
        Node c1 = head;
        Node c2 = dummy.next;
        while (c1 != null) {
            Node random = c1.random;
            if (random != null) {
                Node crandom = map.get(random);
                c2.next = crandom;
            }
        }
        return dummy.next;
    }

    // CLONE WITHOUT SPACE
    public Node copyRandomList(Node head) {
        createMerge(head);
        setRandom(head);
        Node ans = separate(head);
        return ans;
    }

    public void createMerge(Node head) {
        Node c = head;
        while (c != null) {
            Node node = new Node(c.val);
            node.next = c.next;
            c.next = node;
            c = c.next;
        }
    }

    public void setRandom(Node head) {
        Node c = head;
        while (c != null) {
            Node random = c.random;
            if (random != null) {
                c.next.random = c.random.next;
            }
            c = c.next.next;
        }
    }

    public Node separate(Node head) {
        Node d1 = new Node(-1);
        Node d2 = new Node(-1);
        Node c1 = d1, c2 = d2;
        Node c = head;
        while (c != null) {
            c1.next = c;
            c2.next = c.next;

            c1 = c1.next;
            c2 = c2.next;

            c = c.next.next;
        }
        c1.next = null;
        c2.next = null;
        return d2.next;
    }

    // CYCLE IN LINKED LIST
    public boolean hasCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                return true;
            }
        }
        return false;
    }

    // INTERSECTION
    public ListNode detectCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        boolean cycle = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                cycle = true;
                break;
            }
        }
        if (!cycle) {
            return null;

        }
        s = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    // INTERSECTION OF 2 LINKED LIST

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode c = headA;
        while (c.next != null) {
            c = c.next;
        }
        c.next = headA;
        ListNode ans = detectCycle(headB);
        c.next = null;
        return ans;
    }

    // ADD 2 LINKED LIST 1

    public ListNode addTwoNumbers_(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode d = dummy;
        ListNode p1 = l1, p2 = l2;
        int carry = 0;
        while (p1 != null || p2 != null || carry == 1) {
            int v1 = p1 == null ? 0 : p1.val;
            int v2 = p2 == null ? 0 : p2.val;
            int sum = v1 + v2 + carry;
            int cval = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(cval);
            d.next = node;
            p1 = p1 == null ? p1 : p1.next;
            p2 = p2 == null ? p2 : p2.next;
            d = d.next;
        }
        return dummy.next;
    }

    // ADD 2 LINKED LIST 2
    // USING REVERSE
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode ans = addTwoNumbers_(l1, l2);
        return reverseList(ans);
    }

    // ADD WITHOUT EXTRA SPACE

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int s1 = size(l1);
        int s2 = size(l2);
        ListNode c1 = l1, c2 = l2;
        ListNode ans = null;
        while (s1 > s2) {
            ans = addFirst_(ans, c1.val);
            c1 = c1.next;
            s1--;
        }
        while (s2 > s1) {
            ans = addFirst_(ans, c2.val);
            c2 = c2.next;
            s2--;
        }
        while (c1 != null) {
            int val = (c1.val + c2.val);
            int carry = (val / 10);
            int cval = (val % 10);
            ans = addFirst_(ans, cval);
            if (carry == 1) {
                ListNode c = ans.next;
                ListNode f = ans;
                while (c != null && c.val == 9) {
                    c.val = 0;
                    c = c.next;
                }
                if (c == null) {
                    ListNode node = new ListNode(1);
                    f.next = node;
                } else {
                    c.val += 1;
                }
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        return reverseList(ans);

    }

    public int size(ListNode h) {
        ListNode c = h;
        int s = 0;
        while (c != null) {
            c = c.next;
            s++;
        }
        return s;
    }

    public ListNode addFirst_(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            return node;
        }
        node.next = head;
        return node;
    }
}
