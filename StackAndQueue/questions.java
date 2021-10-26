import java.util.Stack;

public class questions {
    // NGER
    public int[] nger(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MAX_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.pop()] = i == arr.length ? -1 : arr[i];
            }
            st.push(i);
        }
        return ans;
    }

    // NSER

    public int[] nser(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i == arr.length ? -1 : arr[i];
            }
            st.push(i);
        }
        return ans;
    }

    // NGEL
    public int[] ngel(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= -1; i--) {
            // how many elemets can be resolved by arr[i]
            int val = i == -1 ? Integer.MAX_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.pop()] = i == -1 ? -1 : arr[i];
            }
            st.push(i);
        }
        return ans;
    }

    // NSEL
    public int[] nsel(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= -1; i--) {
            // how many elemets can be resolved by arr[i]
            int val = i == -1 ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i == -1 ? -1 : arr[i];
            }
            st.push(i);
        }
        return ans;
    }

    // MAX DIFFERENCE

    public int maxDiffnsernsel(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                int nser = i == arr.length ? 0 : arr[i];
                st.pop();
                int nsel = st.size() == 0 ? 0 : arr[st.peek()];
                ans = Math.max(ans, Math.abs(nser - nsel));
            }
            st.push(i);
        }
        return ans;
    }

}