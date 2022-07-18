package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

public class KthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        while (k-- > 0 && fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * LeetCode 19. 删除链表的倒数第 N 个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n-- > 0 && fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }
        }
        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        ListNode next = slow.next;
        pre.next = next;
        return head;
    }

    @Test
    public void testRemoveNthFromEnd() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        ListUtils.printList(removeNthFromEnd(ListUtils.arrayToList(a), 3));
    }

}
