package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

/**
 * 1. 和数组删除不同的是，删除元素是指向，不是通过元素左移覆盖，弱化了快慢指针的概念
 * 2. 为了避免有空指针：（1）while循环和if的判空条件一致（2）指针常规移动最好使用if-else，不然容易报空指针
 * 3. 什么时候用虚拟头节点：貌似一个指针的时候使用，移动k个节点需要移动到目标前的需要
 */
public class DeleteDuplicateElement {

    /**
     * LeetCode 203：给你⼀个链表的头节点 head 和⼀个整数 val ，请你删除链表中所有满⾜
     * Node.val == val 的节点，并返回新的头节点 。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode fast = dummyHead;
        while (fast.next != null) {
            if (fast.next.val == val) {
                fast.next = fast.next.next;
            }else {
                fast = fast.next; //指针常规移动最好使用if-else，不然容易报空指针
            }
        }
        return dummyHead.next;
    }

    /**
     * LeetCode83 存在⼀个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素
     * 只出现⼀次 。返回同样按升序排列的结果链表。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (slow.val == fast.val) {
                slow.next = fast.next;
                fast = fast.next;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return head;
    }

    /**
     * LeetCode82 删除重复元素，重复元素都删除
     * 使用一个指针解决问题
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesIncludeTarget(ListNode head){
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {//（1）while循环和if的判空条件一致
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            }else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
