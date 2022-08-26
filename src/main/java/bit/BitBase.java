package bit;

import org.junit.Test;

import java.lang.annotation.Target;
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
}
