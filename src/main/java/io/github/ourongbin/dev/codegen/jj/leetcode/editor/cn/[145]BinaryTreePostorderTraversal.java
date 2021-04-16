package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一个二叉树，返回它的 后序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 564 👎 0


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        BinaryTreePostorderTraversal question = new BinaryTreePostorderTraversal();
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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    res.add(0, root.val);
                    if (root.left != null) {
                        stack.push(root.left);
                    }
                    root = root.right;
                } else {
                    root = stack.pop();
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}