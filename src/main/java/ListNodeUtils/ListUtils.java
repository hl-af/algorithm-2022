package ListNodeUtils;

public class ListUtils {
    public static ListNode arrayToList(int[] array) {
        if (array.length <= 0) {
            return new ListNode();
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (int i = 0; i < array.length; i++) {
            tail.next = new ListNode(array[i]);
            tail = tail.next;
        }
        return head.next;
    }

    /**
     * 连接node1 node2 -> node1 + node2
     * @param node1
     * @param node2
     * @return
     */
    public static ListNode conjTwoList(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        ListNode head = node1;
        while (node1.next != null) {
            node1 = node1.next;
        }
        node1.next = node2;
        return head;
    }
    public static void printList(ListNode node) {
        ListNode head = node;
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode reverseList(ListNode head) {
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
}
