package cn.h2uwei.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表 【简单】
 * <pre>
 *  给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *
 *
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/10 22:09
 */
public class Q141Solution {
    public static void main(String[] args) {
        Q141Solution solution = new Q141Solution();
        // 3,2,0,-4
        ListNode node = new ListNode(3, new ListNode(2, new ListNode(0, new ListNode(-4, new ListNode(2)))));
        System.out.println(solution.hasCycle(node));
    }

    /**
     * 通过一个 set 标记元素是否重复出现来判断链表是否存在环，（时间复杂度 O(n)，空间复杂度 O(n)）
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> uniq = new HashSet<>();
        ListNode curr = head;
        while (curr.next != null) {
            if (!uniq.add(curr)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * 通过快慢指针的方式进行，当两个指针相遇的时候则出现了环
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null && slow != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

}
