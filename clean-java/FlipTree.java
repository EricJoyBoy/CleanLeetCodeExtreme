
public class FlipTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return compareTrees(root1, root2);
    }

    private boolean compareTrees(TreeNode t1, TreeNode t2) {
        // Base case: Both trees are null
        if (t1 == null && t2 == null) return true;

        // One tree is null, the other is not
        if (t1 == null || t2 == null) return false;

        // Values don't match
        if (t1.val != t2.val) return false;

        // Try both normal and flipped comparisons
        return (compareSubtrees(t1.left, t2.left) && compareSubtrees(t1.right, t2.right)) ||
               (compareSubtrees(t1.left, t2.right) && compareSubtrees(t1.right, t2.left));
    }

    private boolean compareSubtrees(TreeNode t1, TreeNode t2) {
        return compareTrees(t1, t2);
    }
}
