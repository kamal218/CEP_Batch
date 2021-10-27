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

    // largest area histogram
    public int largestRectangleArea(int[] heights) {
        int[] nsel = nselidx(heights);
        int[] nser = nseridx(heights);
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int width = (nser[i] - nsel[i] - 1);
            area = Math.max(area, height * width);
        }
        return area;
    }

    public int[] nseridx(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    // NSEL
    public int[] nselidx(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= -1; i--) {
            // how many elemets can be resolved by arr[i]
            int val = i == -1 ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public int largestRectangleArea_(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int area = 0;
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MIN_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] > val) {
                int nser = i;
                int height = arr[st.pop()];
                int nsel = st.size() == 0 ? -1 : st.peek();
                int width = nser - nsel - 1;
                area = Math.max(area, height * width);
            }
            st.push(i);
        }
        return area;
    }

    // MAXIMAL RECTANGLE
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[] heights = new int[c];
        int ans = 0;
        int sr, sc, er, ec;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            int area = largestRectangleArea_(heights);
            if (ans < area) {
                ans = area;
            }
        }
        return ans;
    }

    // NEXT GREATER ELEMENT 2

    public int[] nextGreaterElements(int[] nums) {
        int l = nums.length;
        int[] ans = new int[l];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= 2 * l; i++) {
            int val = i == 2 * l ? Integer.MAX_VALUE : nums[i % l];
            while (st.size() > 0 && nums[st.peek()] < val) {
                ans[st.pop()] = val == Integer.MAX_VALUE ? -1 : val;
            }
            if (i < l)
                st.push(i);
        }
        return ans;
    }

    // SLIDING WINDOW MAXIMUM

    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = nums.length;
        int[] ans = new int[l - k + 1];
        int[] nger = ngeridx(nums);
        for (int i = 0; i < ans.length; i++) {
            int j = i;
            while (nger[j] < i + k) {
                j = nger[j];
            }
            ans[i] = nums[j];
        }
        return ans;
    }

    public int[] ngeridx(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i <= arr.length; i++) {
            // how many elemets can be resolved by arr[i]
            int val = i == arr.length ? Integer.MAX_VALUE : arr[i];
            while (st.size() > 0 && arr[st.peek()] < val) {
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    // VALID PARENTHESES

    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.size() == 0) {
                st.push(ch);
                continue;
            }
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if (ch == ')' && st.peek() != '(')
                    return false;
                if (ch == '}' && st.peek() != '{')
                    return false;
                if (ch == ']' && st.peek() != '[')
                    return false;
                st.pop();
            }
        }
        return st.size() == 0;
    }

    // BACKSPACE

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> st1 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '#') {
                if (st1.size() > 0)
                    st1.pop();
            } else {
                st1.push(ch);
            }
        }
        Stack<Character> st2 = new Stack<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (ch == '#') {
                if (st2.size() > 0)
                    st2.pop();
            } else {
                st2.push(ch);
            }
        }
        while (st1.size() > 0 && st2.size() > 0) {
            char ch1 = st1.pop();
            char ch2 = st2.pop();
            if (ch1 != ch2) {
                return false;
            }
        }
        return st1.size() == st2.size();
    }
}
