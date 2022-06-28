package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CommonNode {

    public ListNode findFirstCommonNodeByMap(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != null) {
            stack1.push(p1);
            p1 = p1.next;
        }

        while (p2 != null) {
            stack2.push(p2);
            p2 = p2.next;
        }
        ListNode commNode = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ListNode temp1 = stack1.pop();
            ListNode temp2 = stack2.pop();
            if (temp1.val != temp2.val) {
                return commNode;
            }else {
                commNode = temp1;
            }
        }
        return new ListNode(-1);
    }

    @Test
    public void testFindFirstCommonNodeByMap() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {11, 12, 13, 14};
        int[] a3 = {5, 6, 7};
        ListNode p1 = ListUtils.arrayToList(a1);
        ListNode p2 = ListUtils.arrayToList(a2);
        ListNode p3 = ListUtils.arrayToList(a3);
        p1 = ListUtils.conjTwoList(p1, p3);
        p2 = ListUtils.conjTwoList(p2, p3);
        ListNode node = findFirstCommonNodeByMap(p1, p2);
        System.out.println(node.val);
    }
}
