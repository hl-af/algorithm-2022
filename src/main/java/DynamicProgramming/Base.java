package DynamicProgramming;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Base {

    /**
     * LeetCode62 ⼀个机器⼈位于⼀个 m x n ⽹格的左上⻆ （起始点在下图中标记为 “Start” ）。机器⼈每次只能向
     * 下或者向右移动⼀步。机器⼈试图达到⽹格的右下⻆（在下图中标记为 “Finish” ），问总共有多少条不同的路径？
     * 方法一：使用回溯方法
     */
    public int initRecall(int m, int n) {
        return searchRecall(m - 1, n - 1);
    }

    public int searchRecall(int m, int n) {
        if (m  == 0 && n == 0) {
            return 0;
        }
        if (m == 0 || n == 0) {
            return 1;
        }
        return searchRecall(m - 1, n) + searchRecall(m, n - 1);
    }

    @Test
    public void testSearchRecall() {
        int result = initRecall(3, 4);
        System.out.println(result);
    }


    public int initPathDp(int m, int n) {
        int[][] array = new int[m][n];
        int res = searchDp(m - 1, n - 1, array); // 我犯错的地方，因为数组是从零开始计数，所以需要一开始分别减一
        return res;
    }
    /**
     * LeetCode62 ⼀个机器⼈位于⼀个 m x n ⽹格的左上⻆ （起始点在下图中标记为 “Start” ）。机器⼈每次只能向
     * 下或者向右移动⼀步。机器⼈试图达到⽹格的右下⻆（在下图中标记为 “Finish” ），问总共有多少条不同的路径？
     * 方法二：使用递归方法
     */
    public int searchDp(int m, int n, int[][] pathState) {
        if (pathState[m][n] != 0) {
            return pathState[m][n];
        }
        if (m == 0 || n == 0) {
            pathState[m][n] = 1;
            return 1;
        }
        int res = searchDp(m, n - 1, pathState) + searchDp(m - 1, n, pathState);
        pathState[m][n] = res;
        return res;
    }

    @Test
    public void testInitPathDp() {
        System.out.println(initPathDp(3, 4));
    }

    /**
     * LeetCode62 ⼀个机器⼈位于⼀个 m x n ⽹格的左上⻆ （起始点在下图中标记为 “Start” ）。机器⼈每次只能向
     * 下或者向右移动⼀步。机器⼈试图达到⽹格的右下⻆（在下图中标记为 “Finish” ），问总共有多少条不同的路径？
     * 方法三：使用循环的方法
     */
    public int initPathDp2(int m, int n) {
        int[][] array = new int[m][n];
        return searchDp2(m, n, array);
    }

    public int searchDp2(int m, int n, int[][] searchState) {
        searchState[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    searchState[i][j] = searchState[i - 1][j] + searchState[i][j - 1];
                } else if (i == 0 && j > 0) {
                    searchState[0][j] = searchState[0][j - 1];
                } else if (i > 0 && j == 0) {
                    searchState[i][0] = searchState[i - 1][0];
                }
            }
        }
        return searchState[m - 1][n - 1];
    }

    @Test
    public void testInitPathDp2() {
        System.out.println(initPathDp2(3, 4));
    }

    /**
     * LeetCode64.给定⼀个包含⾮负整数的 m x n ⽹格 grid ，请找出⼀条从左上⻆到右下⻆的路径，使得路径上的数字
     * 总和为最小
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] state = new int[m][n];
        state[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    state[i][j] = Math.min(state[i - 1][j], state[i][j - 1]) + grid[i][j];
                } else if (i == 0 && j > 0) {
                    state[i][j] = state[0][j - 1] + grid[i][j];
                } else if (i > 0 && j == 0) {
                    state[i][j] = state[i - 1][0] + grid[i][j];
                }
            }
        }
        return state[m - 1][n - 1];
    }


    @Test
    public void testMinPathSum() {
        int[][] array = new int[3][3];
        array[0] = new int[]{1, 3, 1};
        array[1] = new int[]{1, 5, 1};
        array[2] = new int[]{4, 2, 1};
        System.out.println(minPathSum(array));
    }

    /**
     * LeetCode120.给定⼀个三⻆形 triangle ，找出⾃顶向下的最⼩路径和。每⼀步只能
     * 移动到下⼀⾏中相邻的结点上。相邻的结点 在这⾥指的是 下标 与 上⼀层结点下标 相同或者等于 上⼀层结点下标
     * + 1 的两个结点。
     * @param tri
     * @return
     */
    public int minimumTotal(List<List<Integer>> tri) {
        int M = tri.size();
        int[][] state = new int[M][M];
        state[0][0] = tri.get(0).get(0);
        for (int i = 1; i < M; i++) {
            List<Integer> list = tri.get(i);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    state[i][0] = state[i - 1][0] + list.get(0);
                } else if (i > j) {
                    state[i][j] = Math.min(state[i - 1][j], state[i - 1][j - 1]) + list.get(j);
                } else if (i == j) {
                    state[i][j] = state[i - 1][j - 1] + list.get(j);
                }
            }
        }
        return findMinNum(state[M - 1]);
    }

    private int findMinNum(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    @Test
    public void testMinimumTotal() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2, 0, 0, 0));
        list.add(Arrays.asList(3, 4, 0, 0));
        list.add(Arrays.asList(6, 5, 7, 0));
        list.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(list));
    }

}
