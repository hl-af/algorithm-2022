package stack;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.*;

public class HotQuestion {

    /**
     * LeetCode20
     * 1. 左括号必须⽤相同类型的右括号闭合。
     * 2. 左括号必须以正确的顺序闭合。
     * 只包括： '('，')'，'{'，'}'，'['，']'
     *
     * @param s
     * @return
     */
    boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != null && map.get(s.charAt(i)).equals(stack.peek())) {
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    @Test
    public void testIsValid() {
        String a = "()[[]]{}";
        System.out.println(isValid(a));
    }

}
