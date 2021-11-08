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
        rightBoundary(root.right,ans);
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
        if(root==null){
            return ;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val);
            return;
        }
        leaves(root.left, ans);
        leaves(root.right, ans);
    }

}
