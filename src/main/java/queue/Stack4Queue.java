package queue;

import java.util.Stack;

/**
 * LeetCode232 使⽤两个栈实现先⼊先出队列。队列应当⽀持⼀般队列⽀持的所有操作（push、pop、peek、empty）：
 */
public class Stack4Queue {

    Stack<Integer> stackPop = new Stack();
    Stack<Integer> stackPush = new Stack();

    public void push(Integer node){
        stackPush.push(node);
    }

    public Integer pop() {
        in2out();
        return stackPop.pop();
    }

    public Integer peek() {
        in2out();//容易忽略，因为可能存在出栈存储没有值，而进栈存储有值的情况
        return stackPop.peek();
    }

    public Boolean empty() {
        return stackPop.isEmpty() && stackPush.isEmpty();
    }

    public void in2out() {
        if (stackPop.isEmpty()) { //出栈为空需要提价
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public static void main(String[] args) {
        Stack4Queue queue = new Stack4Queue();
        queue.push(1);
//        System.out.println(queue.peek());
        queue.push(2);
        queue.push(3);
        queue.pop();
        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }

}
