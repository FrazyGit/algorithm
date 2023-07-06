package class04;

import javafx.beans.binding.When;

import java.util.Arrays;

/**
 * 一个数组的子数组的和在[lower,upper]上的个数
 * <p>
 * 第一步转化:
 * 以数组每个位置结尾的达标数量
 * <p>
 * 第二步转化:
 * 如果 sum(i...j)累加和在 {low, upper}范围上, 则:
 * sum[0...j] - sum[0...i-1]也在这个范围上
 * <p>
 * 例
 * 求以17位置结尾的子数组
 * [60, 90], 看之前的前缀和中有多少个落在这个范围
 * 任何一个前缀和只要落在这个范围上, 都可以转化出一个以17位置结尾的达标子数组
 * <p>
 * 结论：
 * <p>
 * 原始array处理成前缀和数组sum(此时原数组可以忘记了),
 * 求sum数组中出现的每一个数x, 它之前有多少个数落在 [x-upper, x-lower]上
 * 下一个数假如是Y, 就是求Y之前有多少个数落在 [Y-upper, Y-lower]上
 * 下一个数假如是Z, 就是求Z之前有多少个数落在 [Z-upper, X-lower]上
 * 假设 0~i整体累加和是x, 题目目标 [Lower, upper]
 * 求必须以i位置结尾的子数组有多少个在这个目标范围, 等同于
 * 去求 i之前的所有前缀和中, 有多少个前缀和在[x-upper, x-lower]范围上
 * <p>
 * <p>
 * <p>
 * 对于右组中的每个数X,求左组中有多少个数，位于[X- upper,X- 1ower]
 * 虽然每一个数的目标是动的，但是每一个数的上限，下限不可能回退--》 窗口 不回退源自单调性
 *
 * @author wlkq
 * @date 2023-03-18 18:31
 */
public class 子数组和的范围 {


    public static long[] getPreSum(int[] arr) {


        long[] res = new long[arr.length];

        res[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            res[i] = res[i - 1] + arr[i];
        }

        return res;


    }




    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        long[] preSum = getPreSum(nums);


        return process(preSum, lower, upper, 0, preSum.length-1);


    }


    public static int process(long[] sum, int lower, int upper, int l, int r) {

        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }

        int mid = l + (r - l) / 2;

        return process(sum, lower, upper, l, mid) + process(sum, lower, upper, mid + 1, r) + merge(sum, lower, upper, l, mid, r);

    }

    public static int merge(long[] sum, int lower, int upper, int l, int mid, int r) {

        long[] help = new long[r - l + 1];//int 溢出


        int res = 0;
        int windowL = l;
        int windowR = l;


        //[windowL,windowR)
        for (int k = mid + 1; k <= r; k++) {
            long max = sum[k] - lower;
            long min = sum[k] - upper;

            while (windowL <= mid && sum[windowL] < min) {
                windowL++;
            }

            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }

            res += windowR - windowL ;


        }

        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= r) {
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }

        while (p2 <= r) {
            help[i++] = sum[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            sum[j + l] = help[j];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] test = {-2,5,-1};

        int i = countRangeSum(test, -2, 2);

        System.out.println(i);


    }
}
