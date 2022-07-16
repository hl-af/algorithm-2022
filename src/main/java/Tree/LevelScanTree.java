package Tree;

import org.junit.Test;

import java.util.*;

public class LevelScanTree {

    /**
     * 基本层次遍历，需要背诵
     * @param root
     * @return
     */
    public List<TreeNode> simpleLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> resNodes = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode node = queue.remove();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            resNodes.add(node);
        }
        return resNodes;
    }

    public List<TreeNode> levelScanTree(TreeNode treeNode){
        if (treeNode == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> result = new ArrayList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                result.add(cur);
                TreeNode left = cur.left;
                TreeNode right = cur.right;
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
        }
        return result;
    }



    @Test
    public void testSimpleLevelOrder() {
//        List<TreeNode> levelList = simpleLevelOrder(TreeUtils.getTreeDemo());
        List<TreeNode> levelList = levelScanTree(TreeUtils.getTreeDemo());
        if (Objects.nonNull(levelList)) {
            levelList.stream().forEach( node ->System.out.print(node.num+","));
        }
    }

    /**
     * 测试队列的数据结构
     */
    @Test
    public void testQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
    }


    /**
     * LeetCode 107 .自底向上遍历二叉树，返回每层的元素
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left, right = node.right;
                level.add(node.num);
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
            result.add(0, level);//这样的方式才能保证插入的是在最前面
        }
        return result;
    }

    @Test
    public void testLevelOrderBottom() {
        List<List<Integer>> result = levelOrderBottom(TreeUtils.getTreeDemo());
        for (List<Integer> level : result) {
            for (Integer num : level) {
                System.out.print(num + ",");
            }
        }
    }

    /**
     * 找到二叉树每层最大的元素
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> maxElePerLevelList = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            int maxLevelElement = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (maxLevelElement < node.num) {
                    maxLevelElement = node.num;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            maxElePerLevelList.add(maxLevelElement);
        }
        return maxElePerLevelList;
    }

    @Test
    public void testLargestValues() {
        System.out.println(largestValues(TreeUtils.getTreeDemo()));
    }

    /**
     * LeetCode 199 (二叉树右视图)给定⼀个⼆叉树的根节点 root，想象⾃⼰站在它的右侧，按照从顶部到底部的顺序，
     * 返回从右侧所能看到的节点值。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
                if (i == size - 1) {
                    result.add(node.num);
                }
            }
        }
        return result;
    }


    @Test
    public void testRightSideView() {
        System.out.println(rightSideView(TreeUtils.getTreeDemo()));
    }

    /**
     * offer 45二叉树最底层最左边元素
     */

    public int findBottomLeftValue(TreeNode root){
        if (root == null){
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result = node.num;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

        }
        return result;
    }

    @Test
    public void testFindBottomLeftValue() {
        int val = findBottomLeftValue(TreeUtils.getTreeDemo());
        System.out.println(val);
    }



}
