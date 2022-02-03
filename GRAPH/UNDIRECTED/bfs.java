import java.util.*;

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
}