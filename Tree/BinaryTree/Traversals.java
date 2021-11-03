import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

}
