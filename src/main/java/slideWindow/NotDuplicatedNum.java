package slideWindow;

import org.junit.Test;

import java.util.*;

/**
 * 重复子串问题
 */
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
//                int num = map.get(s.charAt(right)); //失败用例:dvdf
//                int num = Math.max(left,map.get(s.charAt(right))); //失败用例:abba
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            max = Math.max(right - left + 1, max);
        }
        return max;
    }


    @Test
    public void testLengthOfLongestSubstring(){
//        String a = "abbacabcbb";
        String a = "bbbbbb";
        System.out.println(lengthOfLongestSubstring(a));
    }

    /**
     * LeetCode159 ⾄多包含两个不同字符的最⻓⼦串
     * 自己实现的方式：使用集合来记录不重复的元素，left移动到right位置，有待leetcode检验
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinctMe(String s) {
        Set<Character> set = new HashSet<>(); // 使用集合的话无法记录下标位置，left的移动就不是滑动窗，而是跳跃窗
        Integer res = Integer.MIN_VALUE;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (set.contains(s.charAt(right)) && set.size() >2 ) {
                res = Math.max(right - left, res);
                left = right;
                set.clear();
            }
            set.add(s.charAt(right));
        }
        if (res == Integer.MIN_VALUE) {
            return s.length();
        }else {
            return res;
        }

    }

    /**
     * LeetCode159 ⾄多包含两个不同字符的最⻓⼦串
     * 答案思路版本（待leetcode验证）
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.keySet().size() > 2) {
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            map.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }


    @Test
    public void testLengthOfLongestSubstringTwoDistinct() {
        System.out.println(lengthOfLongestSubstring("ebec"));
    }

    /**
     * LeetCode340 给定⼀个字符串 s，找出 ⾄多 包含 k 个不同字符的最⻓⼦串T。
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.keySet().size() > k) {
                int index = Collections.min(map.values());
                left = index + 1;
                map.remove(s.charAt(index));
            }
            res = Math.max(right - left + 1, res);
            map.put(s.charAt(right), right);
        }
        return res;
    }

    @Test
    public void testLengthOfLongestSubstringKDistinct() {
        System.out.println(lengthOfLongestSubstringKDistinct("abcecccc", 3));
    }

}
