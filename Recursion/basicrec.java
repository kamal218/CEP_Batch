import java.util.*;

public class basicrec {
    public static void main(String[] args) {
        // int[] ans = allindices(new int[] { 4, 2, 2, 5, 4, 4 }, 4, 0, 0);
        // for (int ele : ans) {
        // System.out.println(ele + " ");
        // }
        // subseq("abc", "", 0);
        // ArrayList<String> ans = subseq_("abc", 0);
        // boardPath(5, 0, "");
        // boardPathReactive(5, 0, "");
        // ArrayList<String> ans = boardPath_(5, 0);
        // mazePath(0, 0, 2, 2, "");
        // ArrayList<String> ans = mazePath_(0, 0, 2, 2);
        // boolean[][] vis = new boolean[3][3];
        // vis[0][0] = true;
        // floodFill(0, 0, 2, 2, "", vis);
        // int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { 1, 1 }, { -1, 1
        // }, { 1, 0 }, { 1, 1 } };
        int[][] dir = { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 } };
        int[][] ans = new int[5][5];
        for (int[] ar : ans) {
            Arrays.fill(ar, -1);
        }
        ans[0][0] = 0;
        knightFill(ans, dir, 0, 0, 0);
        // System.out.println(ans);
    }

    public static int[] allindices(int[] arr, int val, int i, int size) {
        if (i == arr.length) {
            return new int[size];
        }
        if (arr[i] == val) {
            size++;
        }
        int[] ans = allindices(arr, val, i + 1, size);
        if (arr[i] == val) {
            ans[size - 1] = i;
        }
        return ans;
    }

    public static void subseq(String ques, String ans, int idx) {
        if (idx == ques.length()) {
            System.out.println(ans);
            return;
        }
        subseq(ques, ans + ques.charAt(idx), idx + 1);// coming
        subseq(ques, ans, idx + 1);// not coming
    }

    public static ArrayList<String> subseq_(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> recAns = subseq_(str, idx + 1);// answer for "bc"
        ArrayList<String> ans = new ArrayList<>();// answer for "abc"
        for (String s : recAns) {
            ans.add(s);
            ans.add(str.charAt(idx) + s);
        }
        return ans;
    }

    public static void boardPath(int n, int st, String ans) {
        if (st == n) {
            System.out.println(ans);
            return;
        }
        boardPath(n, st + 1, ans + "1");
        if (st + 2 <= n) {
            boardPath(n, st + 2, ans + "2");
        }
        if (st + 3 <= n) {
            boardPath(n, st + 3, ans + "3");
        }
    }

    public static void boardPathReactive(int n, int st, String ans) {
        if (st > n) {
            return;
        }
        if (st == n) {
            System.out.println(ans);
            return;
        }
        boardPathReactive(n, st + 1, ans + "1");
        boardPathReactive(n, st + 2, ans + "2");
        boardPathReactive(n, st + 3, ans + "3");
    }

    public static ArrayList<String> boardPath_(int n, int st) {
        if (st == n) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> single = boardPath_(n, st + 1);
        for (String s : single) {
            ans.add("1" + s);
        }
        if (st + 2 <= n) {
            ArrayList<String> doubl = boardPath_(n, st + 2);
            for (String s : doubl) {
                ans.add("2" + s);
            }
        }
        if (st + 3 <= n) {
            ArrayList<String> triple = boardPath_(n, st + 3);
            for (String s : triple) {
                ans.add("3" + s);
            }
        }
        return ans;
    }

    public static void mazePath(int sr, int sc, int er, int ec, String ans) {
        if (sr == er & sc == ec) {
            System.out.println(ans);
            return;
        }
        if (sr + 1 <= er)
            mazePath(sr + 1, sc, er, ec, ans + "V");
        if (sc + 1 <= ec)
            mazePath(sr, sc + 1, er, ec, ans + "H");

    }

    public static ArrayList<String> mazePath_(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> vcall = mazePath_(sr + 1, sc, er, ec);
            for (String s : vcall) {
                ans.add("V" + s);
            }
        }
        if (sc + 1 <= ec) {
            ArrayList<String> hcall = mazePath_(sr, sc + 1, er, ec);
            for (String s : hcall) {
                ans.add("H" + s);
            }
        }
        return ans;
    }

    public static void floodFill(int sr, int sc, int er, int ec, String ans, boolean[][] vis) {
        if (sr == er & sc == ec) {
            System.out.println(ans);
            return;
        }
        if (sr + 1 <= er && vis[sr + 1][sc] == false) {
            vis[sr + 1][sc] = true;
            floodFill(sr + 1, sc, er, ec, ans + "D", vis);
            vis[sr + 1][sc] = false;

        }
        if (sc + 1 <= ec && vis[sr][sc + 1] == false) {
            vis[sr][sc + 1] = true;
            floodFill(sr, sc + 1, er, ec, ans + "R", vis);
            vis[sr][sc + 1] = false;
        }
        if (sr - 1 >= 0 && vis[sr - 1][sc] == false) {
            vis[sr - 1][sc] = true;
            floodFill(sr - 1, sc, er, ec, ans + "U", vis);
            vis[sr - 1][sc] = false;

        }
        if (sc - 1 >= 0 && vis[sr][sc - 1] == false) {
            vis[sr][sc - 1] = true;
            floodFill(sr, sc - 1, er, ec, ans + "L", vis);
            vis[sr][sc - 1] = false;
            ;

        }

    }

    public static void floodFillightCall(int sr, int sc, int er, int ec, String ans, boolean[][] vis, int[][] dir) {
        if (sr == er & sc == ec) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < dir.length; i++) {
            int rc = dir[i][0];
            int cc = dir[i][1];
            int nr = sr + rc;
            int nc = sc + cc;
            if (nr >= 0 && nc >= 0 && nr <= er && nc <= ec && vis[nr][nc] == false) {
                vis[nr][nc] = true;
                floodFill(nr, nc, er, ec, ans, vis);
                vis[nr][nc] = false;
            }
        }
    }

    public static boolean knightFill(int[][] ans, int[][] dir, int sr, int sc, int ct) {
        // ans[sr][sc] = ct;
        if (ct == 24) {
            for (int[] ar : ans) {
                for (int ele : ar) {
                    System.out.print(ele + " ");
                }
                System.out.println();
            }
            return true;
        }

        boolean res = false;
        for (int d = 0; d < dir.length; d++) {
            int rc = dir[d][0];
            int cc = dir[d][1];
            int nr = sr + rc;
            int nc = sc + cc;
            if (nr >= 0 && nc >= 0 && nr < ans.length && nc < ans.length && ans[nr][nc] == -1) {
                ans[nr][nc] = ct + 1;
                res = res || knightFill(ans, dir, nr, nc, ct + 1);
                if (res) {
                    return true;
                }
                ans[nr][nc] = -1;
            }
        }
        // ans[sr][sc] = -1;
        return false;
    }

}
