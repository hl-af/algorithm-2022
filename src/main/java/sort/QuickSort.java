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
        quickSort(array, start, right);
        quickSort(array, left , end);
    }

    @Test
    public void testQuickSort() {
        int[] a = {5, 4, 2, 8, 6, 7, 3, 5, 6, 7};
        quickSort(a, 0, a.length - 1);
        ArrayUtils.printArray(a);
    }

}
