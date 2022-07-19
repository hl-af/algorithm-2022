package Tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer 45 寻找二叉树最左边的节点
 */
public class LeastLeftNode {

    /**
     * 用层次遍历法来求解
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int res = -1;
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            int level = 0;
            int currentMinLevel = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0 && level > currentMinLevel) {
                    currentMinLevel = level;
                    res = node.val;
                }
                level++;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return res;
    }

    @Test
    public void testFindBottomLeftValue() {
        System.out.println(findBottomLeftValue(TreeUtils.getTreeDemo2()));
    }

    /**
     * 用翻转法来求最左边的节点
     * @param root
     * @return
     */
    public int findBottomLeftValue2(TreeNode root) {

        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = new TreeNode(-1);
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                temp = queue.poll();

                if (temp.right != null) {
                    queue.add(temp.right);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
            }
        }
        return temp.val;
    }

    @Test
    public void testFindBottomLeftValue2() {
        System.out.println(findBottomLeftValue2(TreeUtils.getTreeDemo()));
    }

}
