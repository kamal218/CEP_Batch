import java.util.*;

public class queenplace {
    public static void main(String[] args) {
        // int ans = queencombination(7, 0, 3, 0, "");
        // int ans = queencombinationSubseq(7, 0, 3, 0, "");
        // int ans = queenpermutation(7, 0, 3, 0, "", new boolean[7]);
        // int ans = queenpermutationSubseq(7, 0, 3, 0, "", new boolean[7]);
        int r = 4;
        int c = 4;
        // int ans = queencombination2D(r * c, 0, 4, 0, "", c);
        // int ans = queencombinationSubseq2D(r*c, 0, 4, 0, "",c);
        // int ans = queenpermutation2D(r*c, 0, 4, 0, "", new boolean[16],c);
        // int ans = queenpermutationSubseq2D(r * c, 0, 4, 0, "", new boolean[16], c);
        // System.out.println(ans);
    }

    // public static int queenpermutation(int tnb, int bno, int tnq, int qpsf,
    // String ans) {

    // }

    public static int queencombination(int tnb, int bno, int tnq, int qpsf, String ans) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int b = bno; b < tnb; b++) {
            count += queencombination(tnb, b + 1, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queencombinationSubseq(int tnb, int bno, int tnq, int qpsf, String ans) {
        if (tnq == qpsf || bno == tnb) {
            if (tnq == qpsf) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += queencombinationSubseq(tnb, bno + 1, tnq, qpsf + 1, ans + "b" + bno + "q" + qpsf + " ");
        count += queencombinationSubseq(tnb, bno + 1, tnq, qpsf, ans);

        return count;
    }

    public static int queenpermutation(int tnb, int bno, int tnq, int qpsf, String ans, boolean[] vis) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int b = 0; b < tnb; b++) {
            if (vis[b] == false) {
                vis[b] = true;
                count += queenpermutation(tnb, b + 1, tnq, qpsf + 1, ans + "b" + b + "q" + qpsf + " ", vis);
                vis[b] = false;
            }

        }
        return count;
    }

    public static int queenpermutationSubseq(int tnb, int bno, int tnq, int qpsf, String ans, boolean[] vis) {
        if (tnq == qpsf || bno == tnb) {
            if (tnq == qpsf) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (vis[bno] == false) {
            vis[bno] = true;
            count += queenpermutationSubseq(tnb, 0, tnq, qpsf + 1, ans + "b" + bno + "q" + qpsf + " ", vis);
            vis[bno] = false;
        }
        count += queenpermutationSubseq(tnb, bno + 1, tnq, qpsf, ans, vis);

        return count;
    }

    public static int queencombination2D(int tnb, int bno, int tnq, int qpsf, String ans, int col) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int b = bno; b < tnb; b++) {
            int r = b / col;
            int c = b % col;
            count += queencombination2D(tnb, b + 1, tnq, qpsf + 1, ans + "r" + r + ",c->" + c + "q" + qpsf + " ", col);
        }
        return count;
    }

    public static int queencombinationSubseq2D(int tnb, int bno, int tnq, int qpsf, String ans, int col) {
        if (tnq == qpsf || bno == tnb) {
            if (tnq == qpsf) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = bno / col;
        int c = bno % col;
        count += queencombinationSubseq2D(tnb, bno + 1, tnq, qpsf + 1, ans + "r" + r + ",c->" + c + "q" + qpsf + " ",
                col);
        count += queencombinationSubseq2D(tnb, bno + 1, tnq, qpsf, ans, col);

        return count;
    }

    public static int queenpermutation2D(int tnb, int bno, int tnq, int qpsf, String ans, boolean[] vis, int col) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int b = 0; b < tnb; b++) {
            if (vis[b] == false) {
                vis[b] = true;
                int r = b / col;
                int c = b % col;
                count += queenpermutation2D(tnb, b + 1, tnq, qpsf + 1, ans + "r" + r + ",c->" + c + "q" + qpsf + " ",
                        vis, col);
                vis[b] = false;
            }

        }
        return count;
    }

    public static int queenpermutationSubseq2D(int tnb, int bno, int tnq, int qpsf, String ans, boolean[] vis,
            int col) {
        if (tnq == qpsf || bno == tnb) {
            if (tnq == qpsf) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        int r = bno / col;
        int c = bno % col;
        if (vis[bno] == false) {
            vis[bno] = true;
            count += queenpermutationSubseq2D(tnb, 0, tnq, qpsf + 1, ans + "r" + r + ",c->" + c + "q" + qpsf + " ", vis,
                    col);
            vis[bno] = false;
        }
        count += queenpermutationSubseq2D(tnb, bno + 1, tnq, qpsf, ans, vis, col);

        return count;
    }
}
