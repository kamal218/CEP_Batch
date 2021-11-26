import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class questions {
    // MORRIS

    public void morris(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.println(curr.val);
                curr = curr.right;
            } else {
                TreeNode rm = curr.left;
                while (rm.right != null && rm.right != curr) {
                    rm = rm.right;
                }
                if (rm.right == null) {
                    rm.right = curr;
                    curr = curr.left;
                } else {
                    rm.right = null;
                    System.out.println(curr.val);
                    curr = curr.right;
                }
            }
        }
    }
    // BST TO BLL USING MORRIS

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node curr = root;
        Node head = null;
        Node tail = null;
        while (curr != null) {
            if (curr.left == null) {
                if (head == null) {
                    head = tail = curr;
                } else {
                    tail.right = curr;
                    curr.left = tail;
                    tail = curr;
                }
                curr = curr.right;
            } else {
                Node rm = curr.left;
                while (rm.right != null && rm.right != curr) {
                    rm = rm.right;
                }
                if (rm.right == null) {
                    rm.right = curr;
                    curr = curr.left;
                } else {
                    rm.right = null;
                    if (head == null) {
                        head = tail = curr;
                    } else {
                        tail.right = curr;
                        curr.left = tail;
                        tail = curr;
                    }
                    curr = curr.right;
                }
            }
        }
        tail.right = head;
        head.left = tail;
        return head;
    }

    // RECOVER BST

    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode fwrong = null;
        TreeNode swrong = null;
        while (curr != null) {
            if (curr.left == null) {
                if (prev == null) {
                    prev = curr;
                } else {
                    if (prev.val > curr.val) {
                        if (fwrong == null) {
                            fwrong = prev;
                        }
                        swrong = curr;
                    }
                    prev = curr;
                }
                curr = curr.right;
            } else {
                TreeNode rm = curr.left;
                while (rm.right != null && rm.right != curr) {
                    rm = rm.right;
                }
                if (rm.right == null) {
                    rm.right = curr;
                    curr = cuur.left;
                } else {
                    rm.right = null;
                    if (prev == null) {
                        prev = curr;
                    } else {
                        if (prev.val > curr.val) {
                            if (fwrong == null) {
                                fwrong = prev;
                            }
                            swrong = curr;
                        }
                        prev = curr;
                    }
                    curr = curr.right;
                }
            }
        }
        int t = fwrong.val;
        fwrong.val = swrong.val;
        swrong.val = t;
    }

    // ITERATOR\
    class BSTIterator {
        TreeNode curr = null;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        public int next() {
            int tr = -1;
            if (hasNext()) {
                TreeNode[] ans = morris();// answer,next current
                tr = ans[0].val;
                curr = ans[1];
            }
            return tr;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public TreeNode[] morris() {
            TreeNode[] ans = new TreeNode[2];
            while (curr != null) {
                if (curr.left == null) {
                    ans[0] = curr;
                    ans[1] = curr.right;
                    return ans;
                } else {
                    TreeNode rm = curr.left;
                    while (rm.right != null && rm.right != curr) {
                        rm = rm.right;
                    }
                    if (rm.right == null) {
                        rm.right = curr;
                        curr = curr.left;
                    } else {
                        ans[0] = curr;
                        ans[1] = curr.right;
                        rm.right = null;
                        return ans;
                    }
                }
            }
            return ans;
        }
    }

    // MAX WIDTH
    public class pair {
        TreeNode root;
        int state = 0;

        public pair(TreeNode root, int state) {
            this.root = root;
            this.state = state;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<pair> que = new LinkedList<>();
        que.add(new pair(root, 0));
        int ans = 0;
        int min = 0;
        int max = 0;
        while (que.size() > 0) {
            int size = que.size();
            min = Integer.MAX_VALUE;
            max = 0;
            while (size-- > 0) {
                pair top = que.poll();
                min = Math.min(min, top.state);
                max = Math.max(max, top.state);
                if (top.root.left != null) {
                    pq.add(new pair(top.root.left, top.state * 2 + 1));
                }
                if (top.root.right != null) {
                    pq.add(new pair(top.root.right, top.state * 2 + 2));
                }
            }
            ans = Math.max(ans, max - min + 1);
        }
        return ans;
    }

}