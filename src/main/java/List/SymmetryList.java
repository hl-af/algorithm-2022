package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.Stack;

public class SymmetryList {

    /**
     * 剑指offer27 回文列表
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

    @Test
    public void testIsPalindrome() {
        int[] array = {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        ListNode head = ListUtils.arrayToList(array);
        System.out.println(isPalindrome(head));
    }
}
