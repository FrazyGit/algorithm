package class05;

import java.util.Arrays;

/**
 * 给定一个数x, <x的整体放在左边, =x的整体放在中间, >x的整体放在右边
 * 用整个数组的最后一个数做目标 一定有等于区
 * 不要用辅助数组, 时间复杂度O(N) 完成上面的调整
 * 返回相等的区域下标
 * <p>
 * <p>
 * 加强版 O(N):
 * 一个数组中, 给定x, 做到:
 * x的整体放在左边, =x的整体放在中间, >x的整体放在右边:
 * 小于区放在数组左边, 大于区在数组右边, 当前数从0开始, 有3种可能:
 * 1) 当前数<目标, 当前数跟小于区的下一个数交换, 小于区向右扩, 当前数跳到下一个
 * 2) 当前数=目标, 当前数直接跳下一个
 * 3) 当前数>目标, 当前数跟大于区的前一个数交换, 大于区向左扩, 当前数停在原地不动 为什么要停在原地  因为 3是换过来的,还没有验过而小于区的是看过的, 可以直接跳下一个
 * 当前数和右边界撞上的时候停, 此时
 * 整个数组小于 目标数的 放左边，等于 目标数的 放中间, 大于目标数的的放右边.
 * <p>
 * <p>
 * 复杂度 O(N)
 * 左窗口不回退, 右窗口不回退
 * 小于区域向右不回头
 * 大于区域向左不回头
 *
 * @author wlkq
 * @date 2023-03-19 16:10
 */
public class 荷兰国旗 {

    public static int[] flag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, 1};
        }

        if (l == r) {
            return new int[]{l, l};

        }

        int index = l;
        int left = l - 1;
        int right = r;

        while (index < right) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, --right, index);

            } else {
                swap(arr, index++, ++left);
            }


        }
        swap(arr, r, right++);

        return new int[]{left + 1, right - 1};


    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    public static void main(String[] args) {
        int[] test = {-72};

        int[] test1 = flag(test, 0, test.length - 1);



        System.out.println(Arrays.toString(test1));
    }


}
