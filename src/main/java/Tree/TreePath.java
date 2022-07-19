package Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树路径专题
 */
public class TreePath {

    /**
     * LeetCode257：遍历二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> pathList = new ArrayList<>();
        doScanPath(root, "", pathList);
        return pathList;
    }

    public void doScanPath(TreeNode root, String path,List<String> pathList) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path = path + root.val;
            pathList.add(path);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.append(root.val + "->");
        doScanPath(root.left, stringBuilder.toString(), pathList);
        doScanPath(root.right, stringBuilder.toString(), pathList);
    }

    @Test
    public void testDoPath() {
        System.out.println(binaryTreePaths(TreeUtils.getTreeDemo()));
    }

    /**
     * 是LeetCode112题：是否有某一条路径的和为sum
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }

    public boolean dfs(TreeNode root, int num,int sum) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            num = num + root.val;
            if (num == sum) {
                return true;
            } else {
                return false;
            }
        }
        num = num + root.val;
        Boolean leftFlag = dfs(root.left, num, sum);
        Boolean rightFlag = dfs(root.right, num, sum);
        return leftFlag || rightFlag;
    }

    @Test
    public void testHasPathSum() {
        System.out.println(hasPathSum(TreeUtils.getTreeDemo(), 1));
    }

    //LeetCode 113输出路径和为sum的所有路径 是LeetCode113
    public List<List<Integer>> pathSum(TreeNode root,int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        doPathSum(root, new ArrayList<>(), res, targetSum);
        return res;

    }

    public void doPathSum(TreeNode root, List<Integer> currentList, List<List<Integer>> res, int targetSum) {

        if (root == null) {
            return;
        }
        currentList = new ArrayList<>(currentList);
        currentList.add(root.val);
        if (root.left == null && root.right == null) {
            int sum = sum(currentList);
            if (sum == targetSum) {
                res.add(currentList);
            }
        }
        doPathSum(root.left, currentList, res, targetSum);
        doPathSum(root.right, currentList, res, targetSum);
    }

    public int sum(List<Integer> currentList) {
        int resutl = 0;
        for (Integer num : currentList) {
            resutl = resutl + num;
        }
        return resutl;
    }

    @Test
    public void testPathSum() {
        List<List<Integer>> res = pathSum(TreeUtils.getTreeDemo(), 7);
        System.out.println();
    }

}
