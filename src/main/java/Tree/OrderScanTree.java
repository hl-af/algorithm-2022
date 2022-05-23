package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderScanTree {

    /**
     * 递归实现先序遍历
     */
    public void preOrderTraversal(TreeNode root,List<Integer> res) {

        if (root == null) {
            return;
        }

        res.add(root.num);
        preOrderTraversal(root.left,res);
        preOrderTraversal(root.right,res);
    }

    @Test
    public void testPreOrderTraversal() {
        List<Integer> res = new ArrayList<>();
        preOrderTraversal(TreeUtils.getTreeDemo(), res);
        TreeUtils.printTree(res);
    }


    /**
     * 递归中序遍历
     * @param root
     * @param res
     */
    public void inOrderTraversal(TreeNode root,List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, res);
        res.add(root.num);
        inOrderTraversal(root.right, res);
    }

    @Test
    public void testInOrderTraversal() {
        List<Integer> res = new ArrayList<>();
        inOrderTraversal(TreeUtils.getTreeDemo(), res);
        TreeUtils.printTree(res);
    }

    /**
     * 递归后续遍历
     * @param root
     * @param res
     */
    public void postOrderTraversal(TreeNode root,List<Integer> res) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left,res);
        postOrderTraversal(root.right, res);
        res.add(root.num);
    }

    @Test
    public void testPostOrderTraversal() {
        List<Integer> res = new ArrayList<>();
        postOrderTraversal(TreeUtils.getTreeDemo(), res);
        TreeUtils.printTree(res);
    }


}
