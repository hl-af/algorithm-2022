package string;

import List.KthFromEnd;
import org.junit.Test;

import java.util.*;

public class StringTest {

    /**
     * LeetCode709 字符串转小写字母
     * @param s
     * @return
     */
    public static String toLowerCase(String s) {
        char[] phrase = s.toCharArray();
        int diff = 'a' - 'A';
        for (int i = 0; i < phrase.length; i++) {
            if (phrase[i] >= 'A' && phrase[i] <= 'Z') {
                phrase[i] += diff;
//                phrase[i] = phrase[i] + diff; //这样的写法编译无法通过
            }
        }
        return String.valueOf(phrase);
    }

    @Test
    public void testToLowerCase() {
        String a = "AbCdEfg";
        System.out.println(toLowerCase(a));
    }

    /**
     * LeetCode8. 字符串转换整数 (atoi)
     * // 要点： 1. 前置空格删除 2. 有正负符号 3. 遇到非数字字符忽略 4. 越界截断 5. 前置0删除
     * 自己实现的版本，待leetcode验证
     * @param str
     * @return
     */
    public static int myAtoiMe(String str) {
        char[] num = str.toCharArray();
        int left = 0;
        //这段的循环比较难想全
        while (num[left] == ' ' || (num[left] <= '0' || num[left] >= '9')) { //过滤掉前置空格
            if (num[left] == '+' || num[left] == '-') {
                break;
            }
            if ((num[left] <= '0' || num[left] >= '9') && num[left] != ' ') { //排除非数字使用 或， 而不是且 num[left] <= '0' || num[left] >= '9'
                return 0;
            }
            left++;
            if (left == num.length) {
                return 0;
            }
        }


        boolean isPostive = true;
        if ((num[left] == '+') || (num[left] == '-')) {
            if (num[left] == '-') {
                isPostive = false;
            }
            left++;
        }
        Double value = 0.0; //答案要求不能用long，估计double也不可以使用
        int times = 0;
        for (int i = num.length - 1; i >= left ; i--) {
            if (num[i] >= '0' && num[i] <= '9') {
                value = value + Double.parseDouble((num[i] - '0') * Math.pow(10, times++) + "");
            }
        }
        if (!isPostive) {
            value = -value;
        }
        if (value > Integer.MAX_VALUE) {
            value = Integer.MAX_VALUE + 0.0;
        }
        if (value < Integer.MIN_VALUE) {
            value = Integer.MIN_VALUE + 0.0;
        }

        return value.intValue();
    }

    @Test
    public void testMyAtoi() {
        String a = "     -540abc";
//        System.out.println(myAtoiMe(a));
        System.out.println(myAtoi(a));
    }

    /**
     * 字符串转数字
     * 答案版本
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        char[] nums = str.toCharArray();
        int index = 0;
        while (nums[index] == ' ') {
            index++;
        }
        if (index == str.length()) {
            return 0;
        }

        int sign = 1;
        if (nums[index] == '+' || nums[index] == '-') {
            if (nums[index] == '-') {
                sign = -1;
            }
            index++;
        }

        int res = 0;
        while (index < nums.length) {
            if (nums[index] < '0' || nums[index] > '9') {
                break;
            }
            // 判断整型是否越界的经典方法
            if (res > (Integer.MAX_VALUE / 10) ||
                    ((res/10 == Integer.MAX_VALUE/10) && (res%10 > Integer.MAX_VALUE % 10))) { // 当只有个位大于Integer.MAX_VALUE的个位情况，只用res > (Integer.MAX_VALUE / 10) 看不出来的
                return Integer.MAX_VALUE;
            }
            if (res < (Integer.MIN_VALUE / 10) ||
                    (res / 10 == Integer.MAX_VALUE && res %10 < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (nums[index] - '0'); // 注意如果不减48会使用数字对应的ascii来计算
            index++;
        }
        return res;
    }


    /**
     * LeetCode344. 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        return;
    }

    @Test
    public void testReverseString() {
        String s = "hello";
        char[] a = s.toCharArray();
        reverseString(a);
        System.out.println(Arrays.toString(a));
    }

    //LeetCode541. K个⼀组反转
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) {
            return new String();
        }
        char[] chars = s.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int left = index;
            int right = index + k - 1;
            reverseCharArray(chars, left, right);
            index = index + 2 * k;
        }
        if (index < chars.length) {
            int left = index;
            int right = chars.length - 1;
            reverseCharArray(chars, left, right);
        }
        return String.valueOf(chars);
    }

    public void reverseCharArray(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void testReverseStr() {
        String a = "abcd efg";
        System.out.println(reverseStr(a, 2));

    }

    /**
     * LeetCode.917. 仅仅反转字⺟
     * 基于栈实现
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (isLetter(S.charAt(i))) {
                stack.push(S.charAt(i));
            }
        }
        char[] res = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            if (!isLetter(res[i])) {
                continue;
            }
            Character a = stack.pop();
            res[i] = a;
        }
        return String.valueOf(res);
    }

    public boolean isLetter(Character character) {
//        if ((character > 'z' && character < 'a') || (character > 'Z' && character < 'A')) {
        if ((character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z')) {
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void testReverseOnlyLetters() {
        String a = "Test1ng-Leet=code-Q!";//"Qedo1ct-eeLg=ntse-T!"
        System.out.println(reverseOnlyLettersDualPointer(a));
    }

    /**
     * LeetCode.917. 仅仅反转字⺟
     * 基于双指针实现
     * @param S
     * @return
     */
    public String reverseOnlyLettersDualPointer(String S) {
        int left = 0;
        int right = S.length() - 1;
        char[] array = S.toCharArray();
        while (left < right) {
            while (left < S.length() && !isLetter(array[left])) {
                left++;
            }
            while (right > 0 && !isLetter(array[right])) {
                right--;
            }
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        return String.valueOf(array);
    }


    /**
     * LeetCode151. 反转字符串⾥的单词
     * 使用java语言特性实现，自己实现的思路
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.split(" "); //如果有连续的空格，会分出来好多个""
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = words.length - 1; i >= 0; i--) {
            stringBuffer.append(words[i]);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    /**
     * LeetCode151. 反转字符串⾥的单词
     * 使用java语言特性实现，答案的思路
     * @param s
     * @return
     */
    public String reverseWordsAnswer(String s) {
        if (s == null || s.length() == 0) {
            return new String();
        }
        List<String> wordList = Arrays.asList(s.split("\\s+"));//连续空格分割
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    @Test
    public void testReverseWords() {
        String strings = "   i am confused";
        System.out.println(reverseWordsAnswer(strings));
    }


    /**
     * LeetCode557. 反转字符串中的单词 III
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        if (s == null || s.length() == 0) {
            return new String();
        }
        int slow = 0;
        int fast = 0;
        char[] chars = s.toCharArray();
        while (fast < chars.length) {
            while (fast < chars.length && chars[fast] != ' ') {
                fast++;
            }
            reversePart(chars, slow, fast - 1);
            fast++;
            slow = fast;
        }
        return String.valueOf(chars);
    }

    public void reversePart(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    @Test
    public void testReverseWords3() {
        String strings = "Let's take LeetCode contest";
        System.out.println(reverseWords3(strings));
    }

    /**
     * LeetCode.125. 验证回⽂串
     * 判断后是否相等
     * @param s
     * @return
     */
    public boolean isPalindromeReverse(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        StringBuffer origin = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (isCharacterOrDigital(s.charAt(i))) {
                origin.append(s.charAt(i));
            }
        }
        StringBuffer reverse = new StringBuffer(origin).reverse();
        if (reverse.toString().equalsIgnoreCase(origin.toString())) {
            return true;
        }else {
            return false;
        }

    }

    public boolean isCharacterOrDigital(Character character) {
        if ((character >= 'a' && character <= 'z')
                || (character >= 'A' && character <= 'Z')
                || (character >= '0' && character <= '9')) {
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void testIsPalindrome() {
        String words = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(words));

    }


    /**
     * LeetCode.125. 验证回⽂串
     * 双指针
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        char[] array = s.toCharArray();
        while (left < right) {
            while (left < s.length() && !isCharacterOrDigital(array[left])) {
                left++;
            }
            while (right >= 0 && !isCharacterOrDigital(array[right])) {
                right--;
            }
            if (!String.valueOf(array[left]).equalsIgnoreCase(String.valueOf(array[right]))) {
                return false;
            }else {
                left++;
                right--;
            }
        }
        return true;
    }


    /**
     * LeetCode387. 字符串中的第⼀个唯⼀字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            int num = map.get(chars[i]);
            if (num == 1) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void testFirstUniqChar() {
        String a = "loveleetcode";
        System.out.println(firstUniqChar(a));
    }

    /**
     * LeetCode58. 最后⼀个单词的⻓度
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int index = chars.length - 1;
        while (!isLetter(chars[index])) {
            index--;
        }
        int len = 0;
        while (isLetter(chars[index])) {
            index--;
            len++;
        }
        return len;
    }

    @Test
    public void testLengthOfLastWord() {
        String a = " fly me to the moon ";
        System.out.println(lengthOfLastWord(a));
    }

    /**
     * 剑指Offer58、左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0) {
            return new String();
        }
        StringBuffer suffix = new StringBuffer();
        for (int i = 0; i < n && i< s.length(); i++) {
            suffix.append(s.charAt(i));
        }
        StringBuffer prefix = new StringBuffer();
        for (int i = n; i < s.length(); i++) {
            prefix.append(s.charAt(i));
        }

        return prefix.append(suffix).toString();
    }

    @Test
    public void testReverseLeftWords() {
        String a = "abcadfhg";
        System.out.println(reverseLeftWords(a, 10));
    }

    /**
     * 判定是否互为字符重排
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkPermutation(String s1, String s2) {
        int[] s1Array = new int[26];
        int[] s2Array = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Array[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            s2Array[s2.charAt(i) - 'a']++;

        }
        for (int i = 0; i < 26; i++) {
            if (s1Array[i] != s2Array[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCheckPermutation() {
        String s1 = "abcadfhg";
        String s2 = "bcafdagh";
        System.out.println(checkPermutation(s1, s2));
    }

    /**
     * leetcode 443 字符串压缩问题
     * 待leetcode验证
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int left = 0;
        int right = 0;
        int index = 0;
        while (right < chars.length) {
            while (chars[left] == chars[right]) {
                right++;
                if (right == (chars.length)) {
                    break;
                }
            }
            if (right - left > 1) {
                int num = right - left;
                chars[index++] = chars[left];
                int times = 0;
                do {
                    chars[index++] = (char) (num % 10 + '0');
                    num = num / 10;
                    times++;
                } while (num > 0);
                reverse(chars, index - times, index - 1);
            } else {
                index++;
            }
            left = right;
        }
        return index;
    }

    public void reverse(char[] a, int left, int right) {
        while (left < right) {
            char temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void testCompress() {
        String a = "abbbbbbbbbbbbbb";
        System.out.println(compress(a.toCharArray()));
    }
}
