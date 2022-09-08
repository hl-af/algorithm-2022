package Tree;


public class TreeNode {
    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    TreeNode left;
    TreeNode right;

    public int getVal() {
        return val;
    }

    int val;

    public TreeNode(int val) {
        this.val = val;
    }
}
