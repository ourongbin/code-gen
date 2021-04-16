### 方法一：快慢指针（fast一次两步，slow一次一步）
**快慢指针**：**`fast`** 指针一次走两步，**`slow`** 指针一次走一步。

**若链表中存在环**，则 fast 指针和 slow 指针一定会相遇，**`即 fast == slow`**，此时返回 true；否则，返回 false 即可。

---
### 代码
```java
public class Solution {

    public boolean hasCycle(ListNode head) {
        
        if (head == null) return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {

                return true;
            }
        }
        return false;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(1)

---
### 方法二：哈希表
从头开始遍历链表并将节点不断加入到哈希表中，若当前遍历的节点在哈希表中存在了，则说明链表中有环，返回 true；否则，继续遍历到链表结尾仍没有重复节点，则说明链表中不存在环，返回 false。

>**注意**：由于该方法开辟了新的空间，并多了向哈希表中添加和查询的操作，所以运行的时间会比方法一慢很多。

### 代码
```java
public class Solution {

    public boolean hasCycle(ListNode head) {
        
        if (head == null) return false;

        Set<ListNode> hashSet = new HashSet<>();
        ListNode p = head;

        while (p.next != null) {

            if (hashSet.contains(p)) {

                return true;
            } else {

                hashSet.add(p);
                p = p.next;
            }
        }
        return false;
    }
}
```
时间复杂度：O(n)
空间复杂度：O(n)