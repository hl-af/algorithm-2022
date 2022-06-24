package Search;

import org.junit.Test;

public class HotQuestion {

    /**
     * 元素存在重复的二分查找
     * @param array
     * @param low
     * @param high
     * @return
     */
    public int searchWithSameNum(int[] array, int target) {
        int low = 0;
        int high = array.length -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (array[mid] < target) {
//                while (array[mid] == array[mid + 1]) {
//                    mid = mid + 1;
//                }
                low = mid + 1;
            }
            if (target < array[mid]) {
//                while (array[mid] == array[mid - 1]) {
//                    mid = mid - 1;
//                }
                high = mid - 1;
            }
            if (array[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testSearchWithSameNum() {
        int[] array = {1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 9};
        int result = searchWithSameNum(array, 3);
        System.out.println(result);
    }
}
