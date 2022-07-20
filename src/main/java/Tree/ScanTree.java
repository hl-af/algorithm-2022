package Tree;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.*;

public class ScanTree {

    /**
     * Leetcode 144迭代法实现前序遍历
     * @param root
     * @return
     */
    public List<Integer> preOrderScan(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                res.add(root.val);
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
     * LeetCode94 迭代法实现中序遍历
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
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
    @Test
    public void testInorderscan() {
        TreeUtils.printTree(inorderscan(TreeUtils.getTreeDemo()));
    }
    /**
     * LeetCode145后序遍历
     *  使用反转链表法来做
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        Collections.reverse(res);
        return res;
    }

    @Test
    public void testPostOrderTraversal() {
        List<Integer> res = postOrderTraversal(TreeUtils.getTreeDemo());
        TreeUtils.printTree(res);
    }
}
