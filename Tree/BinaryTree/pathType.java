import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            return base;
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

    // MAX PATH SUM
    long ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ans = Long.MIN_VALUE;
        help(root);
        return (int) ans;
    }

    public long maxPathSum_(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        long lv = maxPathSum_(root.left);
        long rv = maxPathSum_(root.right);
        ans = Math.max(ans, root.val);
        ans = Math.max(ans, Math.max(lv, rv) + root.val);
        ans = Math.max(ans, lv + rv + root.val);
        return Math.max(Math.max(lv, rv) + root.val, root.val);
    }

    public long[] maxPathSum_(TreeNode root) {// [0]return [1]ans
        if (root == null) {
            return new long[] { Integer.MIN_VALUE, Integer.MIN_VALUE };
        }
        long[] lv = help(root.left);
        long[] rv = help(root.right);
        long[] ans = new long[2];
        ans[1] = Math.max(lv[1], rv[1]);
        ans[1] = Math.max(ans[1], root.val);
        ans[1] = Math.max(ans[1], Math.max(lv[0], lv[0]) + root.val);
        ans[1] = Math.max(ans[1], lv[0] + lv[0] + root.val);
        ans[0] = Math.max(Math.max(lv[0], rv[0]) + root.val, root.val);
        return ans;
    }

    // rootToNodePath

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = rootToNodePath(root, p.val);
        List<TreeNode> list2 = rootToNodePath(root, q.val);
        int p1 = list1.size() - 1;
        int p2 = list2.size() - 1;
        TreeNode ans = null;
        while (p1 >= 0 && p2 >= 0 && p1.val == p2.val) {
            ans = p1;
            p1--;
            p2--;
        }
        return ans;
    }

    // LCA WITHOUT SPACE WITH BUG

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // LCA WITHOUT BUG
    boolean f1 = false;// p existance
    boolean f2 = false;// q existance

    public TreeNode lowestCommonAncestor(TreeNode root, reeNode p, TreeNode q) {
        TreeNode ans = lowestCommonAncestor_(root, p, q);
        if (f1 == true && f2 == true)
            return ans;
        return null;
    }

    public TreeNode lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode temp = null;
        if (root.val == p.val) {
            f1 = true;
            temp = root;
        }
        if (root.val == q.val) {
            f2 = true;
            temp = root;
        }
        TreeNode left = lowestCommonAncestor_(root.left, p, q);
        TreeNode right = lowestCommonAncestor_(root.right, p, q);
        if (temp != null) {
            return temp;
        }
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // K DOWN

    public void kDown(TreeNode ref, int k) {
        if (k == 0 || ref == null) {
            if (ref == null) {
                return;
            }
            System.out.println(ref.val);
            return;
        }
        kDown(ref.left, k - 1);
        kDown(ref.right, k - 1);
    }

    // K AWAY USING EXTRA SPACE(ROOT TO NODE PATH)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        List<TreeNode> path = rootToNodePath(root, target);
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kDown(path.get(i), block, k - i, ans);
            block = path.get(i);
        }
        return ans;

    }

    public void kDown(TreeNode ref, TreeNode block, int k, List<Integer> ans) {
        if (k == 0 || ref == null || ref == block) {
            if (ref == null || ref == block) {
                return;
            }
            ans.add(ref.val);
            return;
        }
        kDown(ref.left, block, k - 1, ans);
        kDown(ref.right, block, k - 1, ans);
    }

    // K AWAY WITHOUT EXTRA SPACE

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distanceK_(root, target.val, k, ans);
        return ans;
    }

    public int distanceK_(TreeNode root, int data, int k, List<Integer> ans) {
        if (root == null) {
            return -1;
        }
        if (root.val == data) {
            kDown(root, null, k, ans);
            return 1;
        }
        int left = distanceK_(root.left, data, k, ans);
        if (left != -1) {
            kDown(root, root.left, k - left, ans);
            return left + 1;
        }
        int right = distanceK_(root.right, data, k, ans);
        if (right != -1) {
            kDown(root, root.right, k - right, ans);
            return right + 1;
        }
        return -1;
    }

    // BURING TREE USING K AWAY

    public List<List<Integer>> burningTree(TreeNode root, TreeNode ref) {
        List<List<Integer>> ans = new ArrayList<>();
        int time = 0;
        List<Integer> sans = new ArrayList<>();
        while ((sans = distanceK(root, ref, time)).size() > 0) {
            ans.add = sans;
        }
        return ans;
    }

    // BURNING TREE WITHOUT K DOWN
    public List<List<Integer>> burningTree(TreeNode root, TreeNode ref) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        burningTree_(root, ref, map);
        List<List<Integer>> ans = new ArrayList<>();
        int st = 0;
        while (map.containsKey(st)) {
            ans.add(map.get(st));
            st++;
        }
        return ans;
    }

    public int burningTree_(TreeNode root, TreeNode ref, HashMap<Integer, List<Integer>> map) {
        if (root == null) {
            return -1;
        }
        if (root.val == ref.val) {
            allDown(root, 0, null, map);
            return 1;
        }
        int left = burningTree_(root.left, ref, map);
        if (left != -1) {
            allDown(root, left, root.left, map);
            return left + 1;
        }
        int right = burningTree_(root.right, ref, map);
        if (right != -1) {
            allDown(root, right, root.right, map);
            return right + 1;
        }
        return -1;
    }

    public void allDown(TreeNode root, int time, TreeNode blocked, HashMap<Integer, List<Integer>> map) {
        if (root == null || root == blocked) {
            return;
        }
        if (!map.containsKey(time)) {
            map.put(time, new ArrayList<>());
        }
        map.get(time).add(root.val);
        allDown(root.left, time + 1, blocked, map);
        allDown(root.right, time + 1, blocked, map);
    }

    // ALL ROOT TO LEAF PATHS

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        binaryTreePaths_(root, ans, "");
        return ans;
    }

    public int binaryTreePaths_(TreeNode root, List<String> ans, String path) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            path = path + root.val;
            ans.add(path);
            return 1;
        }
        int count = 0;
        count += binaryTreePaths_(root.left, ans, path + root.val + "->");
        count += binaryTreePaths_(root.right, ans, path + root.val + "->");
        return count;
    }

    // PATH SUM1
    public boolean hasPathSum(TreeNode root, int tar) {
        return hasPathSum_(root, tar, 0);
    }

    public boolean hasPathSum_(TreeNode root, int tar, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum + root.val == tar) {
            return true;
        }
        boolean res = false;
        res = res || hasPathSum_(root.left, tar, sum + root.val);
        res = res || hasPathSum_(root.right, tar, sum + root.val);
        return res;
    }

    // PATH SUM2
    public List<List<Integer>> pathSum(TreeNode root, int tar) {
        List<List<Integer>> ans = new ArrayList<>();
        pathSum_(root, tar, 0, ans, new ArrayList<>());
        return ans;
    }

    public void pathSum_(TreeNode root, int tar, int sum, List<List<Integer>> ans, List<Integer> sans) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && tar == sum + root.val) {
            sans.add(root.val);
            ans.add(new ArrayList<>(sans));
            sans.remove(sans.size() - 1);
            return;
        }
        sans.add(root.val);
        pathSum_(root.left, tar, sum + root.val, ans, sans);
        pathSum_(root.right, tar, sum + root.val, ans, sans);
        sans.remove(sans.size() - 1);
    }

    // PATH SUM3
    public int pathSum(TreeNode root, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();// sum vs count
        map.put(0, 1);
        return pathSum_(root, tar, 0, map);
    }

    public int pathSum_(TreeNode root, int tar, int sum, HashMap<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        sum += root.val;
        if (map.containsKey(sum - tar)) {
            count += map.get(sum - tar);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += pathSum_(root.left, tar, sum, map);
        count += pathSum_(root.right, tar, sum, map);
        map.put(sum, map.get(sum) - 1);

        return count;
    }

    // SAME TREE

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // SYMMETRIC TREE
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric_(root, root);
    }

    public boolean isSymmetric_(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSymmetric_(p.left, q.right) && isSymmetric_(p.right, q.left);
    }

    // INVERT TREE

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        TreeNode t = left;
        root.left = right;
        root.right = t;
        return root;
    }

    // CLONE TREE WITH RANDOM

    public TreeNode cloneWithRandom(TreeNode root) {
        TreeNode mix = leftClone(root);
        setRandom(mix);
        TreeNode ans = extract(root);
        return ans;
    }

    public TreeNode leftClone(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode l = leftClone(root.left);
        TreeNode r = leftClone(root.right);
        TreeNodde node = new TreeNode(root.val);
        node.left = l;
        root.left = node;
        root.right = r;
        return root;
    }

    public void setRandom(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.random != null) {
            root.left.random = root.random.left;
        }
        setRandom(root.left.left);
        setRandom(root.right);
    }

    public TreeNode extract(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tr = root.left;
        root.left = root.left.left;
        TreeNode l = extract(root.left);
        TreeNode r = extract(root.right);
        tr.left = l;
        tr.right = r;
        return tr;
    }

}
