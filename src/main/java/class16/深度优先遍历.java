package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author gc
 * @date 2023-11-13 10:03
 */
public class 深度优先遍历 {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }

        Stack<Node> stack = new Stack();
        HashSet<Node> nodes = new HashSet<>();

        stack.add(start);
//        进栈就打印
        System.out.println(stack);
        nodes.add(start);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node node : pop.next) {
                if (!nodes.contains(node)) {
//                    维持完整路径
                    stack.add(pop);
                    stack.add(node);
                    System.out.println(node);
                    nodes.add(node);
//                    轮到刚刚加入的点的直接邻居了
                    break;
                }
            }

        }

    }

}
