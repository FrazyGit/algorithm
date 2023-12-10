package class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author gc
 * @date 2023-11-12 16:53
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    //转化接口示例
    // matrix所有的边
    // N*3的矩阵
    // [weight，from节点 上面的值，to节点上面的值]

    //这个方法它就是一个接口类。面试中遇到的所有奇奇怪怪的玩意儿
    //定义你自己的接口类就直接转化成了你熟悉的结构
    // 如果一个图里,没有from, to 就新建出来
    //有的算法跟入度,出度没关系, 或者没有权重, 这部分代码就可以阉割掉
    public static Graph createGraph(Integer[][] matrix) {

        Graph graph = new Graph();


        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];


            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge = new Edge(weight, fromNode, toNode);

            fromNode.next.add(toNode);
            fromNode.out++;
            toNode.in++;

            graph.edges.add(edge);

        }

        return graph;

    }


}
