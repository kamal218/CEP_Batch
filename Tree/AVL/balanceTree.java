public class balanceTree {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = balanceBST(root.left);
        root.right = balanceBST(root.right);
        root = balanceTree(root);
        return root;
    }

    // balance if needed
    public TreeNode balanceTree(TreeNode root) {
        int balf = getBalFac(root);
        if (balf == 2) {
            int balfleft = getBalFac(root.left);
            if (balfleft == 1) {// rr
                return rr(root);
            } else {// lr
                root.left = ll(root.left);
                return rr(root);
            }
        } else if (balf == -2) {
            int balfright = getBalFac(root.right);
            if (balfright == 1) {// rl
                root.right = rr(root.right);
                return ll(root);
            } else {// ll
                return ll(root);
            }
        }
        return root;
    }

    public int getBalFac(TreeNode root) {
        return height(root.left) - height(root.right);
    }

    public int height(TreeNode root) {
        return root == null ? 0 : Math.max(height(root.left), height(root.right) + 1);
    }
}