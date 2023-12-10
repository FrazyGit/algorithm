package class18;

/**
 * @author gc
 * @date 2023-12-09 9:51
 * <p>
 * <p>
 * 玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class 左右拿牌 {


    /**
     * 暴力递归
     *
     * @param arr
     * @return
     */
    public static int win1(int[] arr) {
        return Math.max(F(arr, 0, arr.length - 1), S(arr, 0, arr.length - 1));
    }

    //定义递归:
    //inf f(arr, L, R)
    //在arry, L..R范围上拿纸牌, 如果我是先手, 返回最后获得的最大分数
    public static int F(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        return Math.max(arr[l] + S(arr, l + 1, r), arr[r] + S(arr, l, r - 1));

    }

    //定义递归:
    //inf f(arr, L, R)
    //在arry, L..R范围上拿纸牌, 如果我是后手, 返回最后获得的最大分数
    public static int S(int[] arr, int l, int r) {
        if (l == r) {//只剩一张了后手拿不到
            return 0;
        }
        //由于先手的决定，我只能在下次我先手的结果中拿到较差的
        //我在对手拿剩下的范围拿
        return Math.min(F(arr, l + 1, r), F(arr, l, r - 1));
    }

    /**
     * 缓存方法
     *
     * @param arr
     * @return
     */
    public static int win2(int[] arr) {
        int length = arr.length;
        //l为Y坐标 r为X坐标
        //先手的缓存表
        int[][] dpF = new int[length][length];
        //后手的缓存表
        int[][] dpS = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dpF[i][j] = -1;
                dpS[i][j] = -1;
            }
        }
        return Math.max(F2(arr, 0, length - 1, dpF, dpS), S2(arr, 0, length - 1, dpF, dpS));
    }


    public static int F2(int[] arr, int l, int r, int[][] dpF, int[][] dpS) {

        if (dpF[l][r] != -1) {
            return dpF[l][r];
        }
        if (l == r) {
            dpF[l][r] = arr[l];
            return arr[l];
        }
        int max = Math.max(arr[l] + S2(arr, l + 1, r, dpF, dpS), arr[r] + S2(arr, l, r - 1, dpF, dpS));
        dpF[l][r] = max;
        return max;


    }


    public static int S2(int[] arr, int l, int r, int[][] dpF, int[][] dpS) {

        if (dpS[l][r] != -1) {
            return dpS[l][r];
        }
        if (l == r) {
            dpS[l][r] = 0;
            return 0;
        }

        int min = Math.min(F2(arr, l + 1, r, dpF, dpS), F2(arr, l, r - 1, dpF, dpS));
        dpS[l][r] = min;
        return min;
    }


    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int win3(int[] arr) {
        int length = arr.length;
        //l为Y坐标 r为X坐标
        //先手的缓存表
        int[][] dpF = new int[length][length];
        //后手的缓存表
        int[][] dpS = new int[length][length];

        //对角线
        for (int i = 0; i < length; i++) {
            dpF[i][i] = arr[i];
        }

        //从左至右的对角线
        for (int x = 1; x < length; x++) {
            int r = x;
            int l = 0;

            //向下
            while (r < length) {
                dpF[l][r] = Math.max(arr[l] + dpS[l + 1][r], arr[r] + dpS[l][r - 1]);
                dpS[l][r] = Math.min(dpF[l + 1][r], dpF[l][r - 1]);

                //按对角线填
                l++;
                r++;
            }
        }
        return Math.max(dpS[0][length - 1], dpF[0][length - 1]);


    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }

}
