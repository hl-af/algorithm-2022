package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode225 请你仅使⽤两个队列实现⼀个后⼊先出（LIFO）的栈，并⽀持普通栈的全部四种操作（push、top、pop 和
 * empty）。
 */
public class Queue2Stack {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>(); //插入辅助类，只插入第一个元素，然后将queue1的元素全部放到queue2里面

    public void push(Integer node) {
        queue2.offer(node);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    public Integer pop() {
        return queue1.poll();
    }

    public Integer peek() {
        return queue1.peek();
    }

    public Boolean empty() {
        return queue1.isEmpty();
    }


    public static void main(String[] args) {
        Queue2Stack stack = new Queue2Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        stack.pop();
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
