package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 484 👎 0


import java.util.ArrayList;
import java.util.List;

class BinaryTreePaths {
    public static void main(String[] args) {
        BinaryTreePaths question = new BinaryTreePaths();
        Solution solution = question.new Solution();
        System.out.println(args);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        List<String> res = new ArrayList<>();
        public List<String> binaryTreePaths(TreeNode root) {
            binaryTreePaths(root, "");
            return res;
        }

        public void binaryTreePaths(TreeNode root, String path) {
            if (root == null) {
                return;
            }
            path = path + root.val;
            if (root.left == null && root.right == null) {
                res.add(path);
            }
            binaryTreePaths(root.left, path + "->");
            binaryTreePaths(root.right, path + "->");
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}