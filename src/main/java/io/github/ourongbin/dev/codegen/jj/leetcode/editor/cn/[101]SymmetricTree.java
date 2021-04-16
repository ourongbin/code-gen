package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1335 👎 0


class SymmetricTree {
    public static void main(String[] args) {
        SymmetricTree question = new SymmetricTree();
        Solution solution = question.new Solution();
        System.out.println(args);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            return isSymmetric(root.left, root.right);
        }

        public boolean isSymmetric(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }

            if (p != null && q != null && p.val == q.val) {
                return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}