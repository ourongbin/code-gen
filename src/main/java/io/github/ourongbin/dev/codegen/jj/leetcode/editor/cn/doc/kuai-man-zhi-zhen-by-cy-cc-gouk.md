### 解题思路
    第一眼看到删除链表的倒数第几个元素，脑中就浮现一个思路--恰恰也是昨天刚刚了解到的快慢指针。
    快慢指针，意在定义两个指针，开始都指向头节点
    要删掉倒数第N个节点，实际上将倒数第N+1个结点的next域指向N个节点的next域
    故 我们要找到倒数第n+1个节点
    这里先模拟下 删除倒数第二个节点
    快指针已经到了最后一个，而慢指针才到倒数第三个，中间相差2个节点（包括慢指针指向的那个元素）
    故我们要先让快指针 先走两个节点
    输入：head = [1,2,3,4,5], n = 2
    输出：[1,2,3,5]
    for(int i=0;i<2;i++){
        fast=fast.next;
    }
    这时 fast已经指向了 2这个元素 而slow还在头节点 1-(-1)=2
    当fast指向队尾，slow指向 3这个元素
    故删掉3后面的这个元素 即 slow.next=slow.next.next;


### 代码

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
*
* 倒数第N个节点可以由快慢指针来
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0,head);
        ListNode fast=dummyHead;
        ListNode slow=dummyHead;
        for(int i=0;i<n;i++){
            fast=fast.next;
        }
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return dummyHead.next;
    }
}
```