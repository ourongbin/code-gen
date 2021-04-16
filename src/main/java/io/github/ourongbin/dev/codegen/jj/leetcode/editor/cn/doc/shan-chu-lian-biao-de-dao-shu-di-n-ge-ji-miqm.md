### 方法一：直接遍历链表，计算链表长度
首先计算出链表的长度 **`count`**，然后令 **`p`** 指针指向 **`head`** 节点，遍历链表中的节点（**遍历结束后，p 指针指向要删除的倒数第 n 个节点的前一个节点**）。

判断要删除的节点是否是尾节点：**若不是尾节点**，则令 p 指针指向的当前节点的下一节点为要删除的节点的下一节点，**`即 p.next = p.next.next`**；**若是尾节点**，则直接将 p 指针指向的当前节点变成尾节点，**`即 p.next = null`**。

>特殊情况：若链表中没有节点或 n 为 0，则直接**返回 head** 即可。
若链表中只有一个节点而又要求删除倒数第 1 个节点，则直接**返回 null** 即可。
若 n == count，则要删除链表中的第一个节点，此时直接**返回 head.next** 即可。

```java
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || n == 0) return head;
        ListNode p = head;
        int count = 1;
        while (p.next != null) {

            p = p.next;
            count++;
        }

        if (count == 1 && n == 1) return null;
        if (n == count) return head.next;

        p = head;
        for (int i=1;i<count-n;i++) {

            p = p.next;
        }
        if (p.next.next != null) {

            p.next = p.next.next;
        } else {

            p.next = null;
        }
        return head;
    }
}
```
时间复杂度：O(n)，n 为链表中节点的个数。
空间复杂度：O(1)

### 方法二：快慢指针
首先创建一个值为 0 的虚拟头节点 dummy，令 **`slow`** 指针指向 dummy 节点，**`fast`** 指针指向 head 节点。

若要删除倒数第 n 个节点，让 **`fast`** 指针移动 **n + 1** 步（**这样同时移动时 `slow` 才能指向删除节点的上一个节点，方便进行删除操作**），然后让 **`fast`** 指针和 **`slow`** 指针同时移动，直到 **`fast`** 指向链表末尾，此时删掉 **`slow`** 指针所指向的节点即可。

![微信图片_20210327131554.jpg](https://pic.leetcode-cn.com/1616824062-Wvxibh-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20210327131554.jpg)


```java
class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = dummy;

        for (int i=0;i<n;i++) {

            fast = fast.next;
        }
        while (fast != null) {

            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```
时间复杂度：O(n)，n 为链表中节点的个数
空间复杂度：O(1)

