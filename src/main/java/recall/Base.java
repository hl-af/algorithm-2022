package recall;

import Tree.TreeNode;
import Tree.TreeUtils;
import org.junit.Test;

import java.util.*;

/**
 * 回溯法基本
 */
public class Base {


    /**
     * LeetCode77 ：给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
     * @param n 范围
     * @param k 组合的元素个数
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, path, res, k, n, 0);
        return res;
    }

    public void dfs(int startIndex, Deque<Integer> path, List<List<Integer>> res, int k, int n, int depth) {

        if (depth == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            dfs(startIndex + 1, path, res, k, n, depth + 1);
            path.remove(i);
        }
    }

    @Test
    public void testCombine() {
        List<List<Integer>> res = combine(12, 2);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }


    /**
     * LeetCode257：给你⼀个⼆叉树的根节点root ，按任意顺序 ，返回所有从根节点到叶⼦节点的路径。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Stack<TreeNode> path = new Stack<>();
        dfs(root, res, path);
        return res;
    }

    public void dfs(TreeNode root, List<String> res, Stack<TreeNode> path) {

        if (root == null) {
            return;
        }

        path.add(root);

        if (root.getLeft() == null && root.getRight() == null) {
            addToRes(res, path);
            return;
        }
        dfs(root.getLeft(), res, path);
        path.pop();
        dfs(root.getRight(), res, path);
        path.pop();
    }

    public void addToRes(List<String> res, Stack<TreeNode> path) {
        StringBuffer stringBuffer = new StringBuffer();
        int size = path.size();
        List<TreeNode> temp = new ArrayList<>(path);
        for (int i = 0; i < size; i++) {  //易错代码，出队会改变大小
            TreeNode node = temp.get(i);
            stringBuffer.append(node.getVal() + "");
            if (i < size - 1) {
                stringBuffer.append("->");
            }
        }
        res.add(stringBuffer.toString());
    }

    @Test
    public void testBinaryTreePaths() {
        List<String> res = binaryTreePaths(TreeUtils.getTreeDemo());
        for (String path : res) {
            System.out.println(path);
        }
    }

    /**
     * LeetCode113题，给你⼆叉树的根节点 root 和⼀个整数⽬标和 targetSum ，找出所有从根节点到叶
     * ⼦节点 路径总和等于给定⽬标和的路径。
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> res = new ArrayList<>();
        dfs(TreeUtils.getTreeDemo(), 7, new ArrayList<>(), res);
        return res;
    }

    public void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {

        if (root == null) {
            return;
        }
        path.add(root.getVal());

        if (root.getLeft() == null && root.getRight() == null) {
            if (caculateSum(path) == targetSum) {
                res.add(new ArrayList<>(path));// 记得开辟新空间，不然path在后面会被删除
            }
            return;
        }
        dfs(root.getLeft(), targetSum, path, res);
        path.remove(path.size() - 1);
        dfs(root.getRight(), targetSum, path, res);
        path.remove(path.size() - 1);
    }

    public int caculateSum(List<Integer> path) {
        if (path == null) {
            return 0;
        }
        int sum = 0;
        for (Integer val : path) {
            sum += val;
        }
        return sum;
    }

    @Test
    public void testPathSum() {

        List<List<Integer>> res = pathSum(TreeUtils.getTreeDemo(), 7);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }


    /**
     * LeetCode39题⽬要求：给你⼀个⽆重复元素的整数数组candidates和⼀个⽬标整数 target ，找出 candidates 中
     * 可以使数字和为⽬标数 target 的 所有不同组合 ，并以列表形式返回。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates,target,0,new ArrayList<>(),res);
        return res;
    }

    public void dfs(int[] candidates, int target, int startIndex, List<Integer> path, List<List<Integer>> res) {

        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, path, res);//运算放在表达式上，不要放在前面
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void testCombinationSum() {
        int[] sum = {2, 3, 6, 7};
        List<List<Integer>> res = combinationSum(sum, 7);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    /**
     *
     * @param str
     * @return
     */
    public List<List<String>> findPalindromeWords(String str) {
        List<List<String>> res = new ArrayList<>();
        dfs(str,new ArrayList<>(),0,res);
        return res;
    }

    public void dfs(String str, List<String> path, int startIndex, List<List<String>> res) {

        if (startIndex == str.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {
            String words = str.substring(startIndex, i + 1);
            if (isPalindrome(words)) {
                path.add(words);
                dfs(str, path, i + 1, res); //如果i+1变成startIndex是有问题的，会出现重复
                path.remove(path.size() - 1);
            }else {
                continue;
            }
        }
    }

    public Boolean isPalindrome(String str) {
        int len = str.length() / 2;
        StringBuffer lastwordBuffer = new StringBuffer(str.substring(len));
        return str.startsWith(lastwordBuffer.reverse().toString());
    }

    @Test
    public void testfindPalindromeWords() {
        String words = "aab";
        System.out.println(isPalindrome(words));
        List<List<String>> res = findPalindromeWords(words);
        for (List<String> list : res) {
            System.out.println(list);
        }
    }


}
