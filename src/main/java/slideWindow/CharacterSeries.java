package slideWindow;

import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.*;

/**
 * 异位字符类滑动窗口题目
 */
public class CharacterSeries {

    /**
     * LeetCode567.给你两个字符串 s1 和 s2 ，写⼀个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，
     * 返回 false 。换句话说，s1 的排列之⼀是 s2 的 ⼦串 。
     * 使用26位字符串判断是否一样
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int[] arrayS1 = new int[26];
        int[] arrayS2 = new int[26];
        int len = s1.length();
        // 初始化标准数组arrayS1, s1先遍历前3
        for (int i = 0; i < s1.length(); i++) {
            arrayS1[s1.charAt(i) - 'a']++;
            arrayS2[s2.charAt(i) - 'a']++;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            if (Arrays.equals(arrayS1,arrayS2)) {
                return true;
            }
            arrayS2[s2.charAt(i) - 'a']++;
            arrayS2[s2.charAt(i - len) - 'a']--;
        }
        return false;
    }

    @Test
    public void testCheckInclusion() {
        String s1 = "ce";
        String s2 = "abceftg";
        System.out.println(checkInclusion(s1, s2));
    }
    /**
     * LeetCode438.找到字符串中所有字⺟异位词，给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的⼦串，返回这
     * 些⼦串的起始索引。
     * @param s 长总字符串
     * @param p 短目标字符串
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] arrayLong = new int[26];
        int[] arrayShort = new int[26];
        List<Integer> res = new ArrayList<>();
        int len = p.length();
        for (int i = 0; i < p.length(); i++) {
            arrayLong[s.charAt(i) - 'a']++;
            arrayShort[p.charAt(i) - 'a']++;
        }
        for (int i = arrayShort.length; i < s.length(); i++) {
            if (Arrays.equals(arrayShort, arrayLong)) {
                res.add(i - len);
            }
            arrayLong[s.charAt(i) - 'a']++;
            arrayLong[s.charAt(i) - 'a']--;
        }
        return res;
    }

    @Test
    public void testFindAnagrams() {
        String p = "abc";
        String s = "cbaebabacd";
        List<Integer> result = findAnagrams(s, p);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
