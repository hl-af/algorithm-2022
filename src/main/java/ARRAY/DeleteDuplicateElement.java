package ARRAY;

import org.junit.Test;

import java.util.Arrays;

/**
 * 2022-7-20 数组删除列表元素重复项目专题
 * 1.slow维护的是有效区间范围
 * 2.基于对于不符合条件的元素用有效元素覆盖
 * 3.分为重复元素类题目（此时数组有序）和删除指定元素（数组无序）
 * 4.重复元素类题目都是判断 nums[slow] != nums[fast] 或 nums[slow-1] != nums[fast],先slow移动，然后再覆盖
 * 5.删除指定元素，slow和fast同时移动，单slow会在不满足的节点停下来，然后让fast覆盖
 * 6.快指针会一直移动
 */
public class DeleteDuplicateElement {

    /**
     * LeetCode26 删除有序数组的重复项(保留一个重复项)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        for (; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow;
    }

    /**
     * Leetcode 80. 删除有序数组中的重复项 II（保留两个重复项）
     * 重复元素保留两个
     * @param nums
     * @return
     */
    public int removeThreeDuplicates(int[] nums) {
        int slow = 1;
        int fast = 2;
        for (; fast < nums.length; fast++) {
            if (nums[slow-1] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        System.out.println(Arrays.toString(nums));
        return slow;
    }

    /**
     * LeetCode27 原地移除数组中为val的元素，数组无序，不用管新数组长度后面的元素(保留零个重复项)
     * @param arr
     * @param val
     * @return
     */
    public int removeElement(int[] arr,int val) {
        int slow = 0;
        int fast = 0;
        for (; fast < arr.length; fast++) {
            if (arr[fast] != val) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        return slow;
    }


}
