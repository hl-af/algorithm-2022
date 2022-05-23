package Tree;

import java.util.Collections;
import java.util.List;

public class TreeUtils {

    /**
     * 普通二叉树 1，2，3，4，5，6，7，8
     * @return
     */
    public static TreeNode getTreeDemo() {
        TreeNode root = new TreeNode(1);
        int num = 2;
        root.left = new TreeNode(num++);
        root.right = new TreeNode(num++);
        root.left.left = new TreeNode(num++);
        root.left.right = new TreeNode(num++);
        root.right.left = new TreeNode(num++);
        root.right.right = new TreeNode(num++);
        return root;
    }

    /**
     * 只有右边的二叉树
     * @return
     */
    public static TreeNode getTreeDemo2() {
        TreeNode root = new TreeNode(1);
        int num = 2;
        root.right = new TreeNode(num++);
        root.right.left = new TreeNode(num++);
        root.right.right = new TreeNode(num++);
        root.right.right.left = new TreeNode(num++);
        root.right.right.right = new TreeNode(num++);
        return root;
    }

    /**
     * 对称二叉树
     * @return
     */
    public static TreeNode getTreeDemo3() {
        TreeNode root = new TreeNode(1);
        int num = 2;
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        return root;
    }

    public static void printTree(List<Integer> res) {
        res.stream().forEach(num -> System.out.print(num + ","));
    }
}
