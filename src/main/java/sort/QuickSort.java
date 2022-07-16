package sort;

import ARRAY.ArrayUtils;
import org.junit.Test;

import java.lang.annotation.Retention;

public class QuickSort {

    public void quickSort(int[] array, int start, int end) {
        int middle = start + ((end - start) >> 1);
        int pivot = array[middle];
        int left = start;
        int right = end;
        if (left >= right) {
            return;
        }
        while (left <= right) {
            while (left <= right && array[left] < pivot) { //注意这里必须抽象出来pivot的元素值，而不是array[middle],因为这个值是不断变换的
                left++;
            }
            while (left <= right && array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(array, start, right);
        quickSort(array, left, end);
    }

    @Test
    public void testQuickSort() {
        int[] a = {4, 2, 8, 7, 3, 5, 6};
        quickSort(a, 0, a.length - 1);
        ArrayUtils.printArray(a);
    }

    /**
     * LeetCode215 数组中的第K个最⼤元素。给定整数数组 nums 和整数 k ，请返回数组中第k个最⼤的元素。
     * 快速排序法
     *
     * @param array
     * @param start
     * @param end
     * @param k
     * @return
     */
    public int quickSort(int[] array, int start, int end, int k) {
        int left = start;
        int right = end;
        int middle = left + ((end - start) >> 1);
        int pivot = array[middle];
        if (start == end) {
            return array[start];
        }
        while (left <= right) {
            while (left <= right && array[left] < pivot) {
                left++;
            }
            while (left <= right && array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        int cnt = right - start + 1;
        if (cnt >= k)
            return quickSort(array, start, right, k);
        else
            return quickSort(array, left, end, k - cnt);
    }


    @Test
    public void testQuickSortForK() {
        int[] a = {4, 2, 8, 7, 3, 5, 6};
        int result = quickSort(a, 0, a.length - 1, 3);
        System.out.println(result);
    }

}
