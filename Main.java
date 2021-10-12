import java.util.*;

public class Main {
    public static void main(String[] args) {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        long t1 = System.currentTimeMillis();
        solve(board);
        long t2 = System.currentTimeMillis();
        for (char[] ar : board) {
            for (char ch : ar) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println(t2 - t1);
    }

    public static void solve(char[][] board) {
        // sudoku_(board, 0);
        // ArrayList<Integer> dots = new ArrayList<>();
        // for (int i = 0; i < board.length; i++) {
        // for (int j = 0; j < board[0].length; j++) {
        // if (board[i][j] == '.') {
        // dots.add(i * 9 + j);
        // }
        // }
        // }
        // sudoku02(board, 0, dots);
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] mat = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num - 1] = true;
                    col[j][num - 1] = true;
                    mat[((i / 3) * 3) + ((num - 1) / 3)][((j / 3) * 3) + ((num - 1) % 3)] = true;
                }
            }
        }
        sudoku03(board, 0, row, col, mat);
    }

    public static boolean sudoku_(char[][] board, int idx) {
        if (idx == 81) {
            return true;
        }
        int r = idx / 9;
        int c = idx % 9;
        if (board[r][c] != '.') {
            return sudoku_(board, idx + 1);
        } else {
            for (int num = 1; num <= 9; num++) {
                if (isValid(board, r, c, num)) {
                    board[r][c] = (char) (num + '0');
                    boolean res = sudoku_(board, idx + 1);
                    if (res) {
                        return true;
                    }
                    board[r][c] = '.';
                }
            }
        }
        return false;
    }

    public static boolean isValid(char[][] board, int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] - '0' == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num) {
                return false;
            }
        }
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[sr + i][sc + j] - '0' == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudoku02(char[][] board, int idx, ArrayList<Integer> dots) {
        if (idx == dots.size()) {
            return true;
        }
        int next = dots.get(idx);
        int r = next / 9;
        int c = next % 9;
        for (int num = 1; num < 10; num++) {
            if (isValid(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                boolean res = sudoku02(board, idx + 1, dots);
                if (res) {
                    return res;
                }
                board[r][c] = '.';
            }
        }
        return false;
    }

    public static boolean sudoku03(char[][] board, int idx, boolean[][] row, boolean[][] col, boolean[][] mat) {
        if (idx == 81) {
            return true;
        }
        int r = idx / 9;
        int c = idx % 9;
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        if (board[r][c] != '.') {
            return sudoku03(board, idx + 1, row, col, mat);
        } else {
            for (int num = 1; num < 10; num++) {
                int nr = ((num - 1) / 3);
                int nc = ((num - 1) % 3);
                if (!row[r][num - 1] && !col[c][num - 1] && !mat[sr + nr][sc + nc]) {
                    row[r][num - 1] = true;
                    col[c][num - 1] = true;
                    mat[sr + nr][sc + nc] = true;
                    board[r][c] = (char) (num + '0');
                    boolean res = sudoku03(board, idx + 1, row, col, mat);
                    if (res) {
                        return res;
                    }
                    board[r][c] = '.';
                    row[r][num - 1] = false;
                    col[c][num - 1] = false;
                    mat[sr + nr][sc + nc] = false;
                }
            }
        }
        return false;
    }
}