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


    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode p = dummyHead;
        while ( p.next != null) { // 这个情况如果尾节点是val，需要改为 p != null && p.next != null ，否则就会报NPE，因为 45 行 p = p.next写在了最后
            if (p.next.val == val) {
                ListNode next = p.next.next;
                p.next = next;
            }
            p = p.next;
        }
        return dummyHead.next;
    }

    @Test
    public void testRemoveElements() {
        int[] a = {1,2,6,3,4,5,6};
        ListNode head = removeElements2(ListUtils.arrayToList(a), 6);
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

    /**
     * LeetCode83 存在⼀个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素
     * 只出现⼀次 。返回同样按升序排列的结果链表。
     * 这种方法有点绕，答案方法更加简洁
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == pre.val) {
                pre.next = cur.next;
                cur = cur.next;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return head;
    }

    /**
     * LeetCode83 经过leetcode验证版
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (slow.val == fast.val) {
                slow.next = fast.next;
                fast = fast.next;
            }else {  //用else避免fast移动后可能是null，产生空指针
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }

    @Test
    public void testDeleteDuplicates() {
        int[] a = {1, 1, 2, 3, 3, 4, 5, 6, 7};
        ListNode head = deleteDuplicates(ListUtils.arrayToList(a));
        ListUtils.printList(head);
    }

    /**
     * LeetCode82 删除重复元素，重复元素都删除
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesIncludeTarget(ListNode head){
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead; //pre是永远跟随cur的前序节点
        dummyHead.next = head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
            }else {
                cur = cur.next;
                pre = pre.next;
            }
        }
        return head;
    }

    /**
     * 这种方法还是会有重复元素，无法通过所有的测试用例
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesIncludeTarget2(ListNode head){
        ListNode dummpHead = new ListNode(-1);
        dummpHead.next = head;
        ListNode slow = dummpHead;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            if (fast.val == fast.next.val) {
                slow.next = fast.next.next;
                fast = slow.next;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummpHead.next;
    }

    /**
     * 连续删除:失败case：[1,2,3,3,4,4,5]
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesIncludeTarget3(ListNode head){
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead.next;
        while (fast != null && fast.next != null) {
            if (fast.val == fast.next.val) {
                while (fast != null && fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
            }else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return dummyHead.next;
    }

    @Test
    public void testDeleteDuplicatesIncludeTarget() {
//        int[] a = {1, 2, 3, 4, 5, 5, 5, 5, 5};
        int[] a = {1, 2, 3, 3, 4, 4, 5};
        ListNode head = deleteDuplicatesIncludeTarget3(ListUtils.arrayToList(a));
        ListUtils.printList(head);
    }

}
