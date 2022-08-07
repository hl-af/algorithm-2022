package slideWindow;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1. 滑动窗是否是固定长度，决定了left 和 right 的初始值
 * 2. 滑动窗左端移动规则 ： 渐进式移动还是跳跃式移动
 * 3. 方法论：（1）异位字符：数组存储 （2）统计窗口：堆  （3）不重复字符串：map<字符，下标>
 */
public class Base {

    /**
     * LeetCode643 给定 n 个整数，找出平均数最⼤且⻓度为 k 的连续⼦数组，并输出该最⼤平均数。
     * 自己实现
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverageMe(int[] nums, int k) {

        int left = 0;
        int right = k - 1;
        int curSum = 0;
        for (int i = left; i <= right ; i++) {
            curSum = curSum + nums[i];
        }
        int maxSum = curSum;
        left = left + 1;
        right = right + 1;
        while (right <= nums.length - 1) {
            curSum = curSum - nums[left - 1] + nums[right];
            if (maxSum < curSum) {
                maxSum = curSum;
            }
            left++;
            right++;
        }
        return maxSum/k;
    }

    /**
     * LeetCode643 给定 n 个整数，找出平均数最⼤且⻓度为 k 的连续⼦数组，并输出该最⼤平均数。
     * 最佳实践
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return -1;
        }
        int curSum = 0;
        for (int i = 0; i < k; i++) {
            curSum = curSum + nums[i];
        }
        int res = Integer.MIN_VALUE;
        for (int right = k; right < nums.length; right++) {
            curSum = curSum - nums[right - k] + nums[right];
            if (res < curSum) {
                res = curSum;
            }
        }
        return (double)res / k;
    }


    @Test
    public void testFindMaxAverage() {
        int[] a = {1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage(a, 4));
    }


    /**
     * LeetCode 674 寻找递增最长子序列
     * 我自己的实现
     * @param nums
     * @return
     */
    public int findLengthOfLCISMe(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = 1; // // 这样的写法默认了数组最小长度是2，对于输入数组长度是1的问题无法求解
        int res = Integer.MIN_VALUE;
        while (right < nums.length) {
            if (nums[right] <= nums[right - 1]) {//需要加上等号
                left = right;
            }
            right++; // 提前加来对齐长度
            res = Math.max(right - left , res);
        }
        return res;
    }

    /**
     * LeetCode 674 寻找递增最长子序列
     * 我自己的实现
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {

        int left = 0;
        int right = 0;
        int res = Integer.MIN_VALUE;
        while (right < nums.length) {
            if (right > 0 && nums[right] <= nums[right - 1]) {//需要加上等号
                left = right;
            }
            right++; // 提前加来对齐长度
            res = Math.max(right - left , res);
        }
        return res;
    }
    @Test
    public void testFindLengthOfLCIS() {
        int[] a = {1, 2, 3, 3, 5, 6};
        System.out.println(findLengthOfLCIS(a));
    }


    /**
     * LeetCode209 ⻓度最⼩的⼦数组,给定⼀个含有 n 个正整数的数组和⼀个正整数 target，使得最小数组大于target
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int currentSum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            currentSum = currentSum + nums[right++]; //这个求和条件放到后面就有问题，如果放在后面就会少加最后一位
            while (currentSum >= target) {
                res = Math.min(right - left, res);
                currentSum = currentSum - nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    @Test
    public void testMinSubArrayLen() {
        int[] a = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, a));
    }

    /**
     * LeetCode75，荷兰国旗问题
     * 失败case:[1,0,2]
     * 双指针两次遍历
     * @param nums
     */
    public void sortColorsMe(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int zeroNum = nums.length /3 ;
        while (left <= right) {
            if (nums[left] - nums[right] == 2) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right--;
        }

        left = zeroNum;
        right = nums.length - 1;
        while (left <= right) {
            if (nums[left] - nums[right] == 1) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right--;
        }

    }

    /**
     * LeetCode75，荷兰国旗问题
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0;
        int right = 0;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }

        for (right = left; right < nums.length; right++) {
            if (nums[right] == 1) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }

    @Test
    public void testSortColors() {
        int[] a = {2, 0, 2, 1, 1, 0};
        sortColors(a);
        System.out.println(Arrays.toString(a));
    }
}
