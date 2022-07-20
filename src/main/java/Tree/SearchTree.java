package Tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

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
        if (root.val == val) {
            return root;
        }
        TreeNode node = null;
        if (root.val > val) {
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
     * LeetCode 98: 验证是否是二叉搜索树 - 遍历方法(错误方法)
     * 解决 [5,4,6,null,null,3,7]失效， 因为不符合所有的右子树都大于当前节点的结论
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean flag = false;
        if (root.left != null) {
            if (root.left.val < root.val) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.right.val > root.val) {
                return false;
            }

        }
        boolean leftFlag = isValidBST(root.left);
        boolean rightFlag = isValidBST(root.right);
        return leftFlag && rightFlag;
    }



    @Test
    public void testIsValidBST() {
//        System.out.println(isValidBST(TreeUtils.getTreeDemo()));
        System.out.println(isValidBST2(TreeUtils.getTreeDemo()));
    }

    public long minNum = Long.MIN_VALUE;
    /**
     * LeetCode 98: 验证是否是二叉搜索树
     * 使用中序遍历实现
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean leftFlag = isValidBST2(root.left);

        if (root.val > minNum) { //注意不能有等于情况
            minNum = root.val;
        }else {
            return false;
        }

        boolean rightFlag = isValidBST(root.right);

        return leftFlag && rightFlag;
    }

    /**
     * LeetCode 98: 验证是否是二叉搜索树
     * 错误方法：判断左右子树是否是二叉搜索树，遍历法思路不是这样
     * @param root
     * @return
     */
    public boolean isValidBST4(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val > minNum) { //注意不能有等于情况
            minNum = root.val;
        }else {
            return false;
        }
        return isValidBST2(root.left)&& isValidBST(root.right);
    }





    /**
     * LeetCode 98: 验证是否是二叉搜索树
     * 中序遍历迭代法实现
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return true;
        }
        double inorder = root.val;
        while (!stack.isEmpty() || stack != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (inorder >= root.val) {
                return false;
            }else {
                inorder = root.val;
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

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
    }







}
