本题要求，求所有树的左叶子节点，这里抓关键字，一定要是左孩子并且是叶子节点
如何判断叶子节点很简单`root.left == null && root.right == null`
那么如何判断是左孩子呢？
这里我使用一个**标志位**判断叶子节点

具体是这样的写的
当你写递归函数的时候是不是递归左孩子`sumOfLeftLeaves(root.left);`
递归右孩子`sumOfLeftLeaves(root.right);`
这里就非常明显了，递归左孩子是不是说明这个节点一定是左孩子
我们就可以将代码这样改写`sumOfLeftLeaves(root.left, true);`,`sumOfLeftLeaves(root.right, false);`

参考代码
```java []
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }
    public int sumOfLeftLeaves(TreeNode root, boolean flag){
        if(root == null){
            return 0;
        }
        if(flag && root.left == null && root.right == null){
            return root.val;
        }
        int leftSum = sumOfLeftLeaves(root.left, true);
        int rightSum = sumOfLeftLeaves(root.right, false);
        return leftSum + rightSum;
    }
}
```