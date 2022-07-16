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
     * 可能存在问题，在leetcode待验证 -> 如果 k大于列表长度情况没有考虑到
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightMe(ListNode head, int k) {

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

    /**
     * Leetcode61 旋转列表
     * 使用反转法计算
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        k = k % length;
        head = reverseList(head);
        ListNode node1 = head;
        ListNode node2 = null;
        k = k - 1;
        while (k-- > 0) {
            node1 = node1.next;
        }
        node2 = node1.next;
        node1.next = null;
        temp = head;
        head = reverseList(head);
        node2 = reverseList(node2);
        temp.next = node2;
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    @Test
    public void testRotateRight() {
        int[] a = {1,2,3,4,5,6,7,8};
//        ListUtils.printList(rotateRightMe(ListUtils.arrayToList(a), 2));
        ListUtils.printList(rotateRight(ListUtils.arrayToList(a), 2));
    }

    /**
     * LeetCode141和142 判断列表是否有环-快慢指针法
     * 快慢指针方法，相遇了三次,自己实现的
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // 快慢指针判断是否有环
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            if(fast == null || fast.next == null){
                return null;
            }
            if(slow == fast){
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 定位环的长度
        fast = slow.next; // 不设置为slow的下一个会导致进入不了下面的循环
        int length = 1; //  已经拉开了差距，所以length从1开始累加
        while(fast != slow){
            length++;
            fast = fast.next;
        }
        // 找到环的入口
        slow = head;
        fast = head;
        while(length-->0){
            fast = fast.next;
        }
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
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
//        System.out.println(hasCycle(head).val);
        System.out.println(detectCycle(head).val);
    }

}
