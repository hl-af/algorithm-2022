package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

/**
 * LeetCode21 将两个升序链表合并为⼀个新的升序链表返回
 */
public class MergeList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode head = p;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                p.next = l2;
                l2 = l2.next;
            }else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        while (l1 != null || l2 != null) {
            if (l1 == null && l2 != null) {
                p.next = l2;
                l2 = l2.next;
            }else if(l1 != null && l2 == null){
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        return head.next;
    }

    @Test
    public void testMergeTwoLists() {
        int[] a = {1, 3, 5, 7, 9};
//        int[] b = {2, 4, 6, 8, 10};
        int[] b = {10, 11};
        ListNode l1 = ListUtils.arrayToList(a);
        ListNode l2 = ListUtils.arrayToList(b);
        ListNode head = mergeTwoLists(l1, l2);
        ListUtils.printList(head);
    }
}
