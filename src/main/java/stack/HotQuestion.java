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

    /**
     *LeetCode150.根据 逆波兰表示法，求表达式的值。
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Set<String> symbolSet = new HashSet<>();
        symbolSet.add("+");
        symbolSet.add("-");
        symbolSet.add("*");
        symbolSet.add("/");
        Stack<String> stack = new Stack<>();
        boolean firstSymbol = true;
        Long result = -1l;
        for (int i = 0; i < tokens.length; i++) {
            if (symbolSet.contains(tokens[i])) {
                if (firstSymbol) {
                    String right = stack.pop();
                    String left = stack.pop();
                    result = fun(left, right, tokens[i]);
                    firstSymbol = false;
                }else {
                    result = fun(stack.pop(),String.valueOf(result), tokens[i]);
                }
            }else {
                stack.push(tokens[i]);
            }
        }
        return result.intValue();
    }

    long fun(String left, String right, String symbol) {
        long a = Long.valueOf(left);
        long b = Long.valueOf(right);
        if (symbol == "+") {
            return a + b;
        }
        if (symbol == "-") {
            return a - b;
        }
        if (symbol == "/") {
            return a / b;
        }
        if (symbol == "*") {
            return a * b;
        }
        return -1;
    }

    @Test
    public void testEvalRPN() {
        String[] tokens = {"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }

}
