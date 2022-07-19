package Tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHight {

    /**
     * leetcode 104:求树的最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 使用层序遍历也可以完成
     */
    //TODO

    @Test
    public void testMaxDepth() {
        System.out.println(maxDepth(TreeUtils.getTreeDemo()));
    }

    /**
     * LeetCode 110:判断一个树是否为平衡树
     * ⼀个⼆叉树每个节点 的左右两个⼦树的⾼度差的绝对值不超过 1
     * 思路：返回树的高度，如果高度差不满足的时候返回-1，不再递归
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        int height = height(root);
        if(height == -1){
            return false;
        }else{
            return true;
        }
    }

    public int height(TreeNode root){
        if(root == null){
            return 1;
        }
        int leftHeight = height(root.left);
        if(leftHeight == -1){
            return -1;
        }
        int rightHeight = height(root.right);
        if(rightHeight == -1){
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight)>1){
            return -1;
        }else{
            return Math.max(leftHeight,rightHeight) + 1;
        }

    }

    @Test
    public void testIsBalanceTree() {
        System.out.println(isBalanced(TreeUtils.getTreeDemo()));

    }

    /**
     * LeetCode 111:求树的最小深度
     * 通过层序遍历实现
     *
     * @param root
     * @return
     */
    public int minDeepth(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        int minDepth = Integer.MIN_VALUE;
        queue.add(root);
        int ans = 0; // 最小深度
        while (queue.size() > 0) {
            int size = queue.size();
            TreeNode treeNode = queue.poll();
            for (int i = 0; i < size; i++) {
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                if (treeNode.left == null && treeNode.right == null) {
                    return ans;
                }
            }
            ans++;
        }
        return -1;
    }

    /**
     * LeetCode 111:求树的最小深度
     * 通过层序遍历实现 : 高度和深度是一个递归思路
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        if(root.left != null) leftDepth = minDepth(root.left);
        if(root.right != null) rightDepth = minDepth(root.right);
        return Math.min(leftDepth,rightDepth) + 1;
    }

    @Test
    public void testMinDeepth() {
        System.out.println(minDeepth(TreeUtils.getTreeDemo2()));
    }

}
