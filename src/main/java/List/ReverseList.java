package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.lang.reflect.GenericDeclaration;

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
     * 方法1：部分区间先反转，然后重新连接 我自己的实现，比较累赘
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

    /**
     *LeetCode92 ：给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right。请你反转从位
     置 left 到位置 right 的链表节点，返回反转后的列表
     穿针引线法标准实现
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenAnswer(ListNode head, int left, int right) {
        ListNode dymmyHead = new ListNode();
        dymmyHead.next = head;
        ListNode pre = dymmyHead;
        ListNode end = dymmyHead;
        ListNode p = head;
        int index = 0;
        while (p != null) {
            if (index == left - 1) {
                pre = p;
            }
            if (index == right) {
                end = p;
            }
            index++;
            p = p.next;
        }
        ListNode next = end.next;
        end.next = null;
        ListNode start = pre.next;
        pre.next = ListUtils.reverseList(start);
        start.next = next;
        return dymmyHead.next;
    }

    @Test
    public void testReverseBetween() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
//        ListNode head = reverseBetween(ListUtils.arrayToList(a), 2, 5);
        ListNode head = reverseBetweenAnswer(ListUtils.arrayToList(a), 2, 5);
        ListUtils.printList(head);
    }


    /**
     * LeetCode25.给你⼀个链表，每 k 个节点⼀组进⾏翻转
     * 使用穿针引线法
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupAnswer(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        // 先记录前序节点和最后一个待处理列表的尾节点
        ListNode pre = dummyHead; //前驱和后继节点定义在外面
        ListNode end = dummyHead;  // 注意end是从 前序节点走，到最后一个要处理的节点结束
        while (end != null) {
            ListNode first = pre.next; //每一个区间使用
            for (int i = 0; i < k && end != null; i++) { //for 中间也能加其他的判断
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode next = end.next;  // 记录后一个end节点
            end.next = null;  //先断开，便于使用通用的列表反转手段
            pre.next = ListUtils.reverseList(first);
            first.next = next;
            pre = first;
            end = first;
        }
        return dummyHead.next;
    }



    @Test
    public void testReverseKGroup() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
//        ListNode head = reverseKGroup(ListUtils.arrayToList(a), 3);
        ListNode head = reverseKGroupAnswer(ListUtils.arrayToList(a), 3);
        ListUtils.printList(head);
    }


    /**
     * 两两交换节点（套用K个节点反转）
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode end = dummyHead;
        while (end != null) {
            ListNode start = pre.next;
            for (int i = 0; i < 2 && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode next = end.next;
            end.next = null;
            pre.next = ListUtils.reverseList(start);
            start.next = next;
            pre = start;
            end = pre; // 这个条件易忽略
        }
        return dummyHead.next;
    }

    @Test
    public void testSwapPairs() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListNode head = swapPairs(ListUtils.arrayToList(a));
        ListUtils.printList(head);
    }


}
