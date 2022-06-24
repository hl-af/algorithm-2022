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

    /**
     * LeetCode852 寻找数组最顶端
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            //递增
            if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            }

            //递减
            if (arr[mid - 1] > arr[mid] && arr[mid] > arr[mid + 1]) {
                high = mid - 1;
            }

            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void testPeakIndexInMountainArray() {
        int[] array = {11, 13, 15, 17, 14, 12, 10};
        int result = peakIndexInMountainArray(array);
        System.out.println(result);
    }

    /**
     * LeetCode153 寻找旋转排序数组中的最小值 II
     * 自己的思路，failed
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            //高位区
            if (nums[low] < nums[mid] && nums[high] < nums[mid] && nums[mid] < nums[mid+1] && nums[mid-1] < nums[mid]) {
                low = mid + 1;
            }
            //低位区
            if (nums[low] > nums[mid] && nums[high] > nums[mid] && nums[mid] < nums[mid + 1] && nums[mid -1 ] < nums[mid]) {
                high = mid - 1;
            }
            //临界点
            if (nums[low] < nums[mid] && nums[high] > nums[mid] && nums[mid] < nums[mid + 1] && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
        }
        return nums[0];
    }

    @Test
    public void testFindMin() {
        int[] array = {4,5,6,7,0,1,4};
        int result = findMin(array);
        System.out.println(result);
    }
}

