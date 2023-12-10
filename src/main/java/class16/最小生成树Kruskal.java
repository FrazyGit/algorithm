package class16;


import java.util.*;

/**
 * @author gc
 * @date 2023-11-19 10:07
 */
public class 最小生成树Kruskal {

    public static class UnionSet<V> {

        //        可以包一层
//        public HashMap<V, Node<V>> nodes;
        //父子关系表
        private HashMap<V, V> parents;

        //集合大小表
        //代表节点才有意义
        private HashMap<V, Integer> sizeMap;

        public UnionSet(Collection<V> collection) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V v : collection) {
                parents.put(v, v);
                sizeMap.put(v, 1);
            }


        }

        public boolean isSameSet(V value1, V value2) {
            if (parents.get(value1) == null || parents.get(value2) == null) {
                return false;
            }

            //如果输入都是字符串1，要区分不同位置的1
            return findFather(value1) == findFather(value2);
        }

        public void Union(V a, V b) {
            if (parents.get(a) == null || parents.get(b) == null) {
                return;
            }
            V aHead = findFather(a);
            V bHead = findFather(b);
            if (aHead != bHead) {
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                if (aSize >= bSize) {
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSize + bSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSize + bSize);
                    sizeMap.remove(aHead);
                }
            }

        }

        public V findFather(V value) {
            //链条扁平化
            Stack<V> path = new Stack<V>();

            while (value != parents.get(value)) {
                path.push(value);
                value = parents.get(value);
            }

            //链条扁平化
            while (path.isEmpty()) {
                V pop = path.pop();
                parents.put(pop, value);
            }

            return value;

        }

    }


    public static Set<Edge> Kruskal(Graph graph) {
        UnionSet<Node> nodeUnionSet = new UnionSet<Node>(graph.nodes.values());

        //权值小的在前
        PriorityQueue<Edge> priorityQueue = new PriorityQueue(new EdgeComparator());

        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);

        }

        Set<Edge> ans = new HashSet<>();

        while (!priorityQueue.isEmpty()){
            Edge poll = priorityQueue.poll();
            //要没不成环的边
            if (!nodeUnionSet.isSameSet(poll.to,poll.to)){
                ans.add(poll);
                nodeUnionSet.Union(poll.to,poll.from);
            }
        }

        return ans;


    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


}
