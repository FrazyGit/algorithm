package class16;

import java.util.HashMap;

/**
 * @author gc
 * @date 2023-11-22 8:56
 */
public class Dijkstra算法_加强堆优化 {

    public static HashMap<Node, Integer> dijkstra1(Node head, int size) {
        NodeHelp nodeHelp = new NodeHelp(size);
        nodeHelp.addOrUpdateOrIgnore(head, 0);

        HashMap<Node, Integer> result = new HashMap<>();

        while (!nodeHelp.isEmpty()) {
            //不包含已经确定的点的最小点
            NodeRecord pop = nodeHelp.pop();
            int distance = pop.distance;
            Node node = pop.node;
            for (Edge edge : node.edges) {
                nodeHelp.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(node, distance);

        }

        return result;

    }


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHelp {
        private Node[] nodes;//实际上的堆结构

        private HashMap<Node, Integer> heapIndexMap;//反向索引表

        private HashMap<Node, Integer> distanceMap;//距离表


        private int size;//堆上点个数

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        private void heapify(int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {//边界判断
                int min = left + 1 < size && distanceMap.get(left + 1) < distanceMap.get(left) ? left + 1 : left;
                min = distanceMap.get(index) < distanceMap.get(min) ? min : index;
                if (min == index) {
                    break;
                }
                swap(index, min);
                index = min;
                left = 2 * index + 1;

            }

        }

        public boolean isEmpty() {
            return size <= 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                insertHeapify(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                distanceMap.put(node, distance);
                heapIndexMap.put(node, size);
                insertHeapify(size++);

            }
        }

        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(0));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.put(nodes[size - 1], -1);
            nodes[size - 1] = null;
            heapify(0, --size);

            return nodeRecord;

        }


        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        public NodeHelp(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }
    }


}
