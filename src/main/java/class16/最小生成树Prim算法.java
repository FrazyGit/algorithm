package class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author gc
 * @date 2023-11-20 14:05
 */
public class 最小生成树Prim算法 {

    public static Set<Edge> prim(Graph graph) {

        //边集
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>();

        //点集
        HashSet<Node> nodes = new HashSet<>();

        //结构集
        HashSet<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) {
            if (!nodes.contains(node)) {
                nodes.add(node);
                //点解锁边
                for (Edge edge : node.edges) {
                    edgePriorityQueue.add(edge);
                }

            }

            while (!edgePriorityQueue.isEmpty()) {
                Edge poll = edgePriorityQueue.poll();//弹出最小边
                //边解锁点
                Node to = poll.to;
//            Node from = poll.from; 出发点已经加入

                if (!nodes.contains(to)) {
                    result.add(poll);
                    nodes.add(to);
                    //点解锁边
                    for (Edge edge : to.edges) {
                        edgePriorityQueue.add(edge);
                    }
                }
            }
            break;//如果要防森林就不break,如果你整个图就是一个就break


        }
        return result;

    }


    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
