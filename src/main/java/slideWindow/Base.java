package slideWindow;

import org.junit.Test;

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
     * LeetCode674 寻找递增最长子序列
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = 1;
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
    @Test
    public void testFindLengthOfLCIS() {
        int[] a = {1, 2, 3, 3, 5, 6};
        System.out.println(findLengthOfLCIS(a));
    }


}
