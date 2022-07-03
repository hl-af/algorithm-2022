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
     * 注意判断k大于链表长度的情况
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k < 0) {
            return new ListNode();
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
            if (fast == null) { //关键点：k的长度可能大于链表长度，所以一定要做判断
                break;
            }
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

    /**
     * Leetcode61 旋转列表
     * 可能存在问题，在leetcode待验证
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || k < 0) {
            return new ListNode();
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }

    @Test
    public void testRotateRight() {
        int[] a = {1,2,3,4,5,6,7,8};
        ListUtils.printList(rotateRight(ListUtils.arrayToList(a), 2));
    }

    /**
     * LeetCode141和142 判断列表是否有环-快慢指针法
     * 快慢指针方法，相遇了三次
     * 自己的实现，比较复杂，答案的方法会更好
     * @param head
     * @return
     */
    public ListNode hasCycle(ListNode head) {
        if (head == null) {
            return new ListNode();
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (slow != fast && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next != null) { //有环
            int dist = 0;
            fast = fast.next;
            while (fast != slow) {
                fast = fast.next;
                dist++;
            }
            slow = head;
            fast = head;
            for (int i = 0; i < dist + 1; i++) { //需要先走一格，不然slow和fast永远遇不到
                fast = fast.next;
            }
            while (fast != slow) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }else { //无环
            return null;
        }
    }

    @Test
    public void testHasCycle() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode head = ListUtils.arrayToList(a);
        ListNode p = head;
        ListNode secondNode = null;
        ListNode lastNode = null;
        for (int i = 0; i < a.length; i++) {
            if (i == 2) {
                secondNode = p;
            }
            if (i == a.length - 1) {
                lastNode = p;
            }
            p = p.next;
        }
        lastNode.next = secondNode;
        System.out.println(hasCycle(head).val);
    }

}
