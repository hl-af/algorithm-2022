package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

public class MergeList {
    /**
     * LeetCode21 将两个升序链表合并为⼀个新的升序链表返回
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode head = p;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
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
        int[] b = {1, 10, 11};
        ListNode l1 = ListUtils.arrayToList(a);
        ListNode l2 = ListUtils.arrayToList(b);
        ListNode head = mergeTwoLists(l1, l2);
        ListUtils.printList(head);
    }

    /**
     * LeetCode1669：给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。请你将 list1 中下标从a到b的
     * 节点删除，并将list2 接在被删除节点的位置。
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode low = null;
        ListNode high = null;
        ListNode head = list1;
        ListNode p = list1;
        int i = 0;
        while (p != null) {
            if (i == (a-1)) {
                low = p;
            }
            if (i == b) {
                high = p;
            }
            i++;
            p = p.next;
        }

        low.next = list2;
        ListNode q = list2;
        while (q.next != null) {
            q = q.next;
        }
        q.next = high.next;
        return head;
    }

    @Test
    public void testMergeInBetween() {

        int[] a = {1, 3, 5, 7, 9};
        int[] b = {1001, 1002, 1003};
        ListNode l1 = ListUtils.arrayToList(a);
        ListNode l2 = ListUtils.arrayToList(b);
        ListNode head = mergeInBetween(l1, 1, 2, l2);
        ListUtils.printList(head);
    }
}
