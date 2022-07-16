package ARRAY;

import org.junit.Test;

import java.util.Arrays;

/**
 * 双指针思想
 */
public class DoublePointer {

    /**
     * LeetCode80 给你⼀个有序数组 nums ，请你原地删除重复出现的元素，使每个元素只出现⼀次 ，返回删除后数组
     * 的新⻓度。
     * 快慢指针去除有序数组中重复的元素
     */
    public void removeDuplicates(int[] arr) {
        int slow = 0;
        int fast = 1;
        int size = 0;
        for (fast = 0; fast < arr.length; fast++) {
            if (arr[slow] != arr[fast]){
                slow++;
                arr[slow] = arr[fast];
                size++;
            }
        }

        for (int i = 0; i <= size; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    @Test
    public void testDoublePointer() {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5, 5, 6};
        removeDuplicates(arr);
//        System.out.println(Arrays.toString(arr));
    }
}
