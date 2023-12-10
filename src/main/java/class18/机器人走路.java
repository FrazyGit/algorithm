package class18;

/**
 * @author gc
 * @date 2023-12-08 16:16
 */


/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class 机器人走路 {

    /**
     * 暴力递归
     *
     * @param N
     * @param start
     * @param target
     * @param K
     * @return
     */
    public static int ways1(int N, int start, int target, int K) {


        //边界判断
        if (N < 2 || start < 1 || start > N || target < 1 || target > N || K < 1) {
            return -1;
        }
        return process(N, start, K, target);
    }


    public static int process(int N, int cur, int K, int target) {
        if (K == 0) {
            return cur == target ? 1 : 0;
        }

        if (cur == 1) {
            return process(N, 2, K - 1, target);
        }
        if (cur == N) {
            return process(N, N - 1, K - 1, target);

        }
        return process(N, cur + 1, K - 1, target) + process(N, cur - 1, K - 1, target);

    }

    /**
     * 缓存
     *
     * @param N
     * @param start
     * @param target
     * @param K
     * @return
     */

    public static int ways2(int N, int start, int target, int K) {


        //边界判断
        if (N < 2 || start < 1 || start > N || target < 1 || target > N || K < 1) {
            return -1;
        }

        //缓存表 位置x坐标 步数为Y坐标
        int[][] dp = new int[N + 1][K + 1];

        //初始化缓存表
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(N, start, K, target, dp);


    }


    public static int process1(int N, int cur, int K, int target, int[][] dp) {
        if (dp[cur][K] != -1) {//查询缓存
            return dp[cur][K];
        }
        int res;
        if (K == 0) {
            return cur == target ? 1 : 0;
        }

        //使用else达到process1 return的效果
        if (cur == 1) {
            res = process1(N, 2, K - 1, target, dp);
        } else if (cur == N) {
            res = process1(N, N - 1, K - 1, target, dp);

        } else {
            res = process1(N, cur + 1, K - 1, target, dp) + process1(N, cur - 1, K - 1, target, dp);
        }
        //进行缓存
        dp[cur][K] = res;
        return res;

    }

    /**
     * 经典动态规划
     *
     * @param N
     * @param start
     * @param target
     * @param K
     * @return
     */

    public static int ways3(int N, int start, int target, int K) {

        //边界判断
        if (N < 2 || start < 1 || start > N || target < 1 || target > N || K < 1) {
            return -1;
        }

        //缓存表 位置Y坐标 步数为X坐标
        int[][] dp = new int[N + 1][K + 1];

        //初始化缓存表 第一列已经填完
        dp[target][0] = 1;


        for (int step = 1; step <= K; step++) {//从左至右
            //第一行
            dp[1][step] = dp[2][step - 1];
            for (int cur = 2; cur <= N - 1; cur++) {//中间行 从上到下
                dp[cur][step] = dp[cur - 1][step - 1] + dp[cur + 1][step - 1];
            }
            //最后一行
            dp[N][step] = dp[N - 1][step - 1];


        }

        return dp[start][K];


    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
