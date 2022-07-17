package ARRAY;

import org.junit.Test;

import java.util.Arrays;

public class RotateArray {

    /**
     * LeetCode189 旋转第k个节点的数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int slow = 0;
        int fast = k - 1;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public void reverse(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    @Test
    public void testRotateArray() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        rotate(a, 3);
    }
}
