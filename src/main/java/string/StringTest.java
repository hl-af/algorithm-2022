package string;

import org.junit.Test;

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
}
