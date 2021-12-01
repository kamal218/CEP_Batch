public class avl {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        int ht = 0;
        int balf = 0;

        public TreeNode(int val) {
            this.val = val;
        }

    }

    // add in bst
    public static TreeNode add(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = add(root.right, val);
        } else {
            root.left = add(root.left, val);
        }
        root = balanceTree(root);
        return root;
    }

    // remove from bst
    public static TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val < val) {
            root.right = remove(root.right, val);
        } else if (root.val > val) {
            root.left = remove(root.left, val);
        } else {
            if (root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;
            TreeNode mVal = getMax(root.left);
            root.val = mVal.val;
            root.left = remove(root.left, mVal.val);
        }
        root = balanceTree(root);
        return root;
    }

    // get maximum
    public static TreeNode getMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // balance if needed
    public static TreeNode balanceTree(TreeNode root) {
        update(root);
        int balf = root.balf;
        if (balf == 2) {
            int balfleft = root.left.balf;
            if (balfleft == 1) {// rr
                return rr(root);
            } else {// lr
                root.left = ll(root.left);
                return rr(root);
            }
        } else if (balf == -2) {
            int balfright = root.right.balf;
            if (balfright == 1) {// rl
                root.right = rr(root.right);
                return ll(root);
            } else {// ll
                return ll(root);
            }
        }
        return root;
    }

    // update height and balance factor
    public static void update(TreeNode root) {
        int lh = root.left == null ? -1 : root.left.ht;
        int rh = root.right == null ? -1 : root.right.ht;
        root.ht = Math.max(lh, rh) + 1;
        root.balf = lh - rh;
    }

    // left left rotation
    public static TreeNode ll(TreeNode A) {
        TreeNode B = A.right;
        TreeNode Bl = B.left;
        B.left = A;
        A.right = Bl;
        update(A);
        update(B);
        return B;
    }

    // right right rotation
    public static TreeNode rr(TreeNode A) {
        TreeNode B = A.left;
        TreeNode Br = B.right;
        B.right = A;
        A.left = Br;
        update(A);
        update(B);
        return B;
    }

    // display tree
    public static void display(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.left != null ? root.left.val : ".");
        System.out.print("->" + root.val + "<-");
        System.out.print(root.right != null ? root.right.val : ".");
        System.out.println();

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i < 20; i++) {
            root = add(root, i);
        }
        display(root);
    }
}