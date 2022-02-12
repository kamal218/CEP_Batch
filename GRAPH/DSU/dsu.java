public class dsu {
    int[] rank;
    int[] par;

    public dsu(int size) {
        rank = new int[size];
        par = new int[size];
        for (int i = 0; i < size; i++) {
            par[i] = -1;
        }
    }

    public int find(int u) {
        return par[u] == u ? u : (par[u] = find(par[u]));
    }

    public int union(int u, int v) {
        int l1 = find(u);
        int l2 = find(v);
        if (l1 != l2) {
            int r1 = rank[l1];
            int r2 = rank[l2];
            if (r1 > r2) {
                par[l2] = l1;
            } else if (r1 < r2) {
                par[l1] = l2;
            } else {
                par[l1] = l2;
                rank[l2]++;
            }
        } else {
            return -1;
        }
        return 1;
    }

    

}