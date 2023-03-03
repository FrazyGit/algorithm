package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wlkq
 * @date 2023-03-02 9:35
 */
public class 数组实现栈和队列 {

        public static class MyStack {

            private int[] arr;
            private int index = 0;

            public MyStack(Integer limit) {


                arr = new int[limit];

            }

            public void push(int value) {
                if (index == arr.length) {
                    throw new RuntimeException("栈满了");
                }
                arr[index++] = value;
            }

            public int pop() {
                if (arr.length < 1) {
                    throw new RuntimeException("栈为空");
                }

                return arr[--index];


            }

            public boolean isEmpty() {
                return arr.length == 0;
            }

        }

    public static class MyQueue<T> {

        private int[] arr;
        private int begin = 0;
        private int end = 0;
        private int size = 0;

        public MyQueue(Integer limit) {
            arr = new int[limit];
        }

        public void push(int value) {
            if (size == arr.length) {
                throw new RuntimeException("队列满了");
            }

            arr[end] = value;
            end = change(end);
            size++;


        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列为空了");
            }

            int res = arr[begin];
            begin = change(begin);
            size--;
            return res;


        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int change(int index) {
            if (index == arr.length - 1) {
                return 0;
            }
            return index + 1;
        }


    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack(oneTestDataNum);
            MyQueue myQueue = new MyQueue(oneTestDataNum);
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
