package class15;

/**
 * @author gc
 * @date 2023-11-08 18:34
 */
public class 岛屿数量 {


    //递归感染
    public int numIslands1(char[][] grid) {

        int rs = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //LeetCode输入是字符
                if (grid[i][j] == '1') {
                    rs++;
                    infect(grid, i, j);

                }

            }
        }

        return rs;

    }

    private void infect(char[][] grid, int i, int j) {
        //边界条件
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);

    }

    //并查集
    public int numIslands(char[][] grid) {

        int col = grid[0].length;
        int row = grid.length;
        UnionFind unionFind = new UnionFind(grid);

        //检查第一行
        for (int i = 1; i < col; i++) {
            //检查左边
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionFind.union(0, i - 1, 0, i);
            }
        }

        //检查第一列
        for (int i = 1; i < row; i++) {
            //检查上边
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                unionFind.union(i - 1, 0, i, 0);
            }
        }

        //通过上面两个for循环已经进行了边界判断
        //检查剩下的
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i][j] == '1') {
                    //上
                  if (grid[i-1][j] == '1'){
                      unionFind.union(i - 1, j, i, j);
                  }

                  //左
                    if (grid[i][j-1] == '1'){
                        unionFind.union(i, j-1, i, j);
                    }
                }
            }
        }

        return unionFind.getSets();


    }

    public class UnionFind {

        private int[] parents;

        private int[] sizes;

        private int sets;

        private int[] help;

        //二维数组的宽
        private int col;


        public UnionFind(char[][] grid) {
            col = grid[0].length;
            int len = grid.length * col;

            parents = new int[len];
            sizes = new int[len];
            help = new int[len];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parents[index] = index;
                        sizes[index] = 1;
                        sets++;
                    }
                }
            }

        }

        public int index(int i, int j) {
            return i * col + j;

        }


        public int getSets() {
            return sets;
        }


        public void union(int a1, int a2, int b1, int b2) {
            //转换为下标
            int a = index(a1, a2);
            int b = index(b1, b2);

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

        public boolean isSameSet(int a, int b) {
            return findFather(a) == findFather(b);
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


}
