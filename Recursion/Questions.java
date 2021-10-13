public class Questions {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int[] arr = { 10, 20, 30, 40 };
        // equiSet(arr);
        // palindromePartition("pepep");
        friendsPairing(3);
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

}
