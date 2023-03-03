package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wlkq
 * @date 2023-02-28 15:34
 */
public class 链表实现栈和队列 {

    public static class MyQueue<T> {

        private DoubleNodeUtils<T> doubleNodeUtils;

        public MyQueue() {
            this.doubleNodeUtils = new DoubleNodeUtils<>();
        }

        public void push(T value) {
            doubleNodeUtils.addFromBottom(value);

        }

        public T poll() {
            return doubleNodeUtils.popFromHead();
        }

        public boolean isEmpty() {
            return doubleNodeUtils.isEmpty();
        }


    }

    public static class MyStack<T> {
        private DoubleNodeUtils<T> doubleNodeUtils;

        public MyStack() {
            doubleNodeUtils= new DoubleNodeUtils<T>();
        }

        public void push(T value) {
            doubleNodeUtils.addFromBottom(value);
        }

        public T pop() {
            return doubleNodeUtils.popFromBottom();
        }

        public boolean isEmpty() {
            return doubleNodeUtils.isEmpty();
        }

    }


    public static class DoubleNodeUtils<T> {
        public DoubleNode<T> head;
        public DoubleNode<T> tail;


        public void addFromHead(T value) {
            DoubleNode cur = new DoubleNode(value);

            if (head == null) {
                head = cur;
                tail = cur;

            } else {
                head.last = cur;
                cur.next = head;
                head = cur;
            }


        }

        public void addFromBottom(T value) {
            DoubleNode cur = new DoubleNode(value);

            if (head == null) {
                head = cur;
                tail = cur;

            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;

            }


        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }

            DoubleNode<T> cur = head;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;

            }

            return cur.value;

        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }

            DoubleNode<T> cur = tail;

            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                cur.last = null;
                tail.next = null;

            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }


    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }



}
