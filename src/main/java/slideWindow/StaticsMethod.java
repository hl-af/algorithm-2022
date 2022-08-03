package slideWindow;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class StaticsMethod {

    /**
     * LeetCode239 给你⼀个整数数组 nums，有⼀个⼤⼩为 k 的滑动窗⼝从数组的最左侧移动到数组的最右侧。你只
     * 可以看到在滑动窗⼝内的 k 个数字。滑动窗⼝每次只向右移动⼀位，返回滑动窗⼝中的最⼤值。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
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
        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));
    }
}
