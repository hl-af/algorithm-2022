package Tree;

import ListNodeUtils.ListNode;
import org.junit.Test;

import java.util.LinkedList;

public class CommonTreeNode {

    /**
     * LeetCode235 .给定⼀个⼆叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestSearchAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((root.val - p.val) * (root.val - q.val) >= 0) { // 二叉树在不同侧，那么公共节点就是，答案的版本是有零的情况，似乎不存在
            return root;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    /**
     * 是LeetCode236 找到普通二叉树的最近公共节点
     * @param root
     * @param p
     * @param q
     * @return
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> list1 = dfs(root, p, new LinkedList<>());
        LinkedList<TreeNode> list2 = dfs(root, q, new LinkedList<>());
        return findCommonList(list1, list2);
    }

    public TreeNode findCommonList(LinkedList<TreeNode> list1, LinkedList<TreeNode> list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        int min = Math.min(size1, size2);
        int delta = 0;
        int i = 0;
        while (i < min && (list1.get(i) == list2.get(i))) i++; //注意这里构造的是一个先都相同，后面不同的列表
//            while (list1.get(delta + i++) != list2.get(i++)) //这样的写法i一下子会加到2，错误写法
        return list1.get(i - 1);

    }

    public LinkedList<TreeNode> dfs(TreeNode root, TreeNode target,LinkedList<TreeNode> list) {
        if (root == null) {
            return null;
        }
        list = new LinkedList<>(list);
        list.add(root);
        if (root == target) {
            return list;
        }

        LinkedList<TreeNode> leftlist = dfs(root.left, target, list);
        if (leftlist != null) {
            return leftlist;
        }
        LinkedList<TreeNode> rightlist = dfs(root.right, target, list);
        if (rightlist != null) {
            return rightlist;
        }
        return null;
    }
    @Test
    public void testlowestCommonAncestor() {
        TreeNode root = new TreeNode(1);
        int num = 2;
        root.left = new TreeNode(num++);
        TreeNode p = root.left;//节点2
        root.right = new TreeNode(num++);
        root.left.left = new TreeNode(num++);
        TreeNode q = root.left.left;//节点4
        root.left.right = new TreeNode(num++);
        root.right.left = new TreeNode(num++);
        root.right.right = new TreeNode(num++);
        TreeNode commonRoot = lowestCommonAncestor(root, p, q);
        System.out.println(commonRoot.val);
    }

}
