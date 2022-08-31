package bit;

import org.junit.Test;

import java.util.Arrays;

public class BitBase {

    /**
     * LeetCode191 编写⼀个函数，输⼊是⼀个⽆符号整数（以⼆进制串的形式），返回其⼆进制表达式中数字位数'1' 的个数。
     * 使用原始数位移实现
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            int res = n & 1;
            n = n >> 1;
            if (res == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * LeetCode191 编写⼀个函数，输⼊是⼀个⽆符号整数（以⼆进制串的形式），返回其⼆进制表达式中数字位数'1' 的个数。
     * 使用 n & (n-1) 少一位的性质来算
     *
     * @param n
     * @return
     */
    public int countOnes(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    @Test
    public void testHammingWeight() {
        int a = 3;
        System.out.println(hammingWeight1(a));
    }


    /**
     * LeetCode338.给你⼀个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其⼆进制表示中 1 的个数 ，返回⼀个
     * ⻓度为 n + 1 的数组 ans 作为答案
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = countOnes(i);
        }
        return res;
    }

    @Test
    public void testCountBits() {
        int[] res = countBits(2);
        System.out.println(Arrays.toString(res));
    }

    /**
     * LeetCode7 给你⼀个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。如果反转后整数超过 32 位的
     * 有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。假设环境不允许存储 64 位整数（有符号或⽆符号）。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res > Integer.MAX_VALUE / 10 ||
                    res < Integer.MIN_VALUE / 10) {
                if (res != Integer.MAX_VALUE / 10 || res != Integer.MIN_VALUE / 10) {
                    return 0;
                } else {
                    if (res % 10 > Integer.MAX_VALUE % 10 || res % 10 < Integer.MIN_VALUE % 10) {
                        return 0;
                    }
                }
            }
            res = res * 10 + (x % 10); //这样的方式避免了引入计数变量
            x = x / 10;
        }
        return res;
    }

    @Test
    public void testReverse() {
        int a = 1147483649;
        System.out.println(reverse(a));
    }


    /**
     * LeetCode9 .给你⼀个整数 x ，如果 x 是⼀个回⽂整数，返回 true ；否则，返回 false 。
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int reverseNum = 0;
        int origin = x;
        while (x != 0) {
            if (x > Integer.MAX_VALUE / 10 || (x == Integer.MAX_VALUE / 10 && x > Integer.MAX_VALUE % 10)) {
                return false;
            }
            if (x < Integer.MIN_VALUE / 10 || (x == Integer.MIN_VALUE / 10 && x < Integer.MIN_VALUE % 10)) {
                return false;
            }
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        if (origin == reverseNum) {
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void testIsPalindrome() {
        int x = 1221;
        System.out.println(isPalindrome(x));
    }

    /**
     * LeetCode504.给定⼀个整数 num ，将其转化为 7 进制，并以字符串形式输出。
     * @param num
     * @return
     */
    public String convertToBase7(int num) {

        StringBuffer stringBuffer = new StringBuffer();
        Boolean isNagative = false;
        if (num < 0) {
            isNagative = true;
            num = Math.abs(num);
        }
        while (num != 0) {
            stringBuffer.append("" + num % 7);
            num = num / 7;
        }
        if (isNagative) {
            stringBuffer = stringBuffer.append("-");
        }
        return stringBuffer.reverse().toString();
    }

    @Test
    public void testConvertToBase7() {
        System.out.println(convertToBase7(-100));
    }


    /**
     * 给定⼀个⼗进制数M，以及需要转换的进制数N，将⼗进制数M转化为N进制数。M是32位整数，2<=N<=16。
     * @param M
     * @param N
     * @return
     */
    public String convert (int M, int N) {
        char[] conver = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuffer stringBuffer = new StringBuffer();
        Boolean isNagative = false;
        if (M < 0) {
            isNagative = true;
            M = -M;
        }
        while (M != 0) {
            stringBuffer.append(conver[M % N]);
            M = M / N;
        }
        if (isNagative) {
            stringBuffer.append("-");
        }
        return stringBuffer.reverse().toString();
    }

    @Test
    public void testConvert() {
        System.out.println(convert(20, 2));
    }

    /**
     * LeetCode66.具体要求是由整数组成的⾮空数组所表示的⾮负整数，在其基
     * 础上加⼀。这⾥最⾼位数字存放在数组的⾸位， 数组中每个元素只存储单个数字。并且假设除了整数 0 之外，这
     * 个整数不会以零开头。例
     * @param digits
     * @return
     */
    public static int[] plusOneMe(int[] digits) {
        int length = digits.length;
        Boolean isHighPlus = false;
        digits[length - 1] = digits[length - 1] + 1;
        if (digits[length - 1] >= 10) {
            digits[length - 1] = 0;
            isHighPlus = true;
        }
        int index = length - 2;
        while (isHighPlus && index >= 0) {
            digits[index] = digits[index] + 1;
            if (digits[index] >= 10) {
                digits[index] = 0;
                isHighPlus = true;
            }else {
                isHighPlus = false;
            }
            index--;
        }
        if (isHighPlus) {
            int[] res = new int[length + 1];
            res[0] = 1;
            return res;
        }else {
            return digits;
        }
    }

    /**
     * LeetCode66.具体要求是由整数组成的⾮空数组所表示的⾮负整数，在其基础上加⼀。
     * 这⾥最⾼位数字存放在数组的⾸位， 数组中每个元素只存储单个数字。并且假设除了整数 0 之外，这
     * 个整数不会以零开头
     * 答案实现的方法
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    @Test
    public void testplusOne() {
        int[] a = {9, 8};
        int[] res = plusOne(a);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 字符串加法
     * @param num1
     * @param num2
     * @return
     */
    public String addStringsMe(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return new String();
        }
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        StringBuffer res = new StringBuffer();
        boolean flag = false;
        while (num1Index >= 0 && num2Index >= 0) {
            int left = num1.charAt(num1Index--) - '0';
            int right = num2.charAt(num2Index--) - '0';
            int temp = (left + right + (flag ? 1 : 0)) % 10;
            res = res.append(temp);
            if (left + right + (flag ? 1 : 0) >= 10) {
                flag = true;
            } else {
                flag = false;
            }
        }
        while (num1Index >= 0) {
            res = res.append(num1.charAt(num1Index--) + "");
        }
        while (num2Index >= 0) {
            res = res.append(num2.charAt(num2Index--) + "");
        }
        return res.reverse().toString();
    }

    @Test
    public void testAddStrings() {
        System.out.println(addStrings("4123", "678"));
    }

    /**
     * 十进制字符串加法
     * 没有进位版本
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int add = 0;
        StringBuffer res = new StringBuffer();
        while (index1 >= 0 || index2 >= 0 || add != 0) {
            int left = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int right = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            index1--;
            index2--;
            add = left + right + add;
            res.append(add % 10);
            add = add / 10;
        }
        return res.reverse().toString();
    }


//    public String addBinary(String a, String b) {
//
//    }

}
