package class16;

import java.util.*;

/**
 * @author gc
 * @date 2023-11-15 14:54
 */
public class 拓扑排序 {

        public static List<Node> sortedTopology(Graph graph) {
            //key node点
            //value 该点剩余的入度
            HashMap<Node, Integer> inMap = new HashMap<>();

            //入度为零的点
            Queue<Node> zeroNode = new LinkedList<>();

            for (Node node : graph.nodes.values()) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroNode.add(node);
                }

            }
            //结果
            List<Node> result = new ArrayList<>();
            while (!zeroNode.isEmpty()) {
                Node poll = zeroNode.poll();

                for (Node node : poll.next) {
                    //消除该点的影响
                    inMap.put(node, inMap.get(node) - 1);
                    if (inMap.get(node) == 0) {
                        zeroNode.add(node);
                    }
                }

            }

            return result;

        }
    }
