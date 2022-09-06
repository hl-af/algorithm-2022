import org.junit.Test;

import java.util.Arrays;

/**
 * 需要背的题目
 * 用于日常的反复练习
 */
public class BaseAlgorithm {

    /**
     * 快速排序模版
     * @param array
     * @param start
     * @param end 最后一个元素的索引
     */
    void quickSort(int[] array, int start, int end) {

        int left = start;
        int right = end;
        if (left >= right) {
            return;
        }
        int pivot = array[start + ((end - start) >> 1)]; // 记得是从数组中取值
        while (left <= right) {
            while (left <= right && pivot > array[left]) { //没有等号
                left++;
            }
            while (left <= right && pivot < array[right]) {
                right--;
            }
            if (left <= right) { //记得判断
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
        int[] array = {6, 8, 3, 5, 8, 1, 2};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 二分查找模版
     * @param array
     * @param low
     * @param high
     * @param target
     * @return 返回目标值对应索引
     */
    public int binarySearch(int[] array, int low, int high, int target) {
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] < target) {
                low = mid + 1;
            } else if (target < array[mid]) {
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testBinarySearch() {
        int[] array = {1, 2, 3, 5, 6, 8, 8};
        System.out.println(binarySearch(array, 0, array.length - 1, 2));
    }

    /**
     * 归并排序
     * 相当于后序遍历 +
     * @param array
     * @param start
     * @param end
     * @param temp
     */
    void mergeSort(int[] array, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        mergeSort(array, start, mid, temp);
        mergeSort(array, mid + 1, end, temp);
        sort(array, start, end, temp);
    }

    public void sort(int[] array, int start, int end, int[] temp) {
        int mid = start + ((end - start) >> 1);
        int index = start;
        int left = start;
        int right = mid + 1; //需要注意
        while (left <= mid && right <= end) {
            if (array[left] < array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }
        while (left <= mid) {
            temp[index++] = array[left++];
        }
        while (right <= end) {
            temp[index++] = array[right++];
        }
        for (int j = start; j <= end; j++) {
            array[j] = temp[j];
        }
        return;
    }

    @Test
    public void testMergeSort() {
        int[] array = {6, 8, 3, 5, 8, 1, 2};
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1,temp);
        System.out.println(Arrays.toString(array));
    }

    /**
     * LeetCode8. 字符串转换整数 (atoi)
     * @return
     */
    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        int sign = 1;
        int index = 0;
        if (str.charAt(0) == '-') {
            sign = -1;
            index++;
        }
        if (str.charAt(0) == '+') {
            index++;
        }
        int res = 0;
        while (index < str.length()) {
            if (str.charAt(index) < '0' || str.charAt(index) > '9') { //这里不能用 && ，而是用||
                break;
            }
            int temp = str.charAt(index) - '0';
            // 检验逻辑应该要放到前面
            if (res > Integer.MAX_VALUE / 10 || (res / 10 == Integer.MAX_VALUE / 10 && res % 10 == Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }

            if (res < Integer.MIN_VALUE / 10 || (res / 10 == Integer.MIN_VALUE / 10 && res % 10 == Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * temp;
            index++;
        }

        return res;

    }

    @Test
    public void testMyAtoi() {
        String str = "42";
        System.out.println(myAtoi(str));
        str = "-42";
        System.out.println(myAtoi(str));
        str = "     -42";
        System.out.println(myAtoi(str));
        str = "4193 with words";
        System.out.println(myAtoi(str));
    }


}
