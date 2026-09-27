import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<String> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // Current string save karo
                stack.push(current.toString());

                // New substring start
                current.setLength(0);

            } 
            else if (ch == ')') {

                // Current substring reverse karo
                current.reverse();

                // Previous string nikalo
                String previous = stack.pop();

                // Combine
                current.insert(0, previous);

            } 
            else {

                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}