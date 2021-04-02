package linked_list.easy;

import common.ListNode;

import java.util.List;

/**
 * 203. 移除链表元素
 *
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 *
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 * 提示：
 *
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElements_203 {

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;
        }
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == val) {
                pre.next = next.next;
            } else {
                pre = pre.next;
            }
            next = next.next;
        }
        return head;
    }
}
