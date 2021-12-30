public class Main {
    public static void main(String[] args) {
        sieve(30);
    }

    public static void sieve(int n) {
        boolean[] arr = new boolean[n + 1];
        for (long i = 2; i <= n; i++) {
            if (i * i > n) {
                break;
            }
            if (arr[(int) i] == false) {// ith number is prime
                // mark table of i as o prime
                for (long j = i * i; j <= n; j += i) {
                    arr[(int) j] = true;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!arr[i]) {
                System.out.println(i);
            }
        }
    }

    
}