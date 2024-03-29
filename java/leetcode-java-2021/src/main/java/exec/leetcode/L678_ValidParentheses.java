import java.util.Deque;
import java.util.LinkedList;

public class L678_ValidParentheses {

    public boolean checkValidString(String s) {
        char[] arr = s.toCharArray();
        Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> asteriskStack = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            char code = arr[i];
            if (code == '(') {
                leftStack.push(i);
                continue;
            }
            if (code == '*') {
                asteriskStack.push(i);
            }
            if (code == ')') {
                if (leftStack.isEmpty()) {
                    if (asteriskStack.isEmpty()) {
                        return false;
                    }
                    asteriskStack.pop();
                } else {
                    leftStack.pop();
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            Integer left = leftStack.pop();
            Integer asterisk = asteriskStack.pop();
            if (asterisk < left) {
                return false;
            }
        }

        return leftStack.isEmpty();
    }
}
