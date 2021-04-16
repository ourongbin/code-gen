### 解题思路
两种窗体滑动方法，第一种在原来的字符串上通过枚举的防止判断两个字符是否相等，如果相等获取长度并计算下一次左指针位置，右指针移动，如果不相等右指针移动，左指针不变，直到对比到当前位置之前。并对当前窗体长度做计算，如果长度大于上一次窗体长度则更新。

### 代码

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
    if (s.length() < 2) {
        return s.length();
    }
    char[] c = s.toCharArray();
    int left = 0;
    int right = 0;
    for (int i = 1; i < c.length; i++) {
        for (int j = left; j < i; j++) {
            if (c[i] == c[j]){
                left = j + 1;
            }
        }
        right = Math.max(right,(i+1-left));
        
    }
    return right;
    }
}
```
第二种则是通过数据结构 set不可重复的原则进行实现。下面文字属于官方题解问题，思路一致，直接复制引用
我们先用一个例子考虑如何在较优的时间复杂度内通过本题。

我们不妨以示例一中的字符串 \texttt{abcabcbb}abcabcbb 为例，找出从每一个字符开始的，不包含重复字符的最长子串，那么其中最长的那个字符串即为答案。对于示例一中的字符串，我们列举出这些结果，其中括号中表示选中的字符以及最长的字符串：

以 \texttt{(a)bcabcbb}(a)bcabcbb 开始的最长字符串为 \texttt{(abc)abcbb}(abc)abcbb；
以 \texttt{a(b)cabcbb}a(b)cabcbb 开始的最长字符串为 \texttt{a(bca)bcbb}a(bca)bcbb；
以 \texttt{ab(c)abcbb}ab(c)abcbb 开始的最长字符串为 \texttt{ab(cab)cbb}ab(cab)cbb；
以 \texttt{abc(a)bcbb}abc(a)bcbb 开始的最长字符串为 \texttt{abc(abc)bb}abc(abc)bb；
以 \texttt{abca(b)cbb}abca(b)cbb 开始的最长字符串为 \texttt{abca(bc)bb}abca(bc)bb；
以 \texttt{abcab(c)bb}abcab(c)bb 开始的最长字符串为 \texttt{abcab(cb)b}abcab(cb)b；
以 \texttt{abcabc(b)b}abcabc(b)b 开始的最长字符串为 \texttt{abcabc(b)b}abcabc(b)b；
以 \texttt{abcabcb(b)}abcabcb(b) 开始的最长字符串为 \texttt{abcabcb(b)}abcabcb(b)。
发现了什么？如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！这里的原因在于，假设我们选择字符串中的第 kk 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 r_kr 
k
​	
 。那么当我们选择第 k+1k+1 个字符作为起始位置时，首先从 k+1k+1 到 r_kr 
k
​	
  的字符显然是不重复的，并且由于少了原本的第 kk 个字符，我们可以尝试继续增大 r_kr 
k
​	
 ，直到右侧出现了重复字符为止。

这样一来，我们就可以使用「滑动窗口」来解决这个问题了：

我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 r_kr 
k
​	
 ；

在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；

在枚举结束后，我们找到的最长的子串的长度即为答案。

判断重复字符

在上面的流程中，我们还需要使用一种数据结构来判断 是否有重复的字符，常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的 HashSet，Python 中的 set, JavaScript 中的 Set）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || "".equals(s)){
            return 0;
        }
        char[] c = s.toCharArray();
        int len = c.length;
        Set<Character> window = new HashSet<>();
        int right = -1;
        int max = 0;
        for (int i = 0; i < len;i++){
            if(i!=0){
                window.remove(c[i-1]);
            }
            while (right + 1 < len && !window.contains(c[right+1])){
                window.add(c[right+1]);
                right++;
            }
            max = Math.max(max,right - i+1);
        }
        return max;
    }
}
```
