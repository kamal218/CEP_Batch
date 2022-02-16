import java.lang.Character.UnicodeScript;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class questions {

    public static class Edge {
        int v = 0;
        int wt = 0;

        public Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }

        @Override

        public String toString() {
            return "(" + v + "," + wt + ") ";
        }
    }

    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 0 }, { 4, 2 } };
        boolean cycle = cycleDetection(5, edges);
        System.out.println(cycle);
        ArrayList<Edge>[] graph = SpanningTree(5, edges);
        display(5, graph);
    }

    // CYCLE DETECTION
    public static boolean cycleDetection(int n, int[][] edges) {
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val == -1) {
                return true;
            }
        }
        return false;
    }

    // SPANNING TREE

    public static ArrayList<Edge>[] SpanningTree(int n, int[][] edges) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        init(graph, n);
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                addEdge(graph, u, v, 0);
            }
        }
        return graph;
    }

    public static void init(ArrayList<Edge>[] graph, int n) {
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(v, wt));
        graph[v].add(new Edge(u, wt));
    }

    public static void display(int n, ArrayList<Edge>[] graph) {
        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");
            for (Edge e : graph[i]) {
                // System.out.print("(" + e.v + "," + e.wt + ") ");
                System.out.print(e);
            }
            System.out.println();
        }
    }

    // GET CONNECTED COMPONENTS

    public static int gcc(int n, int[][] edges) {
        dsu uf = new dsu(n);
        int compo = n;
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                compo--;
            }
        }
        return compo;
    }

    // NUMBER OF ISLAND

    public int numIslands(char[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        dsu uf = new dsu(r * c);
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    int u = i * c + j;
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c
                                && grid[nr][nc] == '1') {
                            int v = nr * c + nc;
                            int val = uf.union(u, v);
                            if (val != -1) {// different parent
                                ans--;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int findPar(int[] par, int u) {
        return (par[u] == u ? u : (par[u] = findPar(par, par[u])));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int[] par = new int[r * c];
        int[] size = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
            size[i] = 1;
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    int u = i * c + j;
                    int l1 = findPar(par, u);
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (l1 != l2) {
                                par[l2] = l1;
                                size[l1] = size[l1] + size[l2];
                            }
                        }
                    }
                    ans = Math.max(ans, size[l1]);
                }
            }
        }
        return ans;
    }

    public int largestIsland(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int[] par = new int[r * c];
        int[] size = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
            size[i] = 1;
        }
        HashSet<Integer> leader = new HashSet<>();

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    int u = i * c + j;
                    int l1 = findPar(par, u);
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (l1 != l2) {
                                par[l2] = l1;
                                size[l1] = size[l1] + size[l2];
                            }
                        }
                    }
                    ans = Math.max(ans, size[l1]);// max area
                }
            }
        }

        int sum = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0) {
                    sum = 0;
                    leader.clear();
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            int v = nr * c + nc;
                            int l2 = findPar(par, v);
                            if (!leader.contains(l2)) {
                                sum += size[l2];
                                leader.add(l2);
                            }
                        }
                    }
                    ans = Math.max(ans, sum + 1);// max after converting
                }
            }
        }

        return ans;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = m;
        int c = n;
        int[] par = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = i;
        }
        int[][] grid = new int[r][c];
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int[] e : positions) {
            int i = e[0];
            int j = e[1];
            if (grid[i][j] == 1) {
                ans.add(count);
                continue;
            }
            grid[i][j] = 1;
            count++;
            for (int[] d : dir) {
                int nr = i + d[0];
                int nc = j + d[1];
                if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                    int u = i * c + j;
                    int v = nr * c + nc;
                    int p1 = findPar(par, u);
                    int p2 = findPar(par, v);
                    if (p1 != p2) {
                        count--;
                        par[p2] = p1;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    // WITHOUT GRID

    public List<Integer> numIslands2_(int m, int n, int[][] positions) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = m;
        int c = n;
        int[] par = new int[r * c];
        for (int i = 0; i < r * c; i++) {
            par[i] = -1;// water initially
        }
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int[] e : positions) {
            int i = e[0];
            int j = e[1];
            int u = i * c + j;
            if (par[u] != -1) {
                ans.add(count);
                continue;
            }
            par[u] = u;// marking as land
            count++;
            for (int[] d : dir) {
                int nr = i + d[0];
                int nc = j + d[1];
                if (nr >= 0 && nc >= 0 && nr < r && nc < c && par[nr * c + nc] != -1) {
                    int v = nr * c + nc;
                    int p1 = findPar(par, u);
                    int p2 = findPar(par, v);
                    if (p1 != p2) {
                        count--;
                        par[p2] = p1;
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }

    public int numSimilarGroups(String[] strs) {
        int len = strs.length;
        int[] par = new int[len];// index based mapping
        int groups = len;
        for (int i = 0; i < len; i++) {
            par[i] = i;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {// checking each pair similarity
                boolean similar = isSimilar(strs, i, j);
                if (similar) {
                    int p1 = findPar(par, i);
                    int p2 = findPar(par, j);
                    if (p1 != p2) {
                        par[p1] = p2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }

    public boolean isSimilar(String[] str, int i, int j) {
        int count = 0;
        for (int k = 0; k < str[i].length(); k++) {
            char ch1 = str[i].charAt(k);
            char ch2 = str[j].charAt(k);
            if (ch1 != ch2) {
                count++;
            }
            if (count > 2) {
                return false;
            }
        }
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        dsu uf = new dsu(n + 1);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val == -1) {
                return e;
            }
        }
        return new int[0];
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] par = new int[26];
        for (int i = 0; i < 26; i++) {
            par[i] = i;
        }
        // DSU GENERATION
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            int p1 = findPar(par, ch1 - 'a');
            int p2 = findPar(par, ch2 - 'a');
            if (p1 < p2) {
                par[p2] = p1;
            } else {
                par[p1] = p2;
            }
        }

        // ANSER GENERATION

        StringBuilder ans = new StringBuilder();

        for (char ch : baseStr.toCharArray()) {
            int idx = ch - 'a';
            int p = findPar(par, idx);
            ans.append((char) (p + 'a'));
        }
        return ans.toString();
    }

    public String findPar(HashMap<String, String> par, String u) {
        if (par.get(u).equals(u)) {
            return u;
        }
        String p = findPar(par, par.get(u));
        par.put(u, p);
        return p;
    }

    // SIMILAR SENTENCE 2
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        HashMap<String, String> par = new HashMap<>();
        if (sentence1.length != sentence2.length) {
            return false;
        }
        // dsu creation
        for (List<String> similar : similarPairs) {
            String s1 = similar.get(0);
            String s2 = similar.get(1);
            if (!par.containsKey(s1)) {
                par.put(s1, s1);
            }

            if (!par.containsKey(s2)) {
                par.put(s2, s2);
            }

            String p1 = findPar(par, s1);
            String p2 = findPar(par, s2);
            par.put(p1, p2);
        }

        // CHECK IF SIMILAR

        for (int i = 0; i < sentence1.length; i++) {
            String s1 = sentence1[i];
            String s2 = sentence2[i];

            if (s1.equals(s2)) {
                continue;
            }

            if (!par.containsKey(s1) || !par.containsKey(s2))
                return false;

            String p1 = findPar(par, s1);
            String p2 = findPar(par, s2);

            if (!p1.equals(p2)) {
                return false;
            }
        }
        return true;
    }

    // SATISFIABLILITY EQUATION
    public boolean equationsPossible(String[] equations) {
        int[] par = new int[26];
        for (int i = 0; i < par.length; i++) {
            par[i] = i;
        }

        // dsu for all equal
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                char ch1 = s.charAt(0);
                char ch2 = s.charAt(3);
                int p1 = findPar(par, ch1 - 'a');
                int p2 = findPar(par, ch2 - 'a');
                par[p1] = p2;
            }
        }

        // CHECK FOR OPPOSITE
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                char ch1 = s.charAt(0);
                char ch2 = s.charAt(3);
                int p1 = findPar(par, ch1 - 'a');
                int p2 = findPar(par, ch2 - 'a');
                if (p1 == p2) {
                    return false;
                }
            }
        }
        return true;
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> (b[0] - a[0]));
        int[] apar = new int[n + 1];
        int[] bpar = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            apar[i] = i;
            bpar[i] = i;
        }
        int remove = 0;
        int c1 = n;
        int c2 = n;
        for (int[] e : edges) {
            int type = e[0];
            int u = e[1];
            int v = e[2];
            if (type == 1) {// alice
                int p1 = findPar(apar, u);
                int p2 = findPar(apar, v);
                if (p1 == p2) {
                    remove++;
                } else {
                    apar[p1] = p2;
                    c1--;
                }
            } else if (type == 2) {// bob
                int p1 = findPar(bpar, u);
                int p2 = findPar(bpar, v);
                if (p1 == p2) {
                    remove++;
                } else {
                    bpar[p1] = p2;
                    c2--;
                }
            } else {// both
                int p1 = findPar(apar, u);
                int p2 = findPar(apar, v);
                if (p1 == p2) {
                    remove++;
                } else {
                    apar[p1] = p2;
                    bpar[p1] = p2;
                    c1--;
                    c2--;
                }
            }
        }
        if (c1 != 1 || c2 != 1) {
            return -1;
        }
        return remove;
    }

    // MALWARE SPREAD

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] par = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        // DSU CREATION
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] == 1) {
                    int p1 = findPar(par, i);
                    int p2 = findPar(par, j);
                    if (p1 != p2) {
                        par[p1] = p2;
                        size[p2] += size[p1];
                    }
                }
            }
        }
        int[] infected = new int[n];
        // INFECTED NODE IN PARTICULAR GROUP
        for (int inf : initial) {
            int p = findPar(par, inf);
            infected[p]++;
        }

        // ANSWER CREATION
        int maxnodesaved = 0;
        Arrays.sort(initial);
        int ans = initial[0];

        for (int inf : initial) {
            int p = findPar(par, inf);
            int nodesinfected = infected[p];
            int sizeofcompo = size[p];
            if (nodesinfected == 1 && sizeofcompo > maxnodesaved) {
                ans = inf;
                maxnodesaved = sizeofcompo;
            }
        }
        return ans;
    }

    // MIN COST TO CONNECT CITIES

    public int minimumCost(int n, int[][] connections) {
        int ans = 0;
        dsu uf = new dsu(n + 1);
        int compo = n;
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        for (int[] e : connections) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            int val = uf.union(u, v);
            if (val != -1) {
                ans += wt;
                compo--;
            }
        }
        if (compo != 1) {
            return -1;
        }
        return ans;
    }

}