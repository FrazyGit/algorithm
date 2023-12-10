package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gc
 * @date 2023-11-13 9:52
 */
public class 宽度优先遍历 {

    public static void bfs(Node start) {
        //需要指定一个出发点
        if (start == null) {
            return;
        }

        Queue<Node> queue = new LinkedList();
        HashSet<Node> nodeHashSet = new HashSet<>();

        queue.add(start);
        nodeHashSet.add(start);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            //使用该算法时的具体要求业务
            System.out.println(poll);
            for (Node node : poll.next) {
                if (!nodeHashSet.contains(node)) {
                    nodeHashSet.add(node);
                    queue.add(node);
                }
            }


        }


    }


}
