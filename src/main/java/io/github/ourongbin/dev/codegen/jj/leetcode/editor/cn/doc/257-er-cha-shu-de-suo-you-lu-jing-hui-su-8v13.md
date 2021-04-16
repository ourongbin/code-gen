### 解题思路
利用深度优先的回溯法对树节点进行遍历。

### 复杂度分析
![image.png](https://pic.leetcode-cn.com/1617172149-IeFmBA-image.png)

### 代码
- 隐式撤销选择（实际上因为用了new StringBuffer(sb)重新创建了一个对象，所以从尝试做出选择到回退状态这一过程sb是没有参与的）
```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        dfs(path, new StringBuffer(), root);
        return path;
    }

    public void dfs(List<String> path, StringBuffer sb, TreeNode root){
        //空节点直接返回
        if(root == null) return;
        //不是空节点，将当前节点加入路径中
        sb.append(root.val);
        //当前节点是根节点：将当前路径加入路径集中
        if(root.left == null && root.right == null){
            path.add(sb.toString());
            return;
        }
        //深度优先搜索：尝试做出选择
        dfs(path, new StringBuffer(sb).append("->"), root.left);
        dfs(path, new StringBuffer(sb).append("->"), root.right);
    
    }
}
```

- 贴一个显式撤销选择的版本：
```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        dfs(path, new StringBuffer(), root);
        return path;
    }

    public void dfs(List<String> path, StringBuffer sb, TreeNode root){
        if(root == null){
            return;
        } 
        sb.append(root.val);
        if(root.left == null && root.right == null){
            path.add(sb.toString());
            return;
        }
        int length = sb.length();
        //尝试做出选择
        dfs(path, sb.append("->"), root.left);
        //显式撤销选择
        sb.delete(length, sb.length());
        //尝试做另一种选择
        dfs(path, sb.append("->"), root.right);  
    }
}
```
