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
}
