package class17;

import java.util.Stack;

/**
 * @author gc
 * @date 2023-12-02 14:31
 */
public class 使用递归逆序一个栈 {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        //移除栈底并返回
        Integer bottom = f(stack);
        reverse(stack);
        stack.push(bottom);

    }

    private static Integer f(Stack<Integer> stack) {
        Integer pop = stack.pop();//不会出现空栈情况
        if (stack.isEmpty()) {
            return pop;
        }
        //dfs
        Integer bottom = f(stack);
        stack.push(pop);
        return bottom;
    }

    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);

        System.out.println(integers.toString());
        reverse(integers);

        System.out.println(integers.toString());

    }

}
