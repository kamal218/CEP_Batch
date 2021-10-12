public class sudoku {
    public static void main(String[] args) {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        solve(board);
    }

    public static void solve(char[][] board) {
        // sudoku01(board, 0);
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] mat = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num - 1] = true;
                    col[j][num - 1] = true;
                    mat[((i / 3) * 3) + ((num - 1) / 3)][((j / 3) * 3) + (num - 1) % 3] = true;
                }
            }
        }
        sudoku02(board, 0, row, col, mat);
    }

    public static void sudoku01(char[][] board, int idx) {
        if (idx == 81) {
            for (char[] ar : board) {
                for (char ch : ar) {
                    System.out.print(ch + " ");
                }
                System.out.println();
            }
            return;
        }
        int r = idx / 9;
        int c = idx % 9;
        if (board[r][c] == '.') {
            for (int num = 1; num < 10; num++) {
                if (isValidToPlace(board, r, c, num)) {
                    board[r][c] = (char) (num + '0');
                    sudoku01(board, idx + 1);
                    board[r][c] = '.';
                }
            }
        } else {
            sudoku01(board, idx + 1);
        }
    }

    public static boolean isValidToPlace(char[][] board, int r, int c, int val) {
        for (int i = 0; i < board.length; i++) {// row check
            if (board[r][i] - '0' == val) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][c] - '0' == val) {
                return false;
            }
        }
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[sr + i][sc + j] - '0' == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void sudoku02(char[][] board, int idx, boolean[][] row, boolean[][] col, boolean[][] mat) {
        if (idx == 81) {
            for (char[] ar : board) {
                for (char ch : ar) {
                    System.out.print(ch + " ");
                }
                System.out.println();
            }
            return;
        }
        int r = idx / 9;
        int c = idx % 9;
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        if (board[r][c] == '.') {
            for (int num = 1; num < 10; num++) {
                int obr = (num - 1) / 3;
                int obc = (num - 1) % 3;
                if (!row[r][num - 1] && !col[c][num - 1] && !mat[sr + obr][sc + obc]) {
                    row[r][num - 1] = true;
                    col[c][num - 1] = true;
                    mat[sr + obr][sc + obc] = true;
                    board[r][c] = (char) (num + '0');
                    sudoku02(board, idx + 1, row, col, mat);
                    board[r][c] = '.';
                    row[r][num - 1] = false;
                    col[c][num - 1] = false;
                    mat[sr + obr][sc + obc] = false;
                }
            }
        } else {
            sudoku02(board, idx + 1, row, col, mat);
        }
    }
}
