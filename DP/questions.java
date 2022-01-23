import java.util.ArrayList;
import java.util.HashSet;

public class questions {
    // WORD BREAK 1
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len];
        HashSet<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                String lastpart = s.substring(j, i + 1);
                if (set.contains(lastpart)) {
                    if (j == 0 || dp[j - 1] == true) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[len - 1];
    }

    // WORD BREAK 2
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        List<String>[] dp = new ArrayList[len];
        HashSet<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        for (int i = 0; i < len; i++) {
            dp[i] = new ArrayList<>();
            for (int j = i; j >= 0; j--) {
                String lastpart = s.substring(j, i + 1);
                if (set.contains(lastpart)) {
                    if (j == 0) {
                        dp[i].add(lastpart);
                    } else {
                        List<String> p = dp[j - 1];
                        for (String slastpart : p) {
                            dp[i].add((slastpart + " " + lastpart));
                        }
                    }
                }
            }
        }
        return dp[len - 1];
    }

    // DECODE WAYS 1

    public int numDecodings(String s) {
        int len = s.length();
        int last = 1;
        int slast = s.charAt(len - 1) == '0' ? 0 : 1;
        for (int i = len - 2; i >= 0; i--) {
            int curr = 0;
            int d1 = s.charAt(i) - '0';
            if (d1 != 0) {
                curr = slast;
                int num = (d1 * 10 + (s.charAt(i + 1) - '0'));
                if (num <= 26) {
                    curr += last;
                }
            }
            last = slast;
            slast = curr;
        }
        return slast;
    }

    public int numDecodings(String s) {
        int len = s.length();
        long last = 1;
        long slast = 0;
        long mod = (int) (1e9) + 7;
        if (s.charAt(len - 1) == '0') {
            slast = 0;
        } else if (s.charAt(len - 1) == '*') {
            slast = 9;
        } else {
            slast = 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            long curr = 0;
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(i + 1);
            if (s.charAt(i) == '0') {

            } else if (s.charAt(i) == '*') {
                curr = (slast % mod * 9) % mod;// single digit
                if (ch2 == '*') {
                    curr = (curr % mod + (last) % mod * 15) % mod;
                } else {
                    int d2 = ch2 - '0';
                    if (d2 <= 6) {
                        curr = (curr % mod + (last) % mod * 2) % mod;
                    } else {
                        curr = (curr % mod + last % mod) % mod;
                    }
                }
            } else {
                curr = (curr % mod + (slast) % mod) % mod;
                if (ch2 == '*') {
                    if (ch1 == '1') {
                        curr = (curr % mod + (last) % mod * 9) % mod;
                    } else if (ch1 == '2') {
                        curr = (curr % mod + (last) % mod * 6) % mod;
                    }
                } else {
                    int num = ((ch1 - '0') * 10 + (ch2 - '0'));
                    if (num <= 26) {
                        curr = (curr % mod + (last) % mod) % mod;
                    }
                }
            }
            last = slast;
            slast = curr;
        }
        return (int) slast;
    }

    // INTERLEAVING STRING

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];
        return help(s1, s2, s3, 0, 0, 0, dp) == -1 ? false : true;
    }

    public static int help(String s1, String s2, String s3, int i, int j, int k, int[][][] dp) {
        // true->1
        // false->-1
        // not visited->0
        if (k == s3.length()) {
            return 1;
        }
        if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        }
        int res = -1;// false
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            res = help(s1, s2, s3, i + 1, j, k + 1, dp);// -1/1
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(k) && res != 1) {
            res = help(s1, s2, s3, i, j + 1, k + 1, dp);
        }
        return dp[i][j][k] = res;
    }

}