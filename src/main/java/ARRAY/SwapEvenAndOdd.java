package ARRAY;

import org.junit.Test;

import java.util.Arrays;

public class SwapEvenAndOdd {

    /**
     * LeetCode905，按奇偶排序数组。
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            if (A[left] % 2 == 0) {
                left++;
            }
            if (A[right] % 2 == 1) {
                right--;
            }
            if (A[left] % 2 != 0 && A[right] % 2 != 1) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }

        }
        return A;
    }

    @Test
    public void testSortArrayByParity() {
        int[] a = {1, 3, 6, 4, 7, 5, 8};
        System.out.println(Arrays.toString(sortArrayByParity(a)));
    }
}
