package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 
// 👍 684 👎 0


import java.util.Stack;

class DiameterOfBinaryTree {
    public static void main(String[] args) {
        DiameterOfBinaryTree question = new DiameterOfBinaryTree();
        Solution solution = question.new Solution();
        System.out.println(args);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

//        int res = 0;
//
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//        while (root != null || !stack.isEmpty()) {
//            if (root != null) {
//                stack.push(root);
//                root = root.left;
//            } else {
//                root = stack.pop();
//                int diameter = depthOfBinaryTree(root.left) + depthOfBinaryTree(root.right);
//                res = Math.max(diameter, res);
//                root = root.right;
//            }
//        }
        depthOfBinaryTree(root);
        return res;
    }
    public int depthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = depthOfBinaryTree(root.left), rDepth = depthOfBinaryTree(root.right);
        res = Math.max(lDepth + rDepth, res);
        return Math.max(lDepth, rDepth) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}