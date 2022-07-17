package ARRAY;

import org.junit.Test;

import javax.management.remote.rmi.RMIConnectionImpl;
import javax.swing.table.TableRowSorter;
import java.util.Arrays;

/**
 * 单调数组
 */
public class MonotonicArray {

    /**
     *  LeetCode 896 判断一个数组是否是单调递增或者递减的
     * 使用两个变量来标记是否是单调的
     * 自己实现，方法有些笨重，答案思路简洁
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean incr = false;
        boolean desc = false;
        for(int i = 0;i < nums.length - 1;i++){
            if(nums[i] < nums[i+1]){
                incr = true;
            }
            if(nums[i] > nums[i+1]){
                desc = true;
            }
        }
        if(incr && desc){
            return false;
        }
        if(incr || desc){
            return true;
        }
        return true;
    }

    /**
     *  LeetCode 896 判断一个数组是否是单调递增或者递减的
     * 使用两个变量来标记是否是单调的
     * 标准答案实现法
     * @return
     */
    public boolean isMonotonicStandard(int[] nums) {
        boolean incr = true;
        boolean desc = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                desc = false;
            }
            if (nums[i] > nums[i + 1]) {
                incr = true;
            }
        }
        return incr || desc;
    }


    /**
     * 测试单调数组
     */
    @Test
    public void testArray() {
        int[] arr = {3,1};
//        System.out.println(isMonotonic(arr));
        System.out.println(isMonotonicStandard(arr));
    }

    /**
     * LeetCode88 合并两个有序数组
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
        while (m >= 0 && n >= 0) { // 注意要两个都大于0
            if (arr1[m] >= arr2[n]) {
                arr1[i--] = arr1[m--];
            }else {
                arr1[i--] = arr2[n--];
            }
        }
        while (n != -1) { //注意其中一个数组还有剩余的情况
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

    /**
     * 如leetcode35：给定⼀个排序数组和⼀个⽬标值，在数组中找到⽬标值，并返回其索引。如果⽬标值不存在于数组
     * 中，返回它将会被按顺序插⼊的位置。
     * 使用二分查找法解决问题
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int middle = low + ((high - low) >> 1);
        while (low <= high) {
            if (nums[middle] < target) {
                low = middle + 1;
            } else if (target < nums[middle]) {
                high = middle - 1;
            }else {
                return middle;
            }
            middle = low + ((high - low) >> 1);
        }
        return middle + 1;
    }

    @Test
    public void testSearchInsert() {
        int[] a = {1, 3, 5, 7, 9};
        System.out.println(searchInsert(a, 3));
    }

}
