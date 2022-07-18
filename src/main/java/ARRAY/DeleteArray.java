package ARRAY;

import org.junit.Test;
import sun.jvm.hotspot.oops.OopUtilities;

import java.util.Arrays;

public class DeleteArray {

    /**
     * LeetCode26 删除有序数组的重复项(保留一个重复项)
     * 进阶题
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        for (; fast <= nums.length - 1; fast++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        System.out.println(Arrays.toString(nums));
        return slow;
    }

    @Test
    public void testRemoveDuplicates() {
        int[] a = {1, 3, 4, 5, 6, 7, 7, 8};
        System.out.println(removeDuplicates(a));
    }

    /**
     * LeetCode27 原地移除数组中为val的元素，数组无序，不用管新数组长度后面的元素(保留零个重复项)
     * 快慢指针
     * @param arr
     * @param val
     * @return
     */
    public int removeElement(int[] arr,int val) {
        int fast = 0;
        int slow = 0;
        // fast指向元素，如果遇到目标元素，slow停下来， 如果遇到非目标元素，交换slow 和fast 指向元素的值
        for (; fast < arr.length; fast++) {
            if (arr[fast] != val) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return slow;
    }

    /**
     *LeetCode27 原地移除数组中为val的元素，数组无序，不用管新数组长度后面的元素
     * 对撞指针
     * @param arr
     * @param val
     * @return
     */
    public int removeElementOppositePoint(int[] arr,int val) {
        int left = 0;
        int right = arr.length - 1;
        for (;right > left;left++) {
            while (arr[right] == val) {
                right--;
            }
            if (arr[left] == val) {
                arr[left] = arr[right];
                arr[right] = val;
                right--;
            }
        }
        System.out.println(Arrays.toString(arr));
        return left;
    }

    @Test
    public void testRemoveElement() {
        int[] arr = {1,2,2,3,4,6,7,3,2,2,3};
//        System.out.println(removeElement(arr, 2));
        System.out.println(removeElementOppositePoint(arr, 2));
    }

    /**
     * Leetcode 80. 删除有序数组中的重复项 II（保留两个重复项）
     * 重复元素保留两个
     * 根据答案提示自己做出来的：还是思考slow存储的是已经有效的区域，根据有效区域来判断fast的是否成立
     * @param nums
     * @return
     */
    public int removeThreeDuplicates(int[] nums) {
        int slow = 2;
        int fast = 3;
        for (; fast < nums.length; fast++) {
            if (nums[slow - 1] != nums[fast]) { //和leetcode 的差别仅在 96，97，99行，思路是确保slow指针的有效区间内的是符合条件的
                slow++;
                nums[slow] = nums[fast];
            }
        }
        System.out.println(Arrays.toString(nums));
        return slow;
    }

    @Test
    public void  testRemoveThreeDuplicates() {
        int[] a = {1, 3, 3, 3, 5, 5, 7, 7, 7, 9};
        System.out.println(removeThreeDuplicates(a));
    }
}
