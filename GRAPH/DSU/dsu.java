public class dsu {
    int[] rank;
    int[] par;

    public dsu(int size) {
        rank = new int[size];
        par = new int[size];
        for (int i = 0; i < size; i++) {
            par[i] = i;
        }
    }

    public int find(int u) {
        return par[u] == u ? u : (par[u] = find(par[u]));
    }

    public int union(int u, int v) {
        int l1 = find(u);
        int l2 = find(v);
        if (l1 != l2) {
            par[l2] = l1;
        } else {
            return -1;
        }
        return 1;
    }
}