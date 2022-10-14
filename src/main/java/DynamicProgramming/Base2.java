package DynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Base2 {

    /**
     * LeetCode322.给你⼀个整数数组 coins ，表示不同⾯额的硬币，以及⼀个整数 amount ，表示总⾦额。计算并返
     * 回可以凑成总⾦额所需的最少的硬币个数 。如果没有任何⼀种硬币组合能组成总⾦额，返回 -1 。你可以认为每种
     * 硬币的数量是⽆限的。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // dp[] 代表硬币数， []代表金额
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int currentAmount = 0; currentAmount <= amount; currentAmount++) {
            for (int j = 0; j < coins.length; j++) {
                if (currentAmount - coins[j] >= 0 &&
                dp[currentAmount] > dp[currentAmount - coins[j]] + 1) {
                    dp[currentAmount] = dp[currentAmount - coins[j]] + 1;
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    @Test
    public void testCoinChange() {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 1));
    }

    /**
     *  LeetCode674.给定⼀个未经排序的整数数组，找到最⻓且连续递增的⼦序列，并返回该序列的⻓度。
     * @param A
     * @return
     */
    public int findLengthOfLCIS(int[] A){
        int[] dp = new int[A.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                dp[i] = dp[i - 1] + 1;
            }
            if (result < dp[i]) {
                result = dp[i];
            }
        }
        return result;
    }

    @Test
    public void testFindLengthOfLCIS() {
        int[] A = {1,3,5,4,7,8,9,10,11,12};
        System.out.println(findLengthOfLCIS(A));
    }




    /**
     * LeetCode300.给你⼀个整数数组 nums ，找到其中最⻓严格递增⼦序列的⻓度。
     * @param A
     * @return
     */
    public int lengthOfLIS(int[] A) {
        int[] dp = new int[A.length];
        int result = Integer.MIN_VALUE;
        Arrays.fill(dp, 1);
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < i; j++) {
                if (A[j] < A[i] && dp[i] < (dp[j] + 1)) {
                    dp[i] = dp[j] + 1;
                }
                if (result < dp[i]) {
                    result = dp[i];
                }
            }
        }
        return result;
    }

    @Test
    public void testLengthOfLIS() {
        int[] A = {10, 9, 2, 5, 3, 7, 101, 1};
        System.out.println(lengthOfLIS(A));
    }

    /**
     * LeetCode279.给你⼀个整数 n ，返回和为n的完全平⽅数的最少数量。
     * 完全平⽅数 是⼀个整数，其值等于另⼀个整数的平⽅；换句话说，其值等于⼀个整数⾃乘的积。例如，1、4、9 和16 都是完全平⽅数，⽽ 3 和 11 不是。
      * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> squarNum = new ArrayList<>();
        int[] dp = new int[n + 1]; //dp[] 是最少数量，n是目标数
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n ; i++) { //目标数
            if (isSquarNum(i)) {
                squarNum.add(i);
                dp[i] = 1;
            }
            for (int j = 0; j < squarNum.size(); j++) {
                if ( i - squarNum.get(j) >= 0 && dp[i] > dp[i - squarNum.get(j)] + 1){
                    dp[i] = dp[i - squarNum.get(j)] + 1;
                }
            }
        }
        return dp[n];
    }

    public boolean isSquarNum(int n) {
        Double a = Math.sqrt(n);
        int b = a.intValue();
        if (b * b != n) {
            return false;
        }else {
            return true;
        }
    }

    @Test
    public void testNumSquares() {
        System.out.println(isSquarNum(11));
        System.out.println(numSquares(17));
    }
}
