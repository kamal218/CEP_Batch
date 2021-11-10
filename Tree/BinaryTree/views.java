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