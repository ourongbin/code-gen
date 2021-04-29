记录一下，主要掌握思想，快指针是为null时结束还是为链表的最后一个节点结束都无所谓，主要保证结束时慢指针指向的是要删除节点的前驱节点，然后更换指向，另外对于头节点的删除，要做特判即可
```
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode pre=head;//慢指针
        ListNode cur=head;//快指针
        while(n>0)//快指针先走n步
        {
            cur=cur.next;
            n--;
        }
        if(cur==null)//特判：如果删除的是头节点，则n为链表长度，此时cur为空
        {
            return head.next;
        }
        while(cur.next!=null)
        {
            cur=cur.next;
            pre=pre.next;
        }
        pre.next=pre.next.next;
        return head;
    }
```
