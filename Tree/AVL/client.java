public class client {
    public static void main(String[] args) {
        avl tree = new avl();
        tree.TreeNode root = null;
        for (int i = 1; i < 10; i++) {
            root = tree.add(root, i);
        }
        tree.display(root);
    }

    
}