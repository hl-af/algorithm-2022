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
     * LeetCode131 分割回⽂串，给你⼀个字符串s，请你将s分割成⼀些⼦串，使每个⼦串都是回⽂串 ，返回s所有可能
     * 的分割⽅案。
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


    /**
     * LeetCode93.有效IP地址正好由四个整数（每个整数位于 0 到 255 之间组
     * 成，且不能含有前导 0），整数之间⽤ '.'
     * @param s
     * @return
     */
//    public Boolean isValidIP(String s) {
//
//
//    }

    public void dfs(String s, List<String> path, int start, int end) {
        if (end - start > 2) {
            return;
        }
        if (path.size() == 4) {
            return;
        }
        String num = s.substring(start, end);
        path.add(num);
        for (int i = 1; i < 3; i++) {
            dfs(s, path, start, start + i);
        }

    }

    /**
     * LeetCode78，给你⼀个整数数组nums，数组中的元素互不相同。返回该数组所有可能的⼦集（幂集）。解集不能
     * 包含重复的⼦集。你可以按任意顺序返回解集。
     * 输⼊：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subSetsHelper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void subSetsHelper(int[] nums, int startIndex, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        if (startIndex == nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subSetsHelper(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void testsubsets() {
        int[] array = {1, 2, 3};
        System.out.println(subsets(array));
    }

    /**
     * LeetCode46.给定⼀个没有重复数字的序列，返回其所有可能的全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        Set<Integer> usedSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            usedSet.add(nums[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(nums, usedSet, new ArrayList<>(), res);
        return res;
    }


    public void permuteHelper(int[] nums,Set<Integer> usedSet, List<Integer> path, List<List<Integer>> res) {
        if (usedSet.isEmpty()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (usedSet.contains(nums[i])) {
                path.add(nums[i]);
                usedSet.remove(nums[i]);
                permuteHelper(nums, usedSet, path, res);
                path.remove(path.size() - 1);
                usedSet.add(nums[i]);
            }
        }
    }

    @Test
    public void testPermute() {
        int[] array = {1, 2, 3};
        System.out.println(permute(array));
    }

    /**
     * LeetCode17.电话号码组合问题，也是热度⾮常⾼的⼀个题⽬，给定⼀个仅包含数字 2-9 的字符串，返回所有它能
     * 表示的字⺟组合。 给出数字到字⺟的映射如下(与电话按键相同)。注意 1 不对应任何字⺟，9对应四个字⺟。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        helper(digits, 0, new StringBuffer(), res, numString);
        return res;
    }

    public void helper(String digits, int startIndex, StringBuffer path, List<String> res,String[] numString) {

        if (startIndex == digits.length()) {
            res.add(new String(path));
            return;
        }

//        int num = Integer.valueOf(digits.charAt(startIndex)); //这样的方式竟然打印出来的是ascii值
        int num = digits.charAt(startIndex) - '0';
        String str = numString[num];
        for (int i = 0; i < str.length(); i++) {
            path.append(str.charAt(i));
            helper(digits, startIndex + 1, path, res, numString);
            path.replace(path.length() - 1, path.length(), "");
        }
    }

    @Test
    public void testLetterCombinations() {
        String digits = "32";
        System.out.println(letterCombinations(digits));
    }

    /**
     * LeetCode22.数字 n 代表⽣成括号的对数，请你设计⼀个函数，⽤于能够⽣成所
     * 有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        int left = n;
        int right = n;
        List<String> res = new ArrayList<>();
        parenthesisHelper(left, right, new StringBuilder(), res);
        return res;
    }

    public void parenthesisHelper(int left, int right, StringBuilder path, List<String> res) {

        if (left <= 0 && right <= 0) {
            res.add(new String(path));
            return;
        }

        if (left > 0) {  //可以出左括号
            path.append("(");
            left = left - 1; //需要这样写，如果放到参数里left--传递的参数是没有减少的，注意⚠️
            parenthesisHelper(left, right, path, res);
            left++;
            path.deleteCharAt(path.length() - 1);
        }
        if (left < right) { //可以出右括号
            path.append(")");
            right = right - 1;
            parenthesisHelper(left, right, path, res);
            right++;
            path.deleteCharAt(path.length() - 1);
        }
    }

    @Test
    public void testGenerateParenthesis() {
        System.out.println(generateParenthesis(3));
    }

    /**
     * LeetCode784. 字⺟⼤⼩写全排列：给定⼀个字符串 s ，通过将字符串 s 中的每个字⺟转变⼤⼩写，我们可以获得
     * ⼀个新的字符串。
     * @param s
     * @return
     */
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        char[] path = s.toCharArray();
        caseHelper(s, 0, path, res);
        return res;
    }

    public void caseHelper(String str, int startIndex, char[] path, List<String> res) {
        res.add(Arrays.toString(path));
        for (int i = startIndex; i < str.length(); i++) {
            if (!isLetter(path[i])) {
                continue;
            }
            path = changeLetter(path, i);
            caseHelper(str, i + 1, path, res);
            res.add(Arrays.toString(path));

        }
    }

    public char[] changeLetter(char[] array,int startIndex) {
        if (isSmallCase(array[startIndex])) {
            array[startIndex] -= 32;
        }else {
            array[startIndex] += 32;
        }
        return array;
    }
    public boolean isSmallCase(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }else {
            return false;
        }
    }
    public boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) { // 记得加=号
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void testLetterCasePermutation() {
        List<String> res = letterCasePermutation("a1b2");
        System.out.println(res);
//        char c = 'b' - 32;
//        System.out.println(c);
    }

}
