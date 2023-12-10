package class16;

import java.io.*;
import java.util.ArrayList;

/**
 * @author gc
 * @date 2023-11-18 9:05
 */
public class 拓扑排序_牛客 {

    public static int MAXN = 200001;

    //入度为零的点
    public static int[] queue = new int[MAXN];

    //入度
    public static int[] indegree = new int[MAXN];

    //答案集合
    public static int[] ans = new int[MAXN];

    public static int
            n, //点的个数
            m, //边的条数
            from, //开始点
            to; //结束点


    public static void main(String[] args) throws IOException {
//        获取输入
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//       答案输出
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            //        构造点的直接邻居
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

//            这个bug的是因为，在add元素时，直接指定了index=1，跳过了index=0。应该加判断。 所以下面从零开始
            //初始化点
            for (int i = 0; i <= n; i++) {
                graph.add(i, new ArrayList<>());

            }

            //初始化边
            for (int i = 0; i < m; i++) {
                in.nextToken();
                from = (int) in.nval;
                in.nextToken();
                to = (int) in.nval;

                graph.get(from).add(to);

            }

//        开始排序
            if (!topoSort(graph)) {
                System.out.println(-1);
            } else {
                for (int i = 0; i < n - 1; i++) {
                    out.print(ans[i] + " ");
                }
                out.println(ans[n - 1]);
            }
            out.flush();
        }


    }

    private static boolean topoSort(ArrayList<ArrayList<Integer>> graph) {

        //初始化入度
        for (int i = 1; i <= n; i++) {
            for (Integer integer : graph.get(i)) {
                indegree[integer]++;
            }


        }
        //为了遍历入度未0的点 r从右边遍历 l从左边遍历

        int r = 0;
        int l = 0;

        //初始化入度为0的点
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;

            }

        }

        int cnt = 0;

        while (l < r) {
            int cur = queue[l++];
            ans[cnt++] = cur;

            for (Integer integer : graph.get(cur)) {
                if (--indegree[integer] == 0) {
                    queue[r++] = integer;
                }
            }

        }

        return n == cnt;

    }


}




