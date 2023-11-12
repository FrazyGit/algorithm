package class14.并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author gc
 * @date 2023-11-08 9:32
 */
public class 哈希表实现 {
    public static class UnionSet<V>{

//        可以包一层
//        public HashMap<V, Node<V>> nodes;
        //父子关系表
        private HashMap<V,V> parents;

        //集合大小表
        //代表节点才有意义
        private HashMap<V,Integer> sizeMap;

        public UnionSet(List<V> values){
            for (V value :
                    values) {
                parents.put(value, value);
                sizeMap.put(value,1);
            }
        }

        public boolean isSameSet(V value1,V value2){
            if (parents.get(value1)==null||parents.get(value2)==null){
                return false;
            }

            //如果输入都是字符串1，要区分不同位置的1
          return  findFather(value1)==findFather(value2);
        }

        public void Union(V a,V b){
            if (parents.get(a)==null||parents.get(b)==null){
                return ;
            }
            V aHead = findFather(a);
            V bHead = findFather(b);
            if (aHead!=bHead){
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                if (aSize>=bSize){
                    parents.put(bHead,aHead);
                    sizeMap.put(aHead,aSize+bSize);
                    sizeMap.remove(bHead);
                }else {
                    parents.put(aHead,bHead);
                    sizeMap.put(bHead,aSize+bSize);
                    sizeMap.remove(aHead);
                }
            }

        }

        public V findFather(V value){
            //链条扁平化
            Stack<V> path = new Stack<V>();

            while (value!=parents.get(value)){
                path.push(value);
                value=parents.get(value);
            }

            //链条扁平化
            while (path.isEmpty()){
                V pop = path.pop();
                parents.put(pop,value);
            }

            return value;

        }

    }
}
