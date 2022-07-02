package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

public class TwoPointer {

    /**
     * LeetCode876 给定⼀个头结点为 head 的⾮空单链表，返回链表的中间结点。如果有两个中间结点，则返回第⼆个中间结点。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void testMiddleNode() {
        int[] a = {1,2,3,4,5,6};
        ListNode head = middleNode(ListUtils.arrayToList(a));
        ListUtils.printList(head);
    }

    /**
     * 输⼊⼀个链表，输出该链表中倒数第k个节点。本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    @Test
    public void testGetKthFromEnd() {
        int[] a = {1,2,3,4,5,6,7,8};
        System.out.println(getKthFromEnd(ListUtils.arrayToList(a), 10).val);
    }
}
