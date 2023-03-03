package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wlkq
 * @date 2023-03-02 19:10
 */
public class 队列实现栈 {

    public static class myStack {

        private Queue<Integer> dataQueue;

        private Queue<Integer> popQueue;

        public myStack() {
            dataQueue = new LinkedList<>();
            popQueue = new LinkedList<>();
        }

        public void push(int num) {
            dataQueue.add(num);
        }

        public Integer pop() {
            while (dataQueue.size() > 1) {
                popQueue.add(dataQueue.poll());
            }

            Integer poll = dataQueue.poll();
            Queue<Integer> tamp = dataQueue;
            dataQueue = popQueue;
            popQueue = tamp;

            return poll;


        }

        public Integer peek() {
            while (dataQueue.size() > 1) {
                popQueue.add(dataQueue.poll());
            }



            Integer poll = dataQueue.poll();

            //换之前保持popQueue为空
            popQueue.add(poll);

            Queue<Integer> tamp = dataQueue;
            dataQueue = popQueue;
            popQueue = tamp;

            return poll;


        }

        public boolean isEmpty() {
            return dataQueue.isEmpty();
        }


    }

    public static void main(String[] args) {
        System.out.println("test begin");
        myStack myStack = new myStack();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops 1");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops 2");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.pop().equals(test.pop())) {
                        System.out.println("Oops 3");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops 4");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
