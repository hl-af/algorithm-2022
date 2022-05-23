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
     * @param root
     * @return
     */
    public int isBalanceTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = isBalanceTree(root.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalanceTree(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    @Test
    public void testIsBalanceTree() {
        System.out.println(isBalanceTree(TreeUtils.getTreeDemo()));

    }

    /**
     * LeetCode 111:求树的最小深度
     * 通过层序遍历实现
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

    @Test
    public void testMinDeepth() {
        System.out.println(minDeepth(TreeUtils.getTreeDemo2()));
    }

}
