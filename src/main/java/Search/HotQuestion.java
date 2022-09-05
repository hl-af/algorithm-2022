package Search;

import org.junit.Test;

import javax.xml.bind.annotation.XmlID;

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

    /**
     * 山峰数组
     * 死循环的例子
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray2(int[] arr) {
        int low = 1;
        int high = arr.length - 1;
        while (low < high) {
            int middle = low + ((high - low) >> 1);
            int num = arr[middle];
            if (arr[middle - 1] < num && num < arr[middle + 1]) {
                low = middle + 1;
            } else if (arr[middle - 1] > num && num > arr[middle + 1]) {
                high = middle - 1;
            } else if (arr[middle - 1] < num && arr[middle + 1] < num) {
                return middle;
            }
        }
        return -1;
    }
    @Test
    public void testPeakIndexInMountainArray() {
//        int[] array = {11, 13, 15, 17, 14, 12, 10};
        int[] array = {3,4,5,1};
//        int result = peakIndexInMountainArray(array);
        int result = peakIndexInMountainArray2(array);
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


    /**
     *  LeetCode153 旋转数组找最小值
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low < high) {
            mid = low + ((high - low) >> 1);
            if (nums[mid] < nums[high]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return mid - 1;
    }

    @Test
    public void testFindMin() {
//        int[] array = {4, 5, 6, 7, -1, 0, 1};
        int[] array = {-1, 0, 1, 4, 5, 6, 7};
        int result = findMin2(array);
        System.out.println(result);
    }

    /**
     * 剑指offer
     * 寻找缺失数字：⼀个⻓度为n-1的递增排序数组中的所有数字都是唯⼀的，并且每个数字都在范围0～n-1之内。在
     * 范围0～n-1内的n个数字中有且只有⼀个数字不在该数组中
     * @param nums
     * @return
     */
    public int findLackNum(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (nums[mid] == mid) {
                low = mid + 1;
            }else {
                high = mid - 1;//这里没有返回目标值，使用了left作为返回
            }
        }
        return low;
    }

    @Test
    public void testFindLackNum() {
        int[] array = {1,2, 3, 4};
        int result = findLackNum(array);
        System.out.println(result);
    }

    /**
     * LeetCode 69 实现函数 int sqrt(int x).计算并返回x的平⽅根这个题的思路是⽤最快的⽅式找到n*n=x的n。
     * 没办法全部ac
     * @param x
     * @return
     */
    public int sqrt(int x) {
        int low = 0;
        int high = x;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (x / mid > mid) {
                low = mid + 1;
            }else if(x / mid < mid){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return mid;
    }

    @Test
    public void testSqrt() {
        int result = sqrt(8);
        System.out.println(result);
        System.out.println(sqrt(8, 8/2,1));
    }

    /**
     * 计算sqrt，精确到小数点后7位
     * @param n
     * @param mid
     * @param accuracy
     * @return
     */
    public double sqrt(int n, double mid, double accuracy) {
        if (accuracy < 0.0000001) {
            return mid;
        }
        double low = 0;
        double high = n;
        while (low < high) {
            mid = low + ((high - low) / 2);
            if (mid < n / mid) {
                low = mid + accuracy;
            } else if (n / mid < mid) {
                high = mid - accuracy;
            } else {
                return sqrt(n, mid, accuracy * 0.1);
            }
        }
        return sqrt(n, mid, accuracy * 0.1);
    }


}

