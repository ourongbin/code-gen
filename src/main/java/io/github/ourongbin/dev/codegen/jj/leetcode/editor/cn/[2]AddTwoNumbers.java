package io.github.ourongbin.dev.codegen.jj.leetcode.editor.cn;


//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6038 👎 0


class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers question = new AddTwoNumbers();
        ListNode l1 = ListNode.make(2, 4, 3);
        ListNode l2 = ListNode.make(5, 6, 4);
        ListNode.print(l1);
        ListNode.print(l2);
        Solution solution = question.new Solution();
        System.out.println(solution.addTwoNumbers(l1, l2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode preHead = new ListNode(-1);
            ListNode tail = preHead;
            int carry = 0;
            while (l1 != null || l2 != null || carry > 0) {
                int a = l1 != null ? l1.val : 0;
                int b = l2 != null ? l2.val : 0;
                int sum = a + b + carry;
                carry = sum / 10;
                sum = sum % 10;
                tail.next = new ListNode(sum);
                tail = tail.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            return preHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}