import java.util.ArrayList;

public class questions {
    // FLATTEN BINARY TREE
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        if (root.left == null && root.right == null) {
            return;
        } else if (root.left == null) {
            return;
        } else if (root.right == null) {
            root.right = root.left;
            root.left = null;
        } else {
            TreeNode tail = getTail(root.left);
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    public TreeNode getTail(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    // FLATTEN TREE WITHOUT GET TAIL
    public void flatten(TreeNode root) {
        flatten_(root);
    }

    public TreeNode flatten_(TreeNode root) {
        if (root == null)
            return null;
        TreeNode ltail = flatten_(root.left);
        TreeNode rtail = flatten_(root.right);
        if (root.left == null && root.right == null) {
            return root;
        } else if (root.left == null) {
            return rtail;
        } else if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return ltail;
        } else {
            TreeNode tail = ltail;
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
            return rtail;
        }
    }

    // SERIALIZE AND DESERIALIZE
    public String serialize(TreeNode root) {
        if (root == null) {
            return "n";
        }
        String ans = "";
        String left = serialize(root.left);
        String right = serialize(root.right);
        ans = root.val + "#" + left + "#" + right;
        return ans;
    }

    public TreeNode deserialize(String data) {
        String[] splitted = data.split("#");
        return deserialize_(splitted);
    }

    static int ptr = 0;

    public TreeNode deserialize_(String[] arr) {
        if (arr[ptr].equals("n")) {
            ptr++;
            return null;
        }
        int val = Integer.parseInt(arr[ptr]);
        TreeNode root = new TreeNode(val);
        ptr++;
        root.left = deserialize_(arr);
        root.right = deserialize_(arr);
        return root;
    }

    // DELETE NODES AND RETURN FOREST
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : to_delete) {
            set.add(ele);
        }
        List<TreeNode> ans = new ArrayList<>();
        if (!set.contains(root.val)) {
            ans.add(root);
        }
        delNodes_(root, set, ans);
        return ans;
    }

    public TreeNode delNodes_(TreeNode root, HashSet<Integer> set, List<TreeNode> ans) {
        if (root == null) {
            return root;
        }
        root.left = delNodes_(root.left, set, ans);
        root.right = delNodes_(root.right, set, ans);
        if (set.contains(root.val)) {
            if (root.left != null) {
                ans.add(root.left);
            }
            if (root.right != null) {
                ans.add(root.right);
            }
            return null;
        }
        return root;
    }

    // ALL FULL BINARY TREE

    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 1) {
            TreeNode node = new TreeNode(0);
            ans.add(node);
            return ans;
        }
        for (int i = 1; i < n; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - i - 1);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(0);
                    node.left = l;
                    node.right = r;
                    ans.add(node);
                }
            }
        }
        return ans;
    }

    // COMPLETE BINARY TREE INSERTER
    Queue<TreeNode> que = new LinkedList<>();
    TreeNode ins = null;
    TreeNode head;

    public CBTInserter(TreeNode root) {
        que.add(root);
        head=root;
        while(true){
            TreeNode top=que.poll();
            if(top.left!=null){
                que.add(top.left);
            }else{
                ins=top;
                break;
            }
            if(top.right!=null){
                que.add(top.right);
            }else{
                ins=top;
                break;
            }
        }
    }

    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        int tr = ins.val;
        if (ins.left == null) {
            ins.left = node;
            que.add(node);
        } else {
            ins.right = node;
            que.add(node);
            ins = que.poll();
        }
        return tr;
    }

    public TreeNode get_root() {
        return head;
    }

}
