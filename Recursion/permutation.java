import java.util.*;

public class permutation {
    public static void main(String[] args) {
        // permutation1("abc", "");
        // ArrayList<String> ans = permutation1_("abc", 0);
        // System.out.println(ans);
        // permutation2("aab", "");
        subseq("aab", "", true, 0);
    }

    public static void permutation1(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            String ros = str.substring(0, i) + str.substring(i + 1);
            permutation1(ros, ans + str.charAt(i));
        }
    }

    public static ArrayList<String> permutation1_(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> recAns = permutation1_(str, idx + 1);
        ArrayList<String> ans = new ArrayList<>();
        char ch = str.charAt(idx);
        for (String s : recAns) {
            for (int i = 0; i <= s.length(); i++) {
                String mstr = s.substring(0, i) + ch + s.substring(i);
                ans.add(mstr);
            }
        }
        return ans;
    }

    public static void permutation2(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        boolean[] map = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            if (map[ch - 'a'] == false) {
                map[ch - 'a'] = true;
                permutation2(ros, ans + str.charAt(i));
            }
        }
    }

    public static void subseq(String str, String ans, boolean coming, int idx) {
        if (idx == str.length()) {
            System.out.println(ans);
            return;
        }
        if (coming) {
            subseq(str, ans + str.charAt(idx), true, idx + 1);
            subseq(str, ans, false, idx + 1);

        } else {
            char ch = str.charAt(idx);
            if (ch == str.charAt(idx - 1)) {
                subseq(str, ans, false, idx + 1);

            } else {
                subseq(str, ans + str.charAt(idx), true, idx + 1);
                subseq(str, ans, false, idx + 1);
            }
        }
    }

}
