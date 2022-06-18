package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ScanTree {


    public List<Integer> preOrderScan(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                res.add(root.num);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }

    @Test
    public void testPreOrderScan() {
        List<Integer> res = preOrderScan(TreeUtils.getTreeDemo());
        TreeUtils.printTree(res);
    }
    /**
     * 迭代法实现中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderscan(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (stack.size() > 0 || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.num);
            root = root.right;
        }
        return res;
    }

    @Test
    public void testInorderscan() {
        TreeUtils.printTree(inorderscan(TreeUtils.getTreeDemo()));
    }

    //TODO 迭代法实现后续遍历
}
