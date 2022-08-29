package bit;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Arrays;

public class BitBase {

    /**
     * LeetCode191 编写⼀个函数，输⼊是⼀个⽆符号整数（以⼆进制串的形式），返回其⼆进制表达式中数字位数'1' 的个数。
     * 使用原始数位移实现
     *
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

}
