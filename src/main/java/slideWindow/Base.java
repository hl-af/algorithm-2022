package slideWindow;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1. 滑动窗是否是固定长度，决定了left 和 right 的初始值
 * 2. 滑动窗左端移动规则 ： 渐进式移动还是跳跃式移动
 * 3. 题型分类和方法论：
 * （1）异位字符：
 *   固定窗口，使用数组判断位置，遍历增加次数，左出又进增加字符串，次数一样为有异位词
 * （2）不重复字符串问题：
 *   不重复子串：map<字符，下标> 确定left的下一步位移为max(right + 1,left)
 *   k个重复判断：key的个数判断，如果超过移除最小位置元素，left移动到最小元素的下一个
 * （3）统计类 ：
 *   求平均数: 进一退一求平均，求平均可以一步完成
 *   求最大值: 进一退一 + 堆，求最大值需要借助堆和从堆中取出元素
 *   最长递增子数组：不递增的时候left跳跃到right
 *   加和最短长度： 满足条件就把left不断减少
 */
public class Base {


    /**
     * LeetCode75，荷兰国旗问题
     * 失败case:[1,0,2]
     * 双指针两次遍历
     * @param nums
     */
    public void sortColorsMe(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int zeroNum = nums.length /3 ;
        while (left <= right) {
            if (nums[left] - nums[right] == 2) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right--;
        }

        left = zeroNum;
        right = nums.length - 1;
        while (left <= right) {
            if (nums[left] - nums[right] == 1) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right--;
        }

    }

    /**
     * LeetCode75，荷兰国旗问题
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0;
        int right = 0;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }

        for (right = left; right < nums.length; right++) {
            if (nums[right] == 1) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }

    @Test
    public void testSortColors() {
        int[] a = {2, 0, 2, 1, 1, 0};
        sortColors(a);
        System.out.println(Arrays.toString(a));
    }
}
