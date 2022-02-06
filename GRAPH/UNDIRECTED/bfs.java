import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.MatchResult;

public class bfs {

    // /ROTTEN ORANGES

    public int orangesRotting(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        Queue<Integer> que = new LinkedList<>();
        int r = grid.length;
        int c = grid[0].length;
        int foc = 0;// fresh orange count
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    que.add(i * c + j);
                }
                if (grid[i][j] == 1) {
                    foc++;
                }
            }
        }
        if (foc == 0) {
            return 0;
        }
        int time = 0;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                int i = top / c;
                int j = top % c;
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        que.add(nr * c + nc);
                        foc--;
                        if (foc == 0) {
                            return time + 1;
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }

    // O-1 MATRIX

    public int[][] updateMatrix(int[][] mat) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = mat.length;
        int c = mat[0].length;
        int[][] ans = new int[r][c];
        Queue<Integer> que = new LinkedList<>();
        int one = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] == 0) {
                    que.add(i * c + j);
                } else {
                    one++;
                }
            }
        }

        int level = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                int i = top / c;
                int j = top % c;
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr >= 0 && nc >= 0 && nr < r && nc < c && mat[nr][nc] == 1) {
                        ans[nr][nc] = level;
                        mat[nr][nc] = 0;
                        que.add(nr * c + nc);
                        one--;
                        if (one == 0) {
                            return ans;
                        }
                    }
                }
            }
            level++;
        }
        return ans;
    }

    // GET FOOD
    // SGNLE SOURCE

    public int getFood(char[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int level = 1;
        Queue<Integer> que = new LinkedList<>();
        a: for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '*') {
                    que.add(i * c + j);
                    grid[i][j] = 'X';
                    break a;
                }
            }
        }

        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                int i = top / c;
                int j = top % c;
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] != 'X') {
                        if (grid[nr][nc] == '#') {
                            return level;
                        }
                        que.add(nr * c + nc);
                        grid[nr][nc] = 'X';

                    }
                }
            }
            level++;
        }

        return -1;
    }

    // SIMULTANEOUS BFS

    public int getFood(char[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        int level = 1;
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '#') {
                    que.add(i * c + j);
                    grid[i][j] = 'X';
                }
            }
        }

        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                int i = top / c;
                int j = top % c;
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] != 'X') {
                        if (grid[nr][nc] == '*') {
                            return level;
                        }
                        que.add(nr * c + nc);
                        grid[nr][nc] = 'X';

                    }
                }
            }
            level++;
        }

        return -1;
    }

    // SHORTEST BINARY MATRIX PATH

    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 }, { -1, -1 } };
        int r = grid.length;
        int c = grid[0].length;
        if (r == 1 && c == 1 && grid[0][0] == 0) {
            return 1;
        }
        if (grid[0][0] == 1 || grid[r - 1][c - 1] == 1) {
            return -1;
        }
        int level = 1;
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        grid[0][0] = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int top = que.poll();
                int i = top / c;
                int j = top % c;
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];
                    if (nr >= 0 && nc >= 0 && nr < r && nc < c && grid[nr][nc] == 0) {
                        if (nr == r - 1 && nc == c - 1) {
                            return level + 1;
                        }
                        grid[nr][nc] = 1;
                        que.add(nr * c + nc);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    // BUS ROUTES

    public int numBusesToDestination(int[][] routes, int src, int tar) {
        if (src == tar) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();// stand vs bus no
        int max = 0;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int stand = routes[i][j];
                int bus = i;
                if (!map.containsKey(stand)) {
                    map.put(stand, new ArrayList<>());
                }
                map.get(stand).add(bus);
                max = Math.max(max, stand);
            }
        }

        Queue<Integer> que = new LinkedList<>();
        boolean[] busvis = new boolean[routes.length];
        boolean[] standvis = new boolean[max + 1];
        que.add(src);
        standvis[src] = true;
        int level = 1;
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                int currstand = que.poll();
                List<Integer> currstandbuses = map.get(currstand);
                for (int cbusboard : currstandbuses) {
                    if (!busvis[cbusboard]) {
                        for (int nextstop : routes[cbusboard]) {
                            if (!standvis[nextstop]) {
                                if (nextstop == tar) {
                                    return level;
                                }
                                que.add(nextstop);
                                busvis[cbusboard] = true;
                                standvis[nextstop] = true;
                            }
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
}