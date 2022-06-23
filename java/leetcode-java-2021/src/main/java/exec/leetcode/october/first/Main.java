package exec.leetcode.october.first;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>#199 二叉树的右视图 II</h1>
 * <a href = 'https://leetcode-cn.com/problems/binary-tree-right-side-view/'>题目</a>
 *
 * @author likeguo
 */
public class Main {

    public static void main(String[] args) {
        final TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3, null, new TreeNode(4)));
        final List<Integer> result = new Main().rightSideView(treeNode);
        System.out.println(result);
    }

    List<Integer> result = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        rightSideView(root, 0);
        return result;
    }

    private void rightSideView(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.val);
        }
        depth++;
        rightSideView(root.right, depth);
        rightSideView(root.left, depth);

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
