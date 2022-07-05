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

    /**
     * LeetCode92 ：给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right。请你反转从位
     * 置 left 到位置 right 的链表节点，返回反转后的列表
     * 方法1：部分区间先反转，然后重新连接
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = head;
        int index = 0;
        ListNode pre = null;
        ListNode preLeft = null;
        ListNode rightNext = null;
        ListNode leftNode = null;

        //确定左右节点
        while (p != null) {
            if (index == left) {
                leftNode = p;
                preLeft = pre;
            }
            if (index == right) {
                rightNext = p.next;
            }
            pre = p;
            p = p.next;
            index++;
        }
        //反转列表
        ListNode cur = leftNode;
        pre = null;
        while (cur != rightNext) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 重新连接左右两个节点
        preLeft.next = pre;
        leftNode.next = rightNext;
        return head;
    }


    @Test
    public void testReverseBetween() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode head = reverseBetween(ListUtils.arrayToList(a), 2, 5);
        ListUtils.printList(head);
    }


}
