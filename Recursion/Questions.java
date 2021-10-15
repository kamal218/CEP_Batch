import java.util.*;

public class Questions {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int[] arr = { 10, 20, 30, 40 };
        // equiSet(arr);
        // palindromePartition("pepep");
        // goldMine2(mat);
        // friendsPairing(3);
        // boolean ans = fibonacciSeqCaller("123411235");
        // androidPattern(3, 5);
        // boolean ans = kthSymbol(5, 4);
        // int ans=maxScoreWords_(words, idx, fmap, score);
        // System.out.println(ans);
    }

    public static void equiSet(int[] arr) {
        // int ans = equiSet_(arr, 0, 0, 0, "", "");// arr,idx,sum1,sum2,answer
        int ans = equiSet_dup(arr, 1, arr[0], 0, arr[0] + "+", "");
        System.out.println(ans);
    }

    public static int equiSet_(int[] arr, int idx, int s1, int s2, String set1, String set2) {
        if (idx == arr.length) {
            if (s1 == s2) {
                System.out
                        .println(set1.substring(0, set1.length() - 1) + " == " + set2.substring(0, set2.length() - 1));
                return 1;
            }
            return 0;
        }
        int ans = 0;
        ans += equiSet_(arr, idx + 1, s1 + arr[idx], s2, set1 + arr[idx] + "+", set2);
        ans += equiSet_(arr, idx + 1, s1, s2 + arr[idx], set1, set2 + arr[idx] + "+");
        return ans;
    }

    public static int equiSet_dup(int[] arr, int idx, int s1, int s2, String set1, String set2) {
        if (idx == arr.length) {
            if (s1 == s2) {
                System.out
                        .println(set1.substring(0, set1.length() - 1) + " == " + set2.substring(0, set2.length() - 1));
                return 1;
            }
            return 0;
        }
        int ans = 0;
        ans += equiSet_dup(arr, idx + 1, s1 + arr[idx], s2, set1 + arr[idx] + "+", set2);
        ans += equiSet_dup(arr, idx + 1, s1, s2 + arr[idx], set1, set2 + arr[idx] + "+");
        return ans;
    }

    public static void palindromePartition(String str) {
        int ans = palindromePartition_(str, 0, "");
        System.out.println(ans);
    }

    public static int palindromePartition_(String str, int idx, String ans) {
        if (idx >= str.length()) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int cut = idx; cut < str.length(); cut++) {
            String palin = str.substring(idx, cut + 1);
            if (ispalin(palin)) {
                count += palindromePartition_(str, cut + 1, ans + "(" + palin + ")");
            }
        }
        return count;
    }

    public static boolean ispalin(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void goldMine2(int[][] mat) {
        int ans = 0;
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {// i,j -> start
                if (mat[i][j] > 0) {
                    int cans = collectGoldWithGivenStart(mat, i, j, dir);
                    if (ans < cans) {
                        ans = cans;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static int collectGoldWithGivenStart(int[][] mat, int r, int c, int[][] dir) {
        mat[r][c] = -mat[r][c];
        int ans = 0;
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (nr >= 0 && nc >= 0 && nr < mat.length && nc < mat[0].length && mat[nr][nc] > 0) {
                int rAns = collectGoldWithGivenStart(mat, nr, nc, dir);
                if (ans < rAns) {
                    ans = rAns;
                }
            }
        }
        mat[r][c] = -mat[r][c];
        return ans + mat[r][c];
    }

    public static void friendsPairing(int n) {
        boolean[] used = new boolean[n + 1];
        int ans = friendsPairing(n, used, 1, "");
        System.out.println(ans);
    }

    public static int friendsPairing(int n, boolean[] used, int num, String ans) {
        if (num > n) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        if (used[num] == false) {
            used[num] = true;
            count += friendsPairing(n, used, num + 1, ans + "(" + num + ")");

            for (int pu = num + 1; pu <= n; pu++) {
                if (used[pu] == false) {
                    used[pu] = true;
                    count += friendsPairing(n, used, num + 1, ans + "(" + num + "" + pu + ")");
                    used[pu] = false;
                }
            }
            used[num] = false;
        } else {
            count += friendsPairing(n, used, num + 1, ans);
        }
        return count;
    }

    public static boolean fibonacciSeqCaller(String str) {
        if (str.length() < 3) {
            return false;
        }
        boolean res = false;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            long f1 = Long.parseLong(str.substring(0, i + 1));
            if (str.charAt(0) == '0' && i != 0) {
                break;
            }
            if (f1 > Integer.MAX_VALUE) {
                break;
            }
            for (int j = i + 1; j < str.length() - 1; j++) {
                long f2 = Long.parseLong(str.substring(i + 1, j + 1));
                if (str.charAt(i + 1) == '0' && j != i + 1) {
                    break;
                }
                if (f2 > Integer.MAX_VALUE) {
                    break;
                }
                ans.add((int) f1);
                ans.add((int) f2);
                res = res || fiboanacciSeq(str, j + 1, (int) f1, (int) f2, ans);
                if (res) {
                    System.out.println(ans);
                    return res;
                }
                ans.remove(ans.size() - 1);
                ans.remove(ans.size() - 1);
            }
        }
        return res;
    }

    public static boolean fiboanacciSeq(String str, int idx, int f1, int f2, List<Integer> ans) {
        if (idx == str.length()) {
            return true;
        }
        boolean res = false;
        for (int i = idx; i < str.length(); i++) {
            String nnum = str.substring(idx, i + 1);
            long f3 = Long.parseLong(nnum);
            if (str.charAt(idx) == '0' && i != idx) {
                break;
            }
            if (f3 > Integer.MAX_VALUE) {
                return false;
            }
            if (f1 + f2 == f3) {
                ans.add((int) f3);
                res = res || fiboanacciSeq(str, i + 1, f2, (int) f3, ans);
                if (res) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return res;
    }

    public static void androidPattern(int m, int n) {
        int ans = 0;
        int[][] mat = new int[10][10];
        mat[1][7] = mat[7][1] = 4;
        mat[3][9] = mat[9][3] = 6;
        mat[1][3] = mat[3][1] = 2;
        mat[7][9] = mat[9][7] = 8;
        mat[1][9] = mat[3][7] = mat[4][6] = mat[2][8] = 5;
        mat[9][1] = mat[7][3] = mat[6][4] = mat[8][2] = 5;
        boolean[] vis = new boolean[10];
        for (int moves = m; moves <= n; moves++) {
            ans += (androidPattern(moves, mat, vis, 2) * 4);
            ans += (androidPattern(moves, mat, vis, 1) * 4);
            ans += androidPattern(moves, mat, vis, 5);
        }
        System.out.println(ans);
    }

    public static int androidPattern(int moves, int[][] mat, boolean[] vis, int cpos) {
        if (moves == 0) {
            return 0;
        }
        if (moves == 1) {
            return 1;
        }
        int ans = 0;
        vis[cpos] = true;
        for (int nmove = 1; nmove <= 9; nmove++) {
            if (vis[nmove] == false && (mat[cpos][nmove] == 0 || vis[mat[cpos][nmove]] == true)) {
                ans += androidPattern(moves - 1, mat, vis, nmove);
            }
        }
        vis[cpos] = false;
        return ans;
    }

    public static boolean kthSymbol(int n, int k) {
        int pow = (int) Math.pow(2, n - 1);
        return kthSymbol_(n, k - 1, pow);
    }

    public static boolean kthSymbol_(int n, int k, int len) {
        if (n == 1) {
            return false;
        }
        if (k < (len) / 2) {
            return kthSymbol_(n - 1, k, len / 2);
        } else {
            return !kthSymbol_(n - 1, k - (len / 2), len / 2);
        }
    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] fmap = new int[26];
        for (char ch : letters) {
            fmap[ch - 'a']++;
        }
        return maxScoreWords_(words, 0, fmap, score);
    }

    public static int maxScoreWords_(String[] words, int idx, int[] fmap, int[] score) {
        if (idx == words.length) {
            return 0;
        }
        int notcome = maxScoreWords_(words, idx + 1, fmap, score);
        String cword = words[idx];
        boolean cancome = true;
        int cscore = 0;
        int i = 0;
        for (i = 0; i < cword.length(); i++) {
            char ch = cword.charAt(i);
            if (fmap[ch - 'a'] == 0) {
                cancome = false;
                break;
            }
            fmap[ch - 'a']--;
            cscore += score[ch - 'a'];
        }
        int come = 0;
        if (cancome) {
            come = maxScoreWords_(words, idx + 1, fmap, score) + cscore;
        }
        i--;
        while (i >= 0) {
            fmap[cword.charAt(i) - 'a']++;
        }
        return Math.max(come, notcome);
    }

}
