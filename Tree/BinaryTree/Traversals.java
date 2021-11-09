import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class Traversals {
    // public class TreeNode {
    // int val;
    // TreeNode left;
    // TreeNode right;
    // }

    // PREORDRE TRAVERSAL

    public List<Integer> preOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        List<Integer> left = preOrder(root.left);
        List<Integer> right = preOrder(root.right);
        ans.add(root.val);
        ans.addAll(left);
        ans.addAll(right);
        return ans;
    }

    // INORDER

    public List<Integer> inOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        List<Integer> left = inOrder(root.left);
        List<Integer> right = inOrder(root.right);
        ans.addAll(left);
        ans.add(root.val);
        ans.addAll(right);
        return ans;
    }

    // POST ORDER

    public List<Integer> postOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        List<Integer> left = postOrder(root.left);
        List<Integer> right = postOrder(root.right);
        ans.addAll(left);
        ans.addAll(right);
        ans.add(root.val);
        return ans;
    }

    public class travel {
        TreeNode node = null;
        boolean sd = false;
        boolean ld = false;
        boolean rd = false;

        public travel(TreeNode node) {
            this.node = node;
        }
    }
    // PREORDER

    public List<Integer> preOrderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<travel> st = new Stack<>();
        st.push(new travel(root));

        while (st.size() != 0) {
            travel top = st.peek();
            if (!top.sd) {
                ans.add(top.node.val);
                top.sd = true;
            } else if (!top.ld) {
                if (top.node.left != null)
                    st.push(new travel(top.node.left));
                top.ld = true;
            } else if (!top.rd) {
                if (top.node.right != null)
                    st.push(new travel(top.node.right));
                top.rd = true;
            } else {
                st.pop();
            }
        }
        return ans;
    }

    // INORDER
    public List<Integer> inOrderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<travel> st = new Stack<>();
        st.push(new travel(root));

        while (st.size() != 0) {
            travel top = st.peek();
            if (!top.ld) {
                if (top.node.left != null)
                    st.push(new travel(top.node.left));
                top.ld = true;
            } else if (!top.sd) {
                ans.add(top.node.val);
                top.sd = true;
            } else if (!top.rd) {
                if (top.node.right != null)
                    st.push(new travel(top.node.right));
                top.rd = true;
            } else {
                st.pop();
            }
        }
        return ans;
    }

    // POSTORDER
    public List<Integer> postOrderStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<travel> st = new Stack<>();
        st.push(new travel(root));

        while (st.size() != 0) {
            travel top = st.peek();
            if (!top.ld) {
                if (top.node.left != null)
                    st.push(new travel(top.node.left));
                top.ld = true;
            } else if (!top.rd) {
                if (top.node.right != null)
                    st.push(new travel(top.node.right));
                top.rd = true;
            } else if (!top.sd) {
                ans.add(top.node.val);
                top.sd = true;
            } else {
                st.pop();
            }
        }
        return ans;
    }
    // MORRIS INORDER

    public List<Integer> inOrderMorris(TreeNode root) {
        TreeNode node = root;
        List<Integer> ans = new ArrayList<>();
        while (node != null) {
            if (node.left == null) {
                ans.add(node.val);
                node = node.right;
            } else {
                TreeNode rm = node.left;
                while (rm.right != null && rm.right != node) {
                    rm = rm.right;
                }
                if (rm.right == null) {// no backedge
                    rm.right = node;
                    node = node.left;
                } else {
                    ans.add(node.val);
                    rm.right = null;
                    node = node.right;
                }
            }
        }
        return ans;
    }

    // MORRIS PREORDER

    public List<Integer> preOrderMorris(TreeNode root) {
        TreeNode node = root;
        List<Integer> ans = new ArrayList<>();
        while (node != null) {
            if (node.left == null) {
                ans.add(node.val);
                node = node.right;
            } else {
                TreeNode rm = node.left;
                while (rm.right != null && rm.right != node) {
                    rm = rm.right;
                }
                if (rm.right == null) {// no backedge
                    ans.add(node.val);
                    rm.right = node;
                    node = node.left;
                } else {
                    rm.right = null;
                    node = node.right;
                }
            }
        }
        return ans;
    }

    // POST ORDER USING PREORDER MORRIS

    public List<Integer> postOrderMorris(TreeNode root) {
        TreeNode node = root;
        List<Integer> ans = new ArrayList<>();
        while (node != null) {
            if (node.right == null) {
                ans.add(node.val);
                node = node.left;
            } else {
                TreeNode rm = node.right;
                while (rm.left != null && rm.left != node) {
                    rm = rm.left;
                }
                if (rm.left == null) {// no backedge
                    ans.add(node.val);
                    rm.left = node;
                    node = node.right;
                } else {
                    rm.left = null;
                    node = node.left;
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    // LEVEL ORDER ITERATIVE
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int level = 0;
        while (que.size() > 0) {
            int size = que.size();
            List<Integer> sans = new ArrayList<>();
            while (size-- > 0) {
                TreeNode top = que.poll();// remove
                sans.add(top.val);
                if (top.left != null)
                    que.add(top.left);
                if (top.right != null)
                    que.add(top.right);
            }
            level++;
            ans.add(sans);
        }
        return ans;
    }

    // LEVEL ORDER USING RECURSION

    public List<List<Integer>> levelOrder(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        levelOrderHelper(root, 0, map);
        List<List<Integer>> ans = new ArrayList<>();
        int l = 0;
        while (map.containsKey(l)) {
            ans.add(map.get(l));
            l++;
        }
        for (int key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

    public void levelOrderHelper(TreeNode root, int level, HashMap<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(level)) {
            map.put(level, new ArrayList<>());
        }
        map.get(level).add(root.val);
        levelOrderHelper(root.left, level + 1, map);
        levelOrderHelper(root.right, level + 1, map);
    }

    public void levelOrderHelper(TreeNode root, int level, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        if (ans.size() == level) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        levelOrderHelper(root.left, level + 1, map);
        levelOrderHelper(root.right, level + 1, map);
    }

    // LEVEL ORDER ZIGZAG

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        boolean pfltr = true;// put from left rto right
        while (st.size() > 0) {
            int size = st.size();
            Stack<TreeNode> help = new Stack<>();
            List<Integer> sans = new ArrayList<>();
            while (size-- > 0) {
                TreeNode top = st.pop();
                sans.add(top.val);
                if (pfltr) {
                    if (top.left != null)
                        help.push(top.left);
                    if (top.right != null)
                        help.push(top.right);
                } else {
                    if (top.right != null)
                        help.push(top.right);
                    if (top.left != null)
                        help.push(top.left);
                }
            }
            ans.add(sans);
            st = help;
            pfltr = !pfltr;
        }
        return ans;
    }

    // VERTICAL ORDER

    public class pair {
        int l = 0;// level
        int v = 0;// value

        public pair(int l, int v) {
            this.l = l;
            this.v = v;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<pair>> map = new HashMap<>();
        int[] min = new int[1];
        verticalTraversalHelper(root, 0, 0, min, map);
        for (int key : map.keySet()) {
            List<pair> list = map.get(key);
            Collections.sort(list, (a, b) -> {
                if (a.l == b.l)
                    return a.v - b.v;
                else
                    return a.l - b.l;
            });
        }
        int m = min[0];
        List<List<Integer>> ans = new ArrayList<>();
        while (map.containsKey(m)) {
            List<pair> list = map.get(m);
            List<Integer> sans = new ArrayList<>();
            for (pair p : list) {
                sans.add(p.v);
            }
            ans.add(sans);
            m++;
        }
        return null;
    }

    public void verticalTraversalHelper(TreeNode root, int level, int width, int[] min,
            HashMap<Integer, List<pair>> map) {
        if (root == null) {
            return;
        }
        if (min[0] > width) {
            min[0] = width;
        }
        if (!map.containsKey(width)) {
            map.put(width, new ArrayList<>());
        }
        map.get(width).add(new pair(level, root.val));
        verticalTraversalHelper(root.left, level + 1, width - 1, min, map);
        verticalTraversalHelper(root.right, level + 1, width + 1, min, map);
    }

    //

    public List<List<Integer>> diagonalTraversal(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] min = new int[1];
        diagonalTraversalHelper(root, 0, 0, min, map);
        List<List<Integer>> ans = new ArrayList<>();
        int d = min[0];
        while (map.containsKey(d)) {
            ans.add(map.get(d));
            d += 2;
        }
        return ans;
    }

    public void diagonalTraversalHelper(TreeNode root, int level, int width, int[] min,
            HashMap<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        int sum = width + level;
        if (!map.containsKey(sum)) {
            map.put(sum, new ArrayList<>());
        }
        map.get(sum).add(root.val);
        if (min[0] < width) {
            min[0] = width;
        }
        diagonalTraversalHelper(root.left, level + 1, width - 1, map);
        diagonalTraversalHelper(root.right, level + 1, width + 1, map);
    }

    // BOUNDARY TRAVERSALS

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        leftBoundary(root.left, ans);
        leaves(root.left, ans);
        leaves(root.right, ans);
        rightBoundary(root.right, ans);
        return ans;
    }

    public void leftBoundary(TreeNode root, List<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        ans.add(root.val);
        if (root.left != null) {
            leftBoundary(root.left, ans);
        } else {
            leftBoundary(root.right, ans);
        }
    }

    public void rightBoundary(TreeNode root, List<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right != null) {
            rightBoundary(root.right, ans);
        } else {
            rightBoundary(root.left, ans);
        }
        ans.add(root.val);
    }

    public void leaves(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return;
        }
        leaves(root.left, ans);
        leaves(root.right, ans);
    }

    // VERTICAL ORDER LEVELORDER

    public class Pair {
        TreeNode node = null;
        int state = 0;// level or width

        public Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, List<Pair>> map = new HashMap<>();// Pair->val,level
        Queue<Pair> que = new LinkedList<>();// pair->val,width
        que.add(new Pair(root, 0));
        int level = 0;
        int min = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                Pair top = que.poll();// val,width
                min = Math.min(min, top.state);
                if (!map.containsKey(top.state)) {
                    map.put(top.state, new ArrayList<>());
                }
                map.get(top.state).add(new Pair(top.node, level));
                if (top.node.left != null) {
                    que.add(new Pair(top.node.left, top.state - 1));
                }
                if (top.node.right != null) {
                    que.add(new Pair(top.node.right, top.state + 1));
                }
            }
            level++;
        }
        for (int key : map.keySet()) {
            List<Pair> list = map.get(key);
            Collections.sort(list, (a, b) -> {
                if (a.state == b.state)
                    return a.node.val - b.node.val;
                else
                    return a.state - b.state;
            });
        }
        List<List<Integer>> ans = new ArrayList<>();
        int m = min;
        while (map.containsKey(m)) {
            List<Pair> list = map.get(m);
            List<Integer> sans = new ArrayList<>();
            for (Pair p : list) {
                sans.add(p.node.val);
            }
            ans.add(sans);
            m++;
        }
        return ans;
    }

    // TREE FROM PRE AND IN

    public TreeNode buildTree(int[] pre, int[] in) {
        return preInTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode preInTree(int[] pre, int ps, int pe, int[] in, int is, int ie) {
        if (ps > pe) {
            return null;
        }
        TreeNode node = new TreeNode(pre[ps]);
        int i = 0;
        for (i = is; i <= ie; i++) {
            if (in[i] == pre[ps]) {
                break;
            }
        }
        int lcount = i - is;
        node.left = preInTree(pre, ps + 1, ps + lcount, in, is, i - 1);
        node.right = preInTree(pre, ps + lcount + 1, pe, in, i + 1, ie);
        return node;
    }

    // OPTIMIZED PRE AND IN TREE

    public TreeNode buildTree(int[] pre, int[] in) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return preInTree(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public TreeNode preInTree(int[] pre, int ps, int pe, int[] in, int is, int ie, HashMap<Integer, Integer> map) {
        if (ps > pe) {
            return null;
        }
        TreeNode node = new TreeNode(pre[ps]);
        int i = map.get(pre[ps]);

        int lcount = i - is;
        node.left = preInTree(pre, ps + 1, ps + lcount, in, is, i - 1, map);
        node.right = preInTree(pre, ps + lcount + 1, pe, in, i + 1, ie, map);
        return node;
    }

    // TREE FROM IN AND POST

    public TreeNode buildTree(int[] in, int[] post) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return postInTree(post, 0, post.length - 1, in, 0, in.length - 1, map);
    }

    public TreeNode postInTree(int[] post, int ps, int pe, int[] in, int is, int ie, HashMap<Integer, Integer> map) {
        if (ps > pe) {
            return null;
        }
        TreeNode node = new TreeNode(post[pe]);
        int i = map.get(post[pe]);

        int lcount = i - is;
        node.left = postInTree(post, ps, ps + lcount - 1, in, is, i - 1, map);
        node.right = postInTree(post, ps + lcount, pe - 1, in, i + 1, ie, map);
        return node;
    }

    // PRE POST TREE

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return prePostTree(pre, 0, post.length - 1, post, 0, pre.length - 1, map);
    }

    public TreeNode prePostTree(int[] pre, int ps, int pe, int[] post, int pts, int pte,
            HashMap<Integer, Integer> map) {
        if (pts > pte) {
            return null;
        }
        TreeNode node = new TreeNode(pre[ps]);
        if (pts == pte) {
            return node;
        }
        int i = map.get(pre[ps + 1]);

        int lcount = i - pts + 1;
        node.left = prePostTree(pre, ps + 1, ps + lcount, post, pts, pts + lcount - 1, map);
        node.right = prePostTree(pre, ps + lcount + 1, pe, post, pts + lcount, pte - 1, map);
        return node;
    }

    // LEVEL IN TREE

    TreeNode buildTree(int in[], int level[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return levelInTree(level, in, 0, in.length - 1, map);
    }

    public TreeNode levelInTree(int[] level, int[] in, int is, int ie, HashMap<Integer, Integer> map) {
        if (is > ie) {
            return null;
        }
        TreeNode node = new TreeNode(level[0]);
        int idx = map.get(level[0]);
        int lcount = idx - is;
        int[] llevel = new int[idx - is];
        int[] rlevel = new int[ie - idx];
        int ll = 0;
        int rl = 0;
        for (int i = 1; i < level.length; i++) {
            int iidx = map.get(level[i]);
            if (iidx < idx && iidx >= is) {// lie in left
                llevel[ll] = level[i];
                ll++;
            } else {// lie in right
                rlevel[rl] = level[i];
                rl++;
            }
        }
        node.left = levelInTree(level, in, is, idx - 1, map);
        node.right = levelInTree(level, in, idx + 1, ie, map);
        return node;
    }

    // LEFT VIEW
    public List<Integer> leftSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        List<Integer> ans = new ArrayList<>();
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                TreeNode top = que.poll();

                if (top.left != null) {
                    que.add(top.left);
                }
                if (top.right != null) {
                    que.add(top.right);
                }
                if (size == 0) {
                    ans.add(top.val);
                }
            }
        }
        return ans;
    }

    // RIGHT VIEW
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        List<Integer> ans = new ArrayList<>();
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                TreeNode top = que.poll();
                if (top.right != null) {
                    que.add(top.right);
                }
                if (top.left != null) {
                    que.add(top.left);
                }
                if (size == 0) {
                    ans.add(top.val);
                }
            }
        }
        return ans;
    }

}
