package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

public class ReverseList {


    /**
     * LeetCode206 给你单链表的头节点 head，请你反转链表(模版，背会)
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    @Test
    public void testReverseList() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode head = reverseList(ListUtils.arrayToList(a));
        ListUtils.printList(head);
    }


}
