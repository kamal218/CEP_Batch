public class decodeways {
    public static void main(String[] args) {
        int ans = decode("123", 0, "");
        System.out.println(ans);

    }

    public static int decode(String str, int idx, String ans) {
        if (idx == str.length()) {
            System.out.println(ans);
            return 1;
        }
        int res = 0;
        char ch1 = str.charAt(idx);
        int num1 = ch1 - '0';
        if (num1 == 0) {
            return 0;
        }
        res += decode(str, idx + 1, ans + (char) (65 + num1 - 1));
        if (idx + 1 < str.length()) {
            char ch2 = str.charAt(idx + 1);
            int num2 = ch2 - '0';
            int num = num1 * 10 + num2;
            if (num <= 26)
                res += decode(str, idx + 2, ans + (char) (65 + num - 1));
        }
        return res;
    }
}