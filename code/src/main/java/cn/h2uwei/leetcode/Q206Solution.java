package cn.h2uwei.leetcode;

/**
 * 206. 反转链表 【简单】
 * <pre>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 *  输入：head = [1,2,3,4,5]
 *  输出：[5,4,3,2,1]
 *
 * 示例 2：
 *  输入：head = [1,2]
 *  输出：[2,1]
 *
 * 示例 3：
 *  输入：head = []
 *  输出：[]
 *
 *  提示：
 *
 *  链表中节点的数目范围是 [0, 5000]
 *  -5000 <= Node.val <= 5000
 *
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/7 9:40
 */
public class Q206Solution {
    public static void main(String[] args) {
        Q206Solution solution = new Q206Solution();
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(solution.reverseList(node1));
    }

    /**
     * 思路：通过一个虚节点承接反转后的链表数据，这个会造成多使用一份内存，有想过不使用使用多余内存来处理，但是目前还没有实现出来
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        // 这里需要通过一个虚头节点来支撑链表反转
        ListNode dummy = new ListNode(-1, null);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }


    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }


}
