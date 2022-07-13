package ARRAY;

import org.junit.Test;

import java.util.Arrays;

/**
 * java中常用的数组类
 */
public class ArrayUtils {

    /**
     * Arrays提供的能力
     */
    @Test
    public void sortByArrays() {
        int[] nums = {7,8,4,2,10,1,4};
        Arrays.sort(nums);//排序，无返回
        System.out.println(Arrays.toString(nums)); //转为可打印数组
        nums = new int[]{7,8,4,2,10,1,4};
        Arrays.sort(nums, 2, 6); // 部分排序
        System.out.println(Arrays.toString(nums));
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }


}
