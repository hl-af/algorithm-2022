package ARRAY;

import org.junit.Test;
import sun.jvm.hotspot.oops.OopUtilities;

import java.util.Arrays;

public class DeleteArray {

    /**
     * LeetCode27 原地移除数组中为val的元素，数组无序，不用管新数组长度后面的元素
     * 优化点：重复元素
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

    @Test
    public void testRemoveElement() {
        int[] arr = {1,2,2,3,4,6,7,3,2,2,3};
        System.out.println(removeElement(arr, 2));
    }
}
