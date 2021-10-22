import java.util.*;

public class crypto {
    public static void main(String[] args) {
        String s1 = "send";
        String s2 = "more";
        String s3 = "money";
        cryptoHelper(s1, s2, s3);
    }

    public static void cryptoHelper(String s1, String s2, String s3) {
        StringBuilder sb = new StringBuilder();
        boolean[] map = new boolean[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < s3.length(); i++) {
            map[s3.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] == true) {
                sb.append((char) (i + 'a'));
            }
        }
        int[] charMap = new int[26];
        char[] numMap = new char[10];
        Arrays.fill(charMap, -1);
        Arrays.fill(numMap, ' ');
        boolean ans = cryptoFinal(s1, s2, s3, sb.toString(), 0, charMap, numMap);
        System.out.println(ans);
    }

    public static boolean cryptoFinal(String s1, String s2, String s3, String unique, int idx, int[] charMap,
            char[] numMap) {
        if (idx == unique.length()) {
            int n1 = generateInt(s1, charMap);
            int n2 = generateInt(s2, charMap);
            int n3 = generateInt(s3, charMap);
            if (n1 + n2 == n3) {
                return true;
            }
            // System.out.println(n1 + " " + n2 + " " + n3);
            return false;
        }
        boolean res = false;
        char ch = unique.charAt(idx);
        for (int i = 0; i < 10; i++) {
            if (numMap[i] == ' ') {
                numMap[i] = ch;
                charMap[ch - 'a'] = i;
                res = res || cryptoFinal(s1, s2, s3, unique, idx + 1, charMap, numMap);
                charMap[ch - 'a'] = -1;
                numMap[i] = ' ';
            }
        }
        return res;
    }

    public static int generateInt(String str, int[] map) {
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            ans = ans * 10 + (map[str.charAt(i) - 'a']);
        }
        return ans;
    }

}

    //// METHOD 2 USING HASHMAP
    public boolean isSolvable(String[] words, String result) {
        int[] charMap = new int[26];
        Arrays.fill(charMap, -1);
        char[] numMap = new char[10];
        Arrays.fill(numMap, ' ');
        String distinct = nonRep(words, result);
        return help(words, result, distinct, 0, numMap, charMap);
    }

    public boolean help(String[] words, String result, String str, int idx, char[] numMap, int[] charMap) {
        if (idx == str.length()) {
            return checkIfEqual(words, result, charMap);
        }
        boolean res = false;
        char ch = str.charAt(idx);
        for (int i = 0; i < 10; i++) {
            if (i == 0 && start(words, result, ch)) {
                continue;
            }
            if (charMap[ch - 'A'] == -1 && numMap[i] == ' ') {
                charMap[ch - 'A'] = i;
                numMap[i] = ch;
                res = res || help(words, result, str, idx + 1, numMap, charMap);
                charMap[ch - 'A'] = -1;
                numMap[i] = ' ';
            }
        }
        return res;
    }

    public boolean start(String[] words, String result, char ch) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 1 && words[i].charAt(0) == ch)
                return true;
        }
        if (result.length() > 1 && result.charAt(0) == ch)
            return true;
        return false;
    }

    public boolean checkIfEqual(String[] words, String result, int[] charMap) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < words.length; i++) {
            int val = convertIntoNum(words[i], charMap);
            left += val;
        }
        right = convertIntoNum(result, charMap);
        return left == right;
    }

    public int convertIntoNum(String str, int[] charMap) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * 10 + charMap[str.charAt(i) - 'A'];
        }
        return num;
    }

public String nonRep(String[] words,String result){
    String ans="";
    HashSet<Character> set=new HashSet<>();
    String word=result;
    for(int i=-1;i<words.length;i++){
        if(i!=-1)
            word=words[i];
        for(int j=0;j<word.length();j++){
            if(set.add(word.charAt(j))){
                ans=ans+word.charAt(j);
            }
        }
    }
    return ans;
}