package Tree;

import org.junit.Test;

public class DoublePointer {

    /**
     * 判断两棵树是否为相同的树 LeetCode100
     * 使用先序遍历来判断每个节点是否相同
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        Boolean leftFlag;
        Boolean rightFlag;

        if (p == null && q == null) { // 这个条件必须在前面，因为先判断两个是否都为空，不是的话判断下面
            return true;
        }
        if (p == null || q == null) { // 能判断出只有一个为空的情况
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        leftFlag = isSameTree(p.left, q.left);
        rightFlag = isSameTree(p.right, q.right);
        return leftFlag & rightFlag;
    }

    public boolean isSameTreeMe(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q== null) {
            return true;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    @Test
    public void testIsSameTree() {
//        System.out.println(isSameTree(TreeUtils.getTreeDemo(), TreeUtils.getTreeDemo2()));
        System.out.println(isSameTreeMe(TreeUtils.getTreeDemo(), TreeUtils.getTreeDemo2()));
    }

    /**
     * 判断两棵树是否为对称树 LeetCode101
     * 使用两个指针来判断是否是镜像二叉树，使用先序遍历来判断每个节点是否相同
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }
        return doSymmertric(root.left, root.right);
    }

    public boolean doSymmertric(TreeNode p, TreeNode q) {
        boolean leftFlag;
        Boolean rightFlag;

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        leftFlag = doSymmertric(p.left, q.right);
        rightFlag = doSymmertric(p.right, q.left);
        return leftFlag & rightFlag;
    }

    @Test
    public void testIsSymmetric() {
        System.out.println(isSymmetric(TreeUtils.getTreeDemo2()));
    }

    /**
     * 判断两个二叉树是否是对称二叉树
     * @param p
     * @param q
     * @return
     */
    public Boolean isSymmetricTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
    }

    @Test
    public void testIsSymmetricTree() {
//        System.out.println(isSymmetricTree(TreeUtils.buildBinaryTree(), TreeUtils.buildBinaryTree2()));
        System.out.println(isSymmetricTree(TreeUtils.buildBinaryTree(), TreeUtils.getTreeDemo2()));
    }




    public TreeNode mergeTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return p;
        }

        if (p != null && q != null) {
            p.val = p.val + q.val;
        }
        if (p == null && q != null) {
            p = q;
        }
        if (p != null && q != null) {
            mergeTree(p.left, q.left);
            mergeTree(p.right, q.right);
        }
        return p;

    }

    @Test
    public void testMergeTree() {
        TreeNode treeNode = mergeTree(TreeUtils.getTreeDemo(), TreeUtils.getTreeDemo2());
        System.out.println(treeNode);
    }

}
