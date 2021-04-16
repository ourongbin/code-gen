### 解题思路

![图片.png](https://pic.leetcode-cn.com/1617703261-eQbVdI-%E5%9B%BE%E7%89%87.png)

### 代码

```java
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
    public boolean isBalanced(TreeNode root) {
        if(root == null) {//树为空 一定为平衡
            return true;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        //根节点的左右两棵树的高度差是否<2 并且递归左树是否平衡 并且递归右树是否平衡  要同时满足
        // Math.abs（）求绝对值
        return Math.abs(leftHeight - rightHeight) < 2 &&
        isBalanced(root.left) && isBalanced(root.right);
    }
    //求得树的最大深度
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return leftHeight > rightHeight ? leftHeight+1 : rightHeight+1; 
    }
}
```