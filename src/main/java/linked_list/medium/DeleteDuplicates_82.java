package linked_list.medium;

import common.ListNode;
import org.junit.Test;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 *
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 提示：
 *
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteDuplicates_82 {

    @Test
    public void test() {
        ListNode node1 = ListNode.createList(new int[]{1,2,3,3,4,4,5});
        // ListNode node1 = ListNode.createList(new int[]{1,2,2});
        // ListNode node1 = ListNode.createList(new int[]{1,1,1,2,3});
        deleteDuplicates(node1);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                int headVal = head.val;
                while (head != null && head.val == headVal) {
                    head = head.next;
                }
            } else {
                break;
            }
        }
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (pre != null && cur != null) {
            pre.next = null;
            if (cur.next == null) {
                pre.next = cur;
            } else if (cur.val != cur.next.val) {
                pre.next = cur;
                pre = cur;
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
            }
            cur = cur.next;
        }
        return head;
    }
}
