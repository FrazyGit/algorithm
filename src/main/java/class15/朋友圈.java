package class15;

/**
 * @author gc
 * @date 2023-11-08 10:29
 */
public class 朋友圈 {

    public int findCircleNum(int[][] isConnected) {
        int N = isConnected.length;

        UnionFind unionFind = new UnionFind(N);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.getSets();


    }

    public static class UnionFind {


        private int[] father;

        private int[] size;

        //代替栈
        private int[] help;

        private int sets;

        public int getSets() {
            return sets;
        }

        public UnionFind(int N) {
            father = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;

            for (int i = 0; i < N; i++) {
                father[i] = i;
                size[i] = 1;
            }

        }

        public boolean isSameSet(int value1, int value2) {
            return find(value1) == find(value2);

        }

        public void union(int a, int b) {
            int aFather = find(a);
            int bFather = find(b);

            int aSize = size[aFather];
            int bSize = size[bFather];

            if (aFather != bFather) {
                if (aSize >= bSize) {
                    father[bFather] = aFather;
                    size[aFather] = aSize + bSize;
                } else {
                    father[aFather] = bFather;
                    size[bFather] = aSize + bSize;
                }
            }
            sets--;


        }

        public int find(int value) {
            //代替栈进行扁平化
            int hi = 0;
            while (value != father[value]) {
                help[hi++] = value;
                value = father[value];
            }

            //hi多加了一次要减掉
            for (hi--; hi > 0; hi--) {
                father[help[hi]] = value;
            }

            return value;

        }
    }
}
