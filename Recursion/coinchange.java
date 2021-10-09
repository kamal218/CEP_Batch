import java.util.*;

public class coinchange {
    public static void main(String[] args) {
        // infinite coins
        int[] coins = { 2, 3, 5, 7 };
        // int ans = infipermutation(coins, 10, "");
        // int ans=infipcombination(coins, 10, 0, "");
        // int ans = singlcombination(coins, 10, 0, "");
        // boolean[] vis = new boolean[4];
        // int ans = singlpermutation(coins, 10, "", vis);
        // System.out.println(ans);
        // int val = 0;
        // List<Integer> list = new ArrayList<>();
        // help(0, list);
        // System.out.println(val);
        // System.out.println(list);
        // int ans = inficombiSubseq(coins, 10, 0, "");
        // int ans = infiperSubseq(coins, 10, 0, "");
        // int ans = singlecombSubseq(coins, 10, 0, "");
        int ans = singleperSubseq(coins, 10, 0, "");
        System.out.println(ans);

    }

    public static int infipermutation(int[] coins, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infipermutation(coins, tar - coins[i], ans + coins[i]);
            }
        }
        return count;
    }

    public static int infipcombination(int[] coins, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += infipcombination(coins, tar - coins[i], i, ans + coins[i]);
            }
        }
        return count;
    }

    public static int singlcombination(int[] coins, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                count += singlcombination(coins, tar - coins[i], i + 1, ans + coins[i]);
            }
        }
        return count;
    }

    public static int singlpermutation(int[] coins, int tar, String ans, boolean[] vis) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                if (vis[i] == false) {
                    vis[i] = true;
                    count += singlpermutation(coins, tar - coins[i], ans + coins[i], vis);
                    vis[i] = false;
                }
            }
        }
        return count;
    }

    public static int infipcombination(int[] coins, int tar, int idx, List<Integer> smallans, List<List<Integer>> ans) {
        if (tar == 0) {
            ans.add(new ArrayList<>(smallans));
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (tar - coins[i] >= 0) {
                smallans.add(coins[i]);
                count += infipcombination(coins, tar - coins[i], i, smallans, ans);
                smallans.remove(smallans.size() - 1);
            }
        }
        return count;
    }

    public static void help(int val, List<Integer> ans) {
        if (val == 1) {
            return;
        }
        ans.add(1);
        val += 1;
        help(val, ans);
    }

    public static int inficombiSubseq(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0)
            count += inficombiSubseq(coins, tar - coins[idx], idx, ans + coins[idx]);
        count += inficombiSubseq(coins, tar, idx + 1, ans);
        return count;
    }

    public static int infiperSubseq(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0)
            count += infiperSubseq(coins, tar - coins[idx], 0, ans + coins[idx]);
        count += infiperSubseq(coins, tar, idx + 1, ans);
        return count;
    }

    public static int singlecombSubseq(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - coins[idx] >= 0)
            count += singlecombSubseq(coins, tar - coins[idx], idx + 1, ans + coins[idx]);
        count += singlecombSubseq(coins, tar, idx + 1, ans);
        return count;
    }

    public static int singleperSubseq(int[] coins, int tar, int idx, String ans) {
        if (tar == 0 || idx == coins.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (coins[idx] > 0 && tar - coins[idx] >= 0) {
            coins[idx] = -coins[idx];
            count += singleperSubseq(coins, tar + coins[idx], 0, ans + (-coins[idx]));
            coins[idx] = -coins[idx];
        }
        count += singleperSubseq(coins, tar, idx + 1, ans);
        return count;
    }

}
