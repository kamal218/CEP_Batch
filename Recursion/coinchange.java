public class coinchange {
    public static void main(String[] args) {
        // infinite coins
        int[] coins = { 2, 3, 5, 7 };
        // int ans = infipermutation(coins, 10, "");
        // int ans=infipcombination(coins, 10, 0, "");
        // int ans = singlcombination(coins, 10, 0, "");
        boolean[] vis = new boolean[4];
        int ans = singlpermutation(coins, 10, "", vis);
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
}