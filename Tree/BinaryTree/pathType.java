import java.util.ArrayList;

public class pathType {
    // min
    public int min(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(Math.min(min(root.left), min(root.right)), root.val);
    }

    // max
    public int max(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(Math.max(max(root.left), max(root.right)), root.val);
    }

    // size
    public int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    // height
    public int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // find
    public boolean find(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.val == data) {
            return true;
        }
        boolean res = false;
        res = res || find(root.left, data) || find(root.right, data);
        return res;
    }

    // ROOT TO NODE PATH

    public List<TreeNode> rootToNodePath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.val == data) {
            List<TreeNode> base = new ArrayList<>();
            base.add(root);
            return;
        }
        List<TreeNode> left = rootToNodePath(root.left, data);
        if (left.size() > 0) {
            left.add(root);
            return left;
        }
        List<TreeNode> right = rootToNodePath(root.right, data);
        if (right.size() > 0) {
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    public int rootToNodeDistance(TreeNode root, int data) {
        if (root == null) {
            return -1;
        }
        if (root.val == data) {
            return 1;
        }
        int left = rootToNodeDistance(root.left, data);
        if (left != -1) {
            return left + 1;
        }
        int right = rootToNodeDistance(root.right, data);
        if (right != -1) {
            return right + 1;
        }
        return -1;
    }

    // DIAMETER OF TREE

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        int ld = diameterOfBinaryTree(root.left);
        int rd = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(ld, rd), (lh + rh + 2));
    }

    static int mdia = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int lh = diameterOfBinaryTree(root.left);
        int rh = diameterOfBinaryTree(root.right);
        mdia = Math.max(mdia, lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }

    public int[] diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new int[] { -1, 0 };
        }
        int[] left = diameterOfBinaryTree(root.left);
        int[] right = diameterOfBinaryTree(root.right);
        int[] ans = new int[2];
        ans[0] = Math.max(left[0], right[0]) + 1;
        ans[1] = Math.max(Math.max(left[1], right[1]), left[0] + right[0] + 2);
        return ans;
    }

}