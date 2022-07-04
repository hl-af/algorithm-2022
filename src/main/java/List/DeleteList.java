package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

public class DeleteList {

    /**
     * LeetCode 203：给你⼀个链表的头节点 head 和⼀个整数 val ，请你删除链表中所有满⾜
     * Node.val == val 的节点，并返回新的头节点 。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while (p.next != null) {
            if (p.next.val == val) {
                ListNode next = p.next.next;
                p.next = next;
            } else {
                p = p.next;
            }
        }
        return dummyHead.next;
    }

    @Test
    public void testRemoveElements() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        ListNode head = removeElements(ListUtils.arrayToList(a), 7);
        ListUtils.printList(head);
    }

    /**
     * LeetCode19 给你⼀个链表，删除链表的倒数第n个结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) { // 这里和找倒数K节点不同，因为要删除目标节点先要找到前序节点
            fast = fast.next;
            slow = slow.next;
        }
        ListNode next = slow.next;
        slow.next = next.next;
        next.next = null;
        return head;
    }

    @Test
    public void testRemoveNthFromEnd() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        ListNode head = removeNthFromEnd(ListUtils.arrayToList(a), 3);
        ListUtils.printList(head);
    }
}
