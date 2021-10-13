public class crossword {
    public static void main(String[] args) {
        char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' },
                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

        String[] words = { "agra", "norway", "england", "gwalior" };
        crossWord(board, words);
    }

    public static void crossWord(char[][] board, String[] word) {
        crosswordhelper(board, word, 0);
    }

    public static void crosswordhelper(char[][] board, String[] words, int idx) {
        if (idx == words.length) {
            for (char[] ar : board) {
                for (char ch : ar) {
                    System.out.print(ch + " ");
                }
                System.out.println();
            }
            return;
        }
        String cword = words[idx];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '-') {
                    if (canPlaceVertically(board, i, j, cword)) {
                        boolean[] pbm = placeWordVertically(board, i, j, cword);
                        crosswordhelper(board, words, idx + 1);
                        unplaceWordVertically(board, i, j, cword, pbm);
                    }
                    if (canPlaceHorizontally(board, i, j, cword)) {
                        boolean[] pbm = placeWordHorizontally(board, i, j, cword);
                        crosswordhelper(board, words, idx + 1);
                        unplaceWordHorizontally(board, i, j, cword, pbm);
                    }
                }
            }
        }
    }

    public static boolean canPlaceVertically(char[][] board, int i, int j, String word) {
        if (i - 1 >= 0 && board[i - 1][j] != '+') {
            return false;
        }

        if (i + word.length() < board[0].length && board[i + word.length()][j] != '+') {
            return false;
        }
        for (int k = 0; k < word.length(); k++) {
            if (board[i + k][j] == '+') {
                return false;
            }
            if (board[i + k][j] == '-' || board[i + k][j] == word.charAt(k)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeWordVertically(char[][] board, int i, int j, String word) {
        boolean[] pbm = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (board[i + k][j] == '-') {
                pbm[k] = true;
                board[i + k][j] = word.charAt(k);
            }
        }
        return pbm;
    }

    public static boolean[] placeWordHorizontally(char[][] board, int i, int j, String word) {
        boolean[] pbm = new boolean[word.length()];
        for (int k = 0; k < word.length(); k++) {
            if (board[i][j + k] == '-') {
                pbm[k] = true;
                board[i][j + k] = word.charAt(k);
            }
        }
        return pbm;
    }

    public static void unplaceWordVertically(char[][] board, int i, int j, String word, boolean[] pbm) {
        for (int k = 0; k < word.length(); k++) {
            if (pbm[k] == true) {
                board[i + k][j] = '-';
            }
        }
    }

    public static boolean canPlaceHorizontally(char[][] board, int i, int j, String word) {
        if (j - 1 >= 0 && board[i][j - 1] != '+') {
            return false;
        }

        if (j + word.length() < board.length && board[i][j + word.length()] != '+') {
            return false;
        }
        for (int k = 0; k < word.length(); k++) {
            if (board[i][j + k] == '+') {
                return false;
            }
            if (board[i][j + k] == '-' || board[i][j + k] == word.charAt(k)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void unplaceWordHorizontally(char[][] board, int i, int j, String word, boolean[] pbm) {
        for (int k = 0; k < word.length(); k++) {
            if (pbm[k] == true) {
                board[i][j + k] = '-';
            }
        }
    }
}
