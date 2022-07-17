package List;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CommonNode {
    /**
     * 剑指offer 寻找两个字符串的公共节点
     * 使用栈的方法来解决问题
     * @param pHead1
     * @param pHead2
     * @return
     */
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
//        ListNode node = findFirstCommonNodeByMap(p1, p2);
        ListNode node = findFirstCommonNode(p1, p2);
        System.out.println(node.val);
    }

    /**
     * 剑指offer 52 使用差和指针解决这个问题
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        int length1 = 0;
        int length2 = 0;
        while (p1 != null || p2 != null) {
            if (p1 == null && p2 != null) {
                length2++;
                p2 = p2.next;
            } else if (p1 != null && p2 == null) {
                length1++;
                p1 = p1.next;
            }
            p2 = p2.next;
            p1 = p1.next;
        }
        p1 = pHead1;
        p2 = pHead2;
        if (length1 > length2) {
            int tempLength = length1;
            while (tempLength-- > 0) {
                p1 = p1.next;
            }
        }else {
            int tempLength = length2;
            while (tempLength-- > 0) {
                p2 = p2.next;
            }
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
