package Search;

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
}
