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
