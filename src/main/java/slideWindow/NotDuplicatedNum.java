package slideWindow;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NotDuplicatedNum {


    /**
     * LeetCode3 给定⼀个字符串 s,找出最长不重复子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = -1;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                int num = map.get(s.charAt(right));
                //left = Math.max(left, map.get(s.charAt(right)) + 1); 答案写法，不懂
                left = num + 1;
            }
            map.put(s.charAt(right), right);
            max = Math.max(right - left + 1, max);
        }
        return max;
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String a = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(a));
    }
}
