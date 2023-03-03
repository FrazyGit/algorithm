package class03;

import java.util.Stack;

/**
 *
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 *
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 *
 * 2）设计的栈类型可以使用现成的栈结构。
 * @author wlkq
 * @date 2023-03-02 18:17
 */
public class 最小栈 {


    public static class myStack {

        public Stack<Integer> stack;
        public Stack<Integer> minStack;

        public myStack() {
            stack = new Stack();
            minStack = new Stack();

        }

        public void push(int num) {


            if (minStack.isEmpty()||minStack.peek() > num) {
                minStack.push(num);

            } else {
                minStack.push(minStack.peek());
            }

            stack.push(num);//无论怎样数据栈都加数据


        }

        public Integer pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }

            minStack.pop();

            return stack.pop();


        }

        public Integer getmin() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }

            return minStack.peek();
        }


    }

    public static void main(String[] args) {
        myStack stack1 = new myStack();
        stack1.push(3);
        System.out.println(stack1.getmin());
        stack1.push(4);
        System.out.println(stack1.getmin());
        stack1.push(1);
        System.out.println(stack1.getmin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getmin());

    }

}
