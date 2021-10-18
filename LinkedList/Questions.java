public class Questions {
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
            // if i is equals to left
            while (i >= left && i <= right) {
                ListNode f = c.next;
                c.next = null;
                addFirst(c);
                c = f;
                i++;
            }
            if (i > right) {
                p.next = th;
                tt.next = c;
                break;
            }
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

}