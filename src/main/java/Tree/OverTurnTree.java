package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 226 翻转二叉树
 */
public class OverTurnTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    @Test
    public void testInvertTree() {
        TreeNode root = TreeUtils.getTreeDemo();
        invertTree(root);
        System.out.println();

    }

    //层次遍历翻转二叉树
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                res.add(node.num);
            }
        }
        return root;
    }

    @Test
    public void testInvertTree2() {
        invertTree2(TreeUtils.getTreeDemo());
    }

}
