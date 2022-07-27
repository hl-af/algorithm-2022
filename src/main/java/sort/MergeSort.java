package sort;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * 归并排序基础
 */
public class MergeSort {
    public void mergeSort(int[] array, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int middle = start + ((end - start) >> 1);
        mergeSort(array, start, middle, temp);
        mergeSort(array, middle + 1, end, temp);// 不需要两个merge区间重合，所以第二个使用middle + 1
        merge(array, start, end, temp);
    }

    public void merge(int[] array, int start, int end, int[] temp) {
        int middle = start + ((end - start) >> 1);
        int index = start; //标识temp 的 下标
        int left = start;
        int right = middle + 1;
        while (left <= middle && right <= end) {
            if (array[left] < array[right]) {
                temp[index++] = array[left++];
            }else if(array[left] >= array[right]){
                temp[index++] = array[right++];
            }
        }
        while (left <= middle) {
            temp[index++] = array[left++];
        }
        while (right <= end) {
            temp[index++] = array[right++];
        }
        index = start;
        while (index <= end) {
            array[index] = temp[index];
            index++;
        }
    }

    @Test
    public void testMergeSort() {
        int[] a = {2, 7, 1, 5, 7, 3, 9, 6};
        int[] temp = new int[a.length];
        mergeSort(a, 0, a.length - 1, temp);
        System.out.println(Arrays.toString(a));

    }
}
