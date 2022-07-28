package ARRAY;

import org.junit.Test;

import java.util.*;

public class TwoNumSum {

    /**
     * LeetCode 1 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], i);
            }
            if (map.get(target - nums[i]) != null) {
                int[] a = new int[2];
                a[0] = map.get(target - nums[i]);
                a[1] = i;
                return a;
            }
        }
        return null;
    }

    @Test
    public void testTwoSum() {
        int[] a = {2, 7, 11, 15};
        int[] result = twoSum(a, 9);
        System.out.println(Arrays.toString(result));
    }

    /**
     * LeetCode 15 三数之和
     * @param nums
     * @return
     */
    List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
//        quickSort(nums, 0, nums.length - 1);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                while (nums[left] + nums[right] < -current) {
                    left++;
                }
                while (nums[left] + nums[right] > -current) {
                    right--;
                }
                if (left < right && nums[left] + nums[right] == -current) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    break;
                }
            }
        }
        return result;
    }

    public void quickSort(int[] nums,int start,int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = nums[left + (right - left) >> 1];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

    @Test
    public void testThreeSum() {
        int[] a = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(a);
        System.out.println();
    }
}
