package Tree;

import org.junit.Test;

import java.beans.IntrospectionException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SearchTree {

    /**
     * LeetCode 700 二叉搜索树特定值的子树
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.num == val) {
            return root;
        }
        TreeNode node = null;
        if (root.num > val) {
            node = searchBST(root.left, val);
        }else {
            node = searchBST(root.right, val);
        }
        return node;
    }

    @Test
    public void testSearchBST() {
        TreeNode node = searchBST(TreeUtils.getTreeDemo(), 3);
        System.out.println();
    }

    /**
     * LeetCode 98: 验证是否是二叉搜索树 - 遍历方法
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean flag = false;
        if (root.left != null) {
            if (root.left.num < root.num) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.num > root.num) {
                return false;
            }

        }
        boolean leftFlag = isValidBST(root.left);
        boolean rightFlag = isValidBST(root.right);
        return leftFlag && rightFlag;
    }

    /**
     * LeetCode 98: 验证是否是二叉搜索树
     * 保证前面的数小雨后面的数即可，递归简单实现简单
     *
     * @param root
     * @return
     */

    public int minNum = Integer.MIN_VALUE;

    @Test
    public void testIsValidBST() {
//        System.out.println(isValidBST(TreeUtils.getTreeDemo()));
        System.out.println(isValidBST2(TreeUtils.getTreeDemo()));
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftFlag = isValidBST2(root.left);

        if (root.num <= minNum) { //注意不能有等于情况
            minNum = root.num;
        }else {
            return false;
        }

        boolean rightFlag = isValidBST(root.right);

        return leftFlag && rightFlag;
    }

    public boolean isValidBST3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return true;
        }
        double inorder = root.num;
        while (!stack.isEmpty() || stack != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (inorder >= root.num) {
                return false;
            }else {
                inorder = root.num;
            }
            root = root.right;
        }
        return true;
    }

    /**
     * LeetCode108 给你⼀个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为⼀棵 ⾼度平衡 ⼆叉搜索树。
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public TreeNode dfs(int[] nums,int start,int end) {

        int mid = start + (end - start) / 2;
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = dfs(nums, start, mid -1 );
        TreeNode right = dfs(nums, mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    @Test
    public void testSortedArrayToBST() {
        int a = 3 / 2;
        System.out.println(a);
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println();
    }







}
