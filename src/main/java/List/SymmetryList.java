package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.Stack;

public class SymmetryList {

    /**
     * 剑指offer27 回文列表
     * 使用栈解决问题：先遍历一遍得到总长度，然后压栈到中间
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        int midLength = (int) (length / 2);
        p = head;
        for (int i = 0; i < midLength; i++) {
            stack.push(p);
            p = p.next;
        }
        while (p != null) {
            ListNode node = stack.pop();
            if (node.val != p.val) {
                return false;
            }
            p = p.next;
        }
        return true;
    }

    /**
     * 剑指offer27 回文列表
     * 快慢指针的一遍遍历法 + 反转链表
     * @param head
     * @return
     */
    public boolean isPalindromeDoublePointer(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode leftHalfHead = head;
        ListNode rightHalfHead = slow.next;
        rightHalfHead = reverseList(rightHalfHead);

        while (leftHalfHead != null && rightHalfHead != null) {
            if (leftHalfHead.val != rightHalfHead.val) {
                return false;
            }
            leftHalfHead = leftHalfHead.next;
            rightHalfHead = rightHalfHead.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
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
    public void testIsPalindrome() {
        int[] array = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        ListNode head = ListUtils.arrayToList(array);
//        System.out.println(isPalindrome(head));
        System.out.println(isPalindromeDoublePointer(head));
    }
}
