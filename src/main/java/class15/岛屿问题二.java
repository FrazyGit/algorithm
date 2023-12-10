package class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author gc
 * @date 2023-11-11 10:49
 */
public class 岛屿问题二 {

    public List<Integer> numIslands(int M, int N, int[][] positions) {
        UnionFind unionFind = new UnionFind(M, N);

        List<Integer> ans = new ArrayList<>();

        for (int[] item :
                positions) {
            ans.add(unionFind.connect(item[0], item[1]));
        }

        return ans;

    }


    public class UnionFind {

        private int[] parents;

        private int[] sizes;

        private int sets;

        private int[] help;

        //二维数组的宽
        private int col;

        //二维数组的长
        private int row;


        public UnionFind(int M, int N) {
            col = N;
            row = M;
            int len = M * N;
            parents = new int[len];
            sizes = new int[len];
            help = new int[len];

        }

        public int index(int i, int j) {
            return i * col + j;

        }


        public int getSets() {
            return sets;
        }


        private Integer connect(int a, int b) {
            int index = index(a, b);

            if (sizes[index] == 0) {
                parents[index] = index;
                sizes[index] = 1;
                sets++;
                union(a, b, a - 1, b);
                union(a, b, a + 1, b);
                union(a, b, a, b - 1);
                union(a, b, a, b + 1);
            }

            return sets;

        }


        public void union(int a1, int a2, int b1, int b2) {

            //边界判断
            if (a1 < 0 || a1 >= row || a2 < 0 || a2 >= col || b1 < 0 || b1 >= row || b2 < 0 || b2 >= col) {
                return;
            }
            //转换为下标
            int a = index(a1, a2);
            int b = index(b1, b2);

            if (sizes[a] == 0 || sizes[b] == 0) {
                return;
            }

            int aFather = findFather(a);
            int bFather = findFather(b);

            if (aFather != bFather) {
                if (sizes[aFather] >= sizes[bFather]) {
                    parents[bFather] = aFather;
                    sizes[aFather] += sizes[bFather];
                } else {
                    parents[aFather] = bFather;
                    sizes[bFather] += sizes[aFather];
                }
                sets--;
            }

        }


        //下标计算
        public int findFather(int value) {
            int he = 0;
            while (value != parents[value]) {
                help[he++] = value;
                value = parents[value];
            }

            for (he--; he > 0; he--) {
                parents[help[he]] = value;
            }

            return value;
        }

    }

//    当m,n很大, m:100亿, n10亿, k=5 优化
//    用字符串代表一个位置 “17_1009”
    public class UnionFind2 {

        private HashMap<String, String> parents;

        private HashMap<String, Integer> size;

        private int sets;

        private ArrayList<String> help;


        public UnionFind2(int M, int N) {
            parents = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;

        }


        public int getSets() {
            return sets;
        }


        private Integer connect(int a, int b) {

            String key = String.valueOf(a) + "_" + String.valueOf(b);
            if (!parents.containsKey(key)) {
                parents.put(key, key);
                size.put(key, 1);
                sets++;

                String keyDown = String.valueOf(a - 1) + "_" + String.valueOf(b);
                String keyUp = String.valueOf(a + 1) + "_" + String.valueOf(b);
                String keyLeft = String.valueOf(a) + "_" + String.valueOf(b - 1);
                String keyRight = String.valueOf(a) + "_" + String.valueOf(b + 1);

                union(keyUp, key);
                union(keyDown, key);
                union(keyLeft, key);
                union(keyRight, key);
            }

            return sets;

        }


        public void union(String s1, String s2) {

            if (parents.containsKey(s1) || parents.containsKey(s2)) {
                String s1father = findFather(s1);
                String s2father = findFather(s2);

                Integer s1Size = size.get(s1father);
                Integer s2Size = size.get(s2father);

                if (!s1father.equals(s2father)) {
                    if (s1Size >= s2Size) {
                        parents.put(s2father, s1father);
                        size.put(s1father, s1Size + s2Size);
                    } else {
                        parents.put(s1father, s2father);
                        size.put(s2father, s1Size + s2Size);
                    }
                }
                sets--;
            }

        }


        public String findFather(String value) {
            while (!value.equals(parents.get(value))) {
                help.add(value);
                value = parents.get(value);
            }

            for (String item :
                    help) {
                parents.put(item, value);
            }

            //清空集合
            help.clear();

            return value;
        }

    }
}
