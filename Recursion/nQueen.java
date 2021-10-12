public class nQueen {
    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        // boolean[][] board = new boolean[4][4];
        // nQueens(board, 0, 4, "");
        // nQueens02(0, 4, "", new boolean[4], new boolean[4], new boolean[7], new
        // boolean[7]);
        // nQueens03(0, 4, "", new boolean[4], new boolean[4], new boolean[7], new boolean[7]);
        long et = System.currentTimeMillis();
        System.out.println(et - st);
    }

    public static boolean nQueens(boolean[][] board, int bno, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return true;
        }
        boolean res = false;
        for (int i = bno; i < board.length * board[0].length; i++) {
            int r = i / board[0].length;
            int c = i % board[0].length;
            if (isValidToPlace(board, r, c)) {
                board[r][c] = true;
                res = res || nQueens(board, i + 1, tnq - 1, ans + "r" + r + "c" + c + "\n");
                board[r][c] = false;
            }
        }
        return res;
    }

    public static boolean isValidToPlace(boolean[][] board, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 1 }, { -1, 0 } };
        for (int k = 1; k < board.length; k++) {
            for (int i = 0; i < dir.length; i++) {
                int nr = r + dir[i][0] * k;
                int nc = c + dir[i][1] * k;
                if (nr >= 0 && nc >= 0 && nr < board.length && nc < board.length) {
                    if (board[nr][nc] == true) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean nQueens02(int bno, int tnq, String ans, boolean[] row, boolean[] col, boolean[] d1,
            boolean[] d2) {
        if (tnq == 0) {
            System.out.println(ans);
            return true;
        }
        boolean res = false;
        for (int i = bno; i < row.length * row.length; i++) {
            int r = i / row.length;
            int c = i % row.length;
            if (!row[r] && !col[c] && !d1[r + c] && !d2[r - c + row.length - 1]) {
                row[r] = true;
                col[c] = true;
                d1[r + c] = true;
                d2[r - c + row.length - 1] = true;
                res = res || nQueens02(i + 1, tnq - 1, ans + "r" + r + "c" + c + "\n", row, col, d1, d2);
                row[r] = false;
                col[c] = false;
                d1[r + c] = false;
                d2[r - c + row.length - 1] = false;
            }
        }
        return res;
    }

    public static boolean nQueens03(int floor, int tnq, String ans, boolean[] row, boolean[] col, boolean[] d1,
            boolean[] d2) {
        // floor-> row
        // room-> column
        if (tnq == 0) {
            System.out.println(ans);
            return true;
        }
        boolean res = false;
        for (int room = 0; room < row.length; room++) {
            int r = floor;
            int c = room;
            if (!row[r] && !col[c] && !d1[r + c] && !d2[r - c + row.length - 1]) {
                row[r] = true;
                col[c] = true;
                d1[r + c] = true;
                d2[r - c + row.length - 1] = true;
                res = res || nQueens03(floor + 1, tnq - 1, ans + "r" + r + "c" + c + "\n", row, col, d1, d2);
                row[r] = false;
                col[c] = false;
                d1[r + c] = false;
                d2[r - c + row.length - 1] = false;
            }
        }
        return res;
    }

}
