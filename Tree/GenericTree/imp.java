import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class imp {
    public class Node {
        int val = 0;
        List<Node> children;

        public Node(int val) {
            this.val = val;
        }
    }

    // height
    public int height(Node root) {
        int ans = -1;
        for (Node child : root.children) {
            ans = Math.max(ans, height(child));
        }
        return ans + 1;
    }

    int dia = 0;

    // DIAMETER
    public int diameter(Node root) {
        int max = -1;
        int smax = -1;
        for (Node child : root.children) {
            int cval = diameter(child);
            if (cval > max) {
                smax = max;
                max = cval;
            } else if (cval > smax) {
                smax = cval;
            }
        }
        dia = Math.max(dia, max + smax + 2);
        return Math.max(max, smax) + 1;
    }
    // ROOT TO NODE PATH

    public List<Integer> rootToNode(Node root, int data) {
        if (root.val == data) {
            List<Integer> base = new ArrayList<>();
            base.add(root.val);
            return base;
        }
        for (Node child : root.children) {
            List<Integer> list = rootToNode(child, data);
            if (list.size() > 0) {
                list.add(root.val);
                return list;
            }
        }
        return new ArrayList<>();
    }

    // LCA
    public Node LCA(Node root, int p, int q) {
        if (root.val == p) {
            return root;
        }
        if (root.val == q) {
            return root;
        }
        int ct = 0;
        Node single = null;
        for (Node child : root.children) {
            Node node = LCA(child, p, q);
            if (node != null) {
                ct++;
            }
            if (ct == 1) {
                single = node;
            }
        }
        if (ct == 2) {
            return root;
        }
        return single;
    }
    // FLATTEN GENERIC TREE

    public Node flattenTree(Node root) {
        if (root.children.size() == 0) {
            return root;
        }
        List<Node> tail = new ArrayList<>();
        for (Node child : root.children) {
            Node ctail = flattenTree(child);
            tail.add(ctail);
        }
        for (int i = root.children.size() - 1; i > 0; i--) {
            Node last = root.children.get(i);
            root.children.remove(root.children.size() - 1);
            // getTail of i-1 th element
            Node ctail = tail.get(i - 1);
            ctail.children.add(last);
        }
        return tail.get(tail.size() - 1);
    }

    // DELETE LEAF NODES
    public void deleteLeafNode(Node root) {
        for (int i = 0; i < root.children.size(); i++) {
            if (root.children.get(i).children.size() == 0) {
                root.children.remove(i);
                i--;
            }
        }
        for (Node child : root.children) {
            deleteLeafNode(child);
        }
    }

    // PATH IN ZIG ZAG TREE
    public List<Integer> pathInZigZagTree(int n) {
        List<Integer> ans = new ArrayList<>();
        int clv = 1;
        int csum = 0;
        while (csum < n) {
            csum += clv;
            clv *= 2;
        }
        clv /= 2;
        while (n != 1) {
            ans.add(n);
            int cnode = (3 * clv) - n - 1;
            int par = (cnode / 2);
            n = par;
            clv /= 2;
        }
        ans.add(1);
        Collections.reverse(ans);
        return ans;
    }

    // COLORING TREE

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode ref = find(root, x);
        int lsize = size(ref.left);
        int rsize = size(ref.right);
        int remsize = (n - lsize - rsize - 1);
        int max = Math.max(remsize, Math.max(lsize, rsize));
        return max > (n / 2);
    }

    public int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public TreeNode find(TreeNode root, int data) {
        if (root == null) {
            return null;
        }
        if (root.val == data) {
            return root;
        }
        TreeNode left = find(root.left, data);
        if (left != null) {
            return left;
        }
        TreeNode right = find(root.right, data);
        if (right != null) {
            return right;
        }
        return null;
    }

    // MIN CAMERA
    // 1 need camera
    // 0 camera
    // -1 covered
    int ans = 0;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        int rv = minCamera_(root);
        if (rv == 1) {
            ans++;
        }
        return ans;
    }

    public int minCamera_(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = minCamera_(root.left);
        int right = minCamera_(root.right);
        if (left == 1 || right == 1) {
            ans++;
            return 0;
        }
        if (left == 0 || right == 0) {
            return -1;
        }
        return 1;
    }

}