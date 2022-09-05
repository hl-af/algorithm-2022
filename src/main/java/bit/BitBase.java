package bit;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1.计算1的个数：a & (a - 1)，最后一位的1变为0
 * 2.进位移动：res = res * 10 + num % 10
 * 3.int类型是否越界的判断：x > Integer.MAX_VALUE / 10 || (x == Integer.MAX_VALUE / 10 && x > Integer.MAX_VALUE % 10)
 * 4.M进制转N进制：定义一个N进制的数组，来应对超过9的情况；逐步增加位数的过程先 % 得到数，后 / 作为下一步使用；记得首先判断正负号
 * 5.字符串拼接记得倒过来
 * 6.N进制加法计算：定义一个add作为进位标志
 * 7.是否是某一个数的幂次：使用连续除来算，最后结果是否为1
 * 8.只出现一次的元素判断：使用异或的性质
 * 9.素数和丑数的判断：
 * pre素数判断：使用2到sqrt(n)来判断
 * 9-1.基本：使用组成元素除，判断最后是不是为1;9-2. 定义一个数组，使用埃氏法则做
 */
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


    /**
     * 二进制的加法
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuffer res = new StringBuffer();
        int leftIndex = a.length() - 1;
        int rightIndex = b.length() - 1;
        int add = 0;
        while (leftIndex >= 0 || rightIndex >= 0) {
            int left = leftIndex >= 0 ? a.charAt(leftIndex) - '0' : 0;
            int right = rightIndex >= 0 ? b.charAt(rightIndex) - '0' : 0;
            add = left + right + add;
            res.append(add % 2 + "");
            add = add / 2;
            leftIndex--;
            rightIndex--;
        }
        if (add == 1) {
            res.append("1");
        }
        return res.reverse().toString();
    }

    @Test
    public void testAddBinary() {
        String a = "1101";
        String b = "11";
        System.out.println(addBinary(a ,b));
    }

    /**
     * LeetCode371 给你两个整数 a 和 b ，不使⽤运算符 + 和 - ，计算并返回两整数之和。
     * @param a
     * @param b
     * @return
     */
//    public int getSum(int a, int b) {
//
//    }

    /**
     * LeetCode231. 给你⼀个整数 n，请你判断该整数是否是 2 的幂次⽅。如果是，返回 true ；否则，返回 false 。
     * 如果存在⼀个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次⽅。
     * @param n
     * @return
     */
    boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 2 != 0) {
                return false;
            }
            n = n / 2;
        }
        return true;
    }

    @Test
    public void testIsPowerOfTwo() {
        System.out.println(isPowerOfTwo(3));
    }

    /**
     * leetcode326 给定⼀个整数，写⼀个函数来判断它是否是 3 的幂次⽅。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 3 的幂次⽅需满⾜：存在整数 x 使得 n == 3^x
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {

        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }

    @Test
    public void testIsPowerOfThree() {
        System.out.println(isPowerOfThree(21));
    }

    /**
     * LeetCode136 给定⼀个⾮空整数数组，除了某个元素只出现⼀次以外，其余每个元素均出现两次。找出那个只出
     * 现了⼀次的元素。
     * 基于异或的交换律：a^b^a = b  a^a = 0
     * @param nums
     * @return
     */
    int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    @Test
    public void testSingleNumber() {
        int[] a = {1, 2, 2, 1, 3};
        System.out.println(singleNumber(a));
    }


    /**
     * LeetCode204 给定整数 n ，返回 所有⼩于⾮负整数 n 的质数的数量 。
     * 使用截断为 n = sqrt(n) 来做
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (isPrime(i)) {
                System.out.println(i);
                count++;
            }
        }
        return count;
    }

    /**
     * 素数判断：1即⾮素数，也⾮合数；2是唯⼀的同时为偶数和素数的数字。
     * @param num
     * @return
     */
    public Boolean  isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        int end = (int)Math.sqrt(num);

        for (int i = 2; i <= end; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void testCountPrimes() {
        System.out.println(countPrimesArthas(10));
    }

    /**
     * LeetCode204 给定整数 n ，返回 所有⼩于⾮负整数 n 的质数的数量 。
     * 埃⽒筛 来做
     * @param n
     * @return
     */
    public int countPrimesArthas(int n) {
        int[] array = new int[n];
        int count = 0;
        Arrays.fill(array, 1);
        for (int i = 2; i < n; i++) {
            if (array[i] == 1) {
                count++;
                for (int j = i; ; j++) {
                    int temp = i * j;
                    if (temp < n) {
                        array[i * j] = 0;
                    }else {
                        break;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 丑数判断，使用遍历的方法
     * @param index
     * @return
     */
    public int nthUglyNumberFor(int index) {
        int count = 0;
        for (int i = 1; i <= index; i++) {
            if (isUglyNumber(i)) {
                count++;
            }
        }
        return count;
    }

    public boolean isUglyNumber(int num) {
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        return num == 1 ? true : false;
    }
    /**
     * 剑指offer:丑数查找
     * 把只包含质因⼦ 2、3 和 5 的数称作丑数（Ugly Number），求按从⼩到⼤的顺序
     * 的第 n 个丑数。
     * 使用arthas找到排序的丑数:还是不太会
     *  1, 2, 3, 4, 5, 6, 8, 9, 10, 12
     * @param index
     * @return
     */
    public int nthUglyNumber(int index) {
        int[] res = new int[index];
        res[0] = 1;
        int index3 = 0;
        int index5 = 0;
        int index2 = 0;
        int i = 0;
        int temp = res[0];
        while (temp < index) {
            temp = min(res[index2] * 2, res[index3] * 3, res[index5] * 5);
            if (temp == res[index2] * 2) {
                index2 = index2 + 2 - 1;
                res[i++] = temp;
            }
            if (temp == res[index3] * 3) {
                index3 = index3 + 3 - 1;
                res[i++] = temp;
            }
            if (temp == res[index5] * 5) {
                index5 = index5 + 5 - 1;
                res[i++] = temp;
            }
        }
        return i + 1;
    }

    public int min(int a, int b, int c) {
        int temp = a >= b ? b:a;
        return temp >= c ? c : temp;
    }

    @Test
    public void testNthUglyNumber() {
//        System.out.println(nthUglyNumber(12)); // 测试不通过
        System.out.println(nthUglyNumberFor(12));
    }


}
