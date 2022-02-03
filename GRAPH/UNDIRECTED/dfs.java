import java.util.HashSet;

public class dfs {
    // NUMBER OF ISLAND

    public int numIslands(char[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int island = 0;
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    islandDFS(grid, i, j, dir);
                    island++;
                }
            }
        }
        return island;
    }

    public static void islandDFS(char[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = '0';
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == '1') {
                islandDFS(grid, nr, nc, dir);
            }
        }
    }

    // MAX AREA OF ISLAND

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int maxArea = 0;
        int r = grid.length;
        int c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    int cislandArea = islandDFS2(grid, i, j, dir);// current island area
                    maxArea = Math.max(maxArea, cislandArea);
                }
            }
        }
        return maxArea;
    }

    public static int islandDFS2(int[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = 0;
        int count = 0;
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length && grid[nr][nc] == 1) {
                count += islandDFS2(grid, nr, nc, dir);
            }
        }
        return count + 1;
    }

    // COUNT OF SUB ISLAND

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int ans = 0;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1) {
                    boolean isSubIsland = subIslandDfs(grid1, grid2, i, j, dir);
                    if (isSubIsland) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static boolean subIslandDfs(int[][] grid1, int[][] grid2, int sr, int sc, int[][] dir) {
        boolean ans = true;
        if (grid2[sr][sc] > grid1[sr][sc]) {
            ans = false;
        }
        grid2[sr][sc] = 0;
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nc >= 0 && nr < grid1.length
                    && nc < grid1[0].length && grid2[nr][nc] == 1) {
                boolean res = subIslandDfs(grid1, grid2, nr, nc, dir);
                ans = (ans && res);
            }
        }
        return ans;
    }

    public static void typeOfCycle(int[][] graph) {
        boolean bipartite = isBipartite(graph);
        if (!bipartite) {
            System.out.println("false");
            return;
        }
        boolean cycle = false;// call for cycle in graph
        if (cycle) {
            System.out.println("Even length cycle");
        } else {
            System.out.println("Non cyclic");
        }
    }

    // BIPARTITE

    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];// 0->set2,1->set1
        boolean res = true;
        Arrays.fill(vis, -1);
        for (int i = 0; i < n; i++) {
            if (vis[i] == -1) {
                res = res && bipartiteDfs(graph, i, vis, 0);
            }
        }
        return res;
    }

    public static boolean bipartiteDfs(int[][] graph, int src, int[] vis, int color) {
        vis[src] = color;
        boolean res = true;
        for (int e : graph[src]) {
            if (vis[e] == -1) {
                res = res && bipartiteDfs(graph, e, vis, (color + 1) % 2);
            } else if (vis[e] == color) {
                return false;
            }
        }
        return res;
    }

    // DISTINCT ISLAND

    public int numDistinctIslands(int[][] grid) {

        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        String[] sdir = { "D", "R", "U", "L" };
        HashSet<String> island = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder structure = getStructure(grid, i, j, dir, sdir);
                    island.add(structure.toString());
                }
            }
        }
        return island.size();
    }

    public static StringBuilder getStructure(int[][] grid, int sr, int sc, int[][] dir, String[] sdir) {
        grid[sr][sc] = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int nr = sr + dir[i][0];
            int nc = sc + dir[i][1];
            if (nr >= 0 && nc >= 0 && nr < grid.length
                    && nc < grid[0].length && grid[nr][nc] == 1) {
                StringBuilder recAns = getStructure(grid, nr, nc, dir, sdir);
                ans.append(sdir[i]);
                ans.append(recAns);
            }
        }
        ans.append("B");
        return ans;
    }

    // ISLAND PERIMETER

    public int islandPerimeter(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int ans = 0;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;
                    for (int[] d : dir) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                            ans--;
                        }
                    }
                }
            }
        }
        return ans;
    }

    // SURROUNDED REGION
    public void solve(char[][] board) {
        int r = board.length;
        int c = board[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == r - 1 || j == c - 1) {
                    if (board[i][j] == 'O') {
                        markFlowingDfs(board, i, j, dir);
                    }
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void markFlowingDfs(char[][] board, int sr, int sc, int[][] dir) {
        board[sr][sc] = '.';
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'O') {
                markFlowingDfs(board, nr, nc, dir);
            }
        }
    }

    // NUMBER OF ENCLAVES

    public int numEnclaves(int[][] board) {
        int r = board.length;
        int c = board[0].length;
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == r - 1 || j == c - 1) {
                    if (board[i][j] == 1) {
                        markFlowingDfs(board, i, j, dir);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void markFlowingDfs(int[][] board, int sr, int sc, int[][] dir) {
        board[sr][sc] = -1;
        for (int[] d : dir) {
            int nr = sr + d[0];
            int nc = sc + d[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 1) {
                markFlowingDfs(board, nr, nc, dir);
            }
        }
    }

}