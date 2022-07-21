package Search;

import ListNodeUtils.ListNode;
import ListNodeUtils.ListUtils;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap {

    /**
     * LeetCode215 寻找第k大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || k > nums.length) {
            return -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);//小顶堆
        //PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> b - a);//大顶堆
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = k - 1; i < nums.length; i++) {
            int peak = minHeap.peek();
            if (nums[i] > peak) {
                minHeap.poll(); //如果没有这句的话，会导致这个堆会一直扩大，无法维护K个
                minHeap.add(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 6, 7};
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(2,(a,b)->a-b);
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(nums[i]);
        }
    }

    @Test
    public void testFindKthLargest() {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int result = findKthLargest(nums, 3);
        System.out.println(result);
    }

    /**
     * Leetcode23 合并K个列表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return new ListNode();
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparing(node -> node.val)); //这个比较器是什么意思
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                minHeap.offer(node);
                node = node.next;
            }
        }
        ListNode result = new ListNode();
        ListNode tailf = result;
        while (minHeap.size() > 0) {
            tailf.next = minHeap.poll();
            tailf = tailf.next;
        }
        return result.next;
    }

    @Test
    public void testMergeKLists() {
        int[] a1 = {1, 4, 5};
        int[] a2 = {1, 3, 4};
        int[] a3 = {2, 6};
        ListNode[] lists = {ListUtils.arrayToList(a1), ListUtils.arrayToList(a2), ListUtils.arrayToList(a3)};
        ListNode result = mergeKLists(lists);
        ListUtils.printList(result);

    }
}
