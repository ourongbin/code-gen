package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1687 👎 0


class ReverseLinkedList {
    public static void main(String[] args) {
        ReverseLinkedList question = new ReverseLinkedList();
        Solution solution = question.new Solution();
        System.out.println(args);
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
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
class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode newHead = null;
        while (head != null) {
            ListNode r = head;
            head = head.next;
            r.next = newHead;
            newHead = r;
        }

        return newHead;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}