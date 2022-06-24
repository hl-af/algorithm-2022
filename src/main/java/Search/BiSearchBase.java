package Search;

import org.junit.Test;

/**
 * 二分查找基本使用
 */
public class BiSearchBase {

    public int searchByCirculation(int[] array, int low, int high, int target) {

        while (low <= high) {
            int mid = low + ((high - low) >> 1);//注意不是>>2
            if (array[mid] == target) {
                return mid;
            }
            if (target < array[mid]) { // 这样的写法能够直接反映target和数组中元素的位置
                high = mid - 1; // 也进而能够知道high该如何移动
            }
            if (array[mid] < target) {
                low = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void testCirculation() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = searchByCirculation(array, 0, 8, 0);
        System.out.println(result);
    }

    public int searchByRecursion(int[] array, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (array[mid] < target) {
            return searchByCirculation(array, mid + 1, high, target);
        }
        if (target < array[mid]) {
            return searchByCirculation(array, low, mid - 1, target);
        }
        if (target == array[mid]) {
            return mid;
        }
        return -1;
    }

    @Test
    public void testRecursion() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = searchByRecursion(array, 0, 8, 4);
        System.out.println(result);
    }
}

