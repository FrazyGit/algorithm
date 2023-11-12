package class15;

import java.io.*;

/**
 * @author gc
 * @date 2023-11-08 10:49
 */
public class 数组实现并查集 {

    public static class UnionFind {

        public static int MAXN = 1000001;

        private static int[] father = new int[MAXN];

        private static int[] size = new int[MAXN];

        //代替栈
        private static int[] help = new int[MAXN];

        private static int sets;

        public int getSets() {
            return sets;
        }

        public static void init(int n) {
            for (int i = 0; i <= n; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }


        public static boolean isSameSet(int value1, int value2) {
            //数组实现根据初始化的方式来避免判断不同位置的1的问题（比如下标转换）
            return find(value1) == find(value2);


        }

        public static void union(int a, int b) {
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
                sets--;

            }


        }

        public static int find(int value) {
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


        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StreamTokenizer in = new StreamTokenizer(br);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
            while (in.nextToken() != StreamTokenizer.TT_EOF) {
                int n = (int) in.nval;
                init(n);
                in.nextToken();
                int m = (int) in.nval;
                for (int i = 0; i < m; i++) {
                    in.nextToken();
                    int op = (int) in.nval;
                    in.nextToken();
                    int x = (int) in.nval;
                    in.nextToken();
                    int y = (int) in.nval;
                    if (op == 1) {
                        out.println(isSameSet(x, y) ? "Yes" : "No");
                        out.flush();
                    } else {
                        union(x, y);
                    }
                }
            }
        }
    }


}
