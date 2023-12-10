package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author gc
 * @date 2023-11-20 14:34
 */
public class Dijkstra算法_暴力 {

    public static HashMap<Node, Integer> dijkstra1(Node from) {

        //到某个点的距离表
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);

        //已经确定的点
        HashSet<Node> selectedNodeSet = new HashSet<>();

        //不包含已经确定的点的最小点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodeSet);


        while (minNode != null) {
            //原始点到最小点的距离
            Integer distance = distanceMap.get(minNode);

            //遍历边集
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                //以前无法达到的点
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    //更新记录
                    distanceMap.put(to, Math.min(distance + edge.weight, distanceMap.get(to)));

                }
            }
            selectedNodeSet.add(minNode);
            minNode =  getMinDistanceAndUnselectedNode(distanceMap, selectedNodeSet);

        }

        return distanceMap;


    }

    //暴力版本
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodeSet) {

        Node minNode=null;
        Integer minDistance=Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> nodeIntegerEntry : distanceMap.entrySet()) {
            Node node = nodeIntegerEntry.getKey();
            Integer distance = nodeIntegerEntry.getValue();

            if (!selectedNodeSet.contains(node)&&distance<minDistance){
                minDistance=distance;
                minNode=node;
            }
        }

        return minNode;

    }
}
