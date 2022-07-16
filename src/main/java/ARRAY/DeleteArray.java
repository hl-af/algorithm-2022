package ARRAY;

import org.junit.Test;
import sun.jvm.hotspot.oops.OopUtilities;

import java.util.Arrays;

public class DeleteArray {

    /**
     * LeetCode27 原地移除数组中为val的元素，数组无序，不用管新数组长度后面的元素
     * 快慢指针
     * 这个方法不太好理解和记住，还是用对撞指针来做吧
     * @param arr
     * @param val
     * @return
     */
    public int removeElement(int[] arr,int val) {
        int fast = 0;
        int slow = 0;
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
}
