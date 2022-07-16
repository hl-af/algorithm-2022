package ARRAY;

import org.junit.Test;

import javax.swing.table.TableRowSorter;
import java.util.Arrays;

/**
 * 单调数组
 */
public class MonotonicArray {

    /**
     * 判断一个数组是否是单调递增或者递减的
     * 使用两个变量来标记是否是单调的
     * @return
     */
    public Boolean isMonotonicArray(int[] arr) {
        boolean incr = false;
        boolean desc = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1]) {
                desc = true;
            }else {
                incr = true;
            }
        }
        if (desc == true && incr == true) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 测试单调数组
     */
    @Test
    public void testArray() {
        int[] arr = {3,1};
        System.out.println(isMonotonicArray(arr));
    }

    /**
     * 合并两个有序数组
     * 从后往前遍历
     * @param arr1 数组1 ， 大小 m + n
     * @param m 元素个数
     * @param arr2 数组2
     * @param n 元素个数
     */
    public void mergerArray(int[] arr1, int m,int[] arr2,int n) {
        int i = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0) {
            if (arr1[m] >= arr2[n]) {
                arr1[i--] = arr1[m--];
            }else {
                arr1[i--] = arr2[n--];
            }
        }
        while (n != -1) {
            arr1[i--] = arr2[n--];
        }
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public void testMergeArrays() {
        int[] arr1 = {1,3,5,7,8,12,0,0,0};
        int[] arr2 = {-1, 0, 1};
        mergerArray(arr1, 6, arr2, 3);
    }

}
