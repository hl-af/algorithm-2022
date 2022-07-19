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
            levelList.stream().forEach( node ->System.out.print(node.val +","));
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
                level.add(node.val);
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
     * LeetCode103 题，要求是：给定⼀个⼆叉树，返回其节点值的锯⻮形层序遍历。（即先从左往右，再从右往左进
     * ⾏下⼀层遍历，以此类推，层与层之间交替进⾏）。
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelNum = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if ((levelNum % 2) == 0) {
                    level.add(node.val);
                }else {
                    level.add(0, node.val);
                }
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            levelNum++;
            result.add(level);//这样的方式才能保证插入的是在最前面
        }
        return result;
    }

    @Test
    public void testZigzagLevelOrder() {
        List<List<Integer>> result = zigzagLevelOrder(TreeUtils.getTreeDemo());
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
                if (maxLevelElement < node.val) {
                    maxLevelElement = node.val;
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
                    result.add(node.val);
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
     * 自拟题目 (二叉树右视图)给定⼀个⼆叉树的根节点 root，想象⾃⼰站在它的左侧，按照从顶部到底部的顺序，
     * 返回从左侧所能看到的节点值。
     */
    public List<Integer> leftSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
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
                if (i == 0) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    @Test
    public void testLeftSideView() {
        System.out.println(leftSideView(TreeUtils.getTreeDemo()));
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
                    result = node.val;
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



    /**
     * LeetCode429 多叉树的层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> nLevelOrder(Node root) {
        if (root == null){
            return new ArrayList<>();
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size ; i++) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node childNode : node.children) {
                        if (childNode != null) {
                            queue.offer(childNode);
                        }
                    }
                }
                list.add(node.val);
            }
            result.add(list);
        }
        return result;
    }

    @Test
    public void testNLevelOrder() {
        Node root = TreeUtils.getNnodeTreeDemo(3);
        List<List<Integer>> result = nLevelOrder(root);
        for (List<Integer> level : result) {
            for (Integer num : level) {
                System.out.print(num + ",");
            }
        }
    }


}
