package slideWindow;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class StaticsMethod {

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
     * LeetCode239 给你⼀个整数数组 nums，有⼀个⼤⼩为 k 的滑动窗⼝从数组的最左侧移动到数组的最右侧。你只
     * 可以看到在滑动窗⼝内的 k 个数字。滑动窗⼝每次只向右移动⼀位，返回滑动窗⼝中的最⼤值。
     * 自己实现的不优雅的滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowMe(int[] nums, int k) {
        int right = k;
        int left = 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k,(a,b)->b-a);
        int[] res = new int[nums.length];
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
            res[0] = minHeap.peek();
        }
        while (right < nums.length) {
            minHeap.offer(nums[right]);
            res[left] = minHeap.peek();
            if (minHeap.peek() == nums[left]) {
                minHeap.poll();
            }
            left++;
            right++;
        }
        return res;
    }

    @Test
    public void testMaxSlidingWindow() {
//        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] a = {9, 10, 9, -7, -4, -8, 2, -6};
        System.out.println(Arrays.toString(maxSlidingWindow(a, 5)));
    }

    /**
     * LeetCode239 给你⼀个整数数组 nums，有⼀个⼤⼩为 k 的滑动窗⼝从数组的最左侧移动到数组的最右侧。你只
     * 可以看到在滑动窗⼝内的 k 个数字。滑动窗⼝每次只向右移动⼀位，返回滑动窗⼝中的最⼤值。
     * 答案方案
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            minHeap.offer(new int[]{nums[i],i});
        }
        List<Integer> res = new ArrayList<>();
        res.add(minHeap.peek()[0]);
        for (int right = k; right < nums.length; right++) {
            minHeap.offer(new int[]{nums[right],right});
            while (minHeap.peek()[1] <= right - k) { // 这里是 while 不是 if，能保证里面的个数为k个
                minHeap.poll();
            }
            res.add(minHeap.peek()[0]); //先确定好两边的增减，然后再放入结果中
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


}
