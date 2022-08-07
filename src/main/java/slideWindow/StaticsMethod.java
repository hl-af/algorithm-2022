package slideWindow;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class StaticsMethod {

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
