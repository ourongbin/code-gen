### 解题思路
递归解法，将反转字符方法转化为反转头尾两字符的子方法，将大问题拆解为小问题，这个过程叫递，小问题需要做的事情就是把头尾交换，这个过程叫归。退出条件就是首>=尾或者字符串为空

### 代码

```python
class Solution(object):
    def reverseString(self, s):
        """
        :type s: List[str]
        :rtype: None Do not return anything, modify s in-place instead.
        """
        if s == None:
            return s
        def rever(str,i,j):
            if i>=j:
                return str
            str[i],str[j]=str[j],str[i]
            i+=1
            j-=1
            rever(str,i,j)
        rever(s,0,len(s)-1)
        return s
```