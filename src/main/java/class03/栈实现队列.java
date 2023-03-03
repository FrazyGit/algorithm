package class03;

import java.util.Stack;

/**
 * @author wlkq
 * @date 2023-03-02 19:09
 */
public class 栈实现队列 {

    public static class MyQueue {

        public Stack<Integer> pushStack;
        public Stack<Integer> popStack;


        public MyQueue() {
            pushStack = new Stack();
            popStack = new Stack();
        }

        private void change() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

        }

        public void push(Integer num) {
            pushStack.push(num);
            change();

        }

        public Integer pop() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            change();
            return popStack.pop();
        }

        public Integer peek() {
            if (popStack.isEmpty() && pushStack.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            change();
            return popStack.peek();
        }


    }

    public static void main(String[] args) {
        MyQueue test = new MyQueue();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.peek());
        System.out.println(test.pop());
    }


}
