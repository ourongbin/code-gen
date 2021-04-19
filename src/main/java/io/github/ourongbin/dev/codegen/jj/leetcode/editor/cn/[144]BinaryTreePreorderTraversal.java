package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 553 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        BinaryTreePreorderTraversal question = new BinaryTreePreorderTraversal();
        Solution solution = question.new Solution();
        System.out.println(solution.binarySearch(new int[] {}, 2));
        System.out.println(solution.binarySearch(new int[] {1}, 2));
        System.out.println(solution.binarySearch(new int[] {2}, 2));
        System.out.println(solution.binarySearch(new int[] {1, 3}, 2));
        System.out.println(solution.binarySearch(new int[] {1, 2}, 2));
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
        public int binarySearch(int[] nums, int target) {
            int res = -1;
            if (nums == null || nums.length ==0) {
                return res;
            }

            int low = 0;
            int high = nums.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}