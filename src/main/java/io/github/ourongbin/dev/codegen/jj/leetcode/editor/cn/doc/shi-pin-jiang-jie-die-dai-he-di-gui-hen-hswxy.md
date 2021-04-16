**视频如果看不了的话，请点击**：[反转链表视频讲解](https://ke.qq.com/webcourse/index.html#cid=3065907&term_id=103186001&taid=10593313500350515&type=1024&vid=5285890809407997971)

# 1. 迭代解法

![206_1_迭代解法.mp4](ec194742-c125-429f-9d36-173f5fc7b8fa)

代码如下：
```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

# 2. 递归解法
## 2.1 为什么可以使用递归
![...06_2_为什么可以使用递归实现.mp4](58a00b4f-068d-4efa-985c-dcec2cdd0b23)


## 2.2 递归解法思路
![206_3_递归实现.mp4](83295b82-8ab6-4c45-bf37-7e562a899fb1)

代码如下：
```java
public ListNode reverseList(ListNode head) {
    // 1. 递归终止条件
    if (head == null || head.next == null) {
        return head;
    }
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
```

**我想跟你说的是， 你不会做算法题， 并不是因为你不够聪明， 而是因为你不知道如何高效的刷题**

**如何高效刷题可以参考**：[如何高效刷算法题](https://ke.qq.com/webcourse/index.html#cid=3171403&term_id=103298623&taid=10797719584007243&vid=5285890812571915051)

**如果你觉得刷算法题很痛苦、 需要花费大量的时间的话， 并不是因为你笨， 原因就是你的【数据结构与算法】知识不成体系**

**如果你的【数据结构与算法】知识不成体系的话， 那么**：
1. **即使一道很简单的算法题， 你都要想很长的时间， 还不一定会**
2. **然后， 你就会看题解， 看完题解， 觉得自己会了， 但是过一段时间， 你就又忘了， 单纯的记住答案是不行的**
3. **即使这道题目会了， 那么题目稍微一变型， 你又不会了**

**所以说， 你不会做算法题的原因， 就是因为你没有构建【数据结构与算法】知识体系**

**构建【数据结构与算法】知识体系：**[快速构建【数据结构与算法】知识体系](https://ke.qq.com/course/package/31104?tuin=1bf872a6)