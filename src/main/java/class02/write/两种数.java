package class02.write;

/**
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 *
 * @author wlkq
 * @date 2023-03-16 8:31
 */
public class 两种数 {


    public static void doubleNum(int[] arr) {

        int res = 0;
        for (int a : arr) {
            res ^= a;
        }

        int temp = find(res);

        int res2 = 0;
        for (int a : arr) {
            if ((a & temp) != 0) {
                res2 ^= a;
            }
        }

        int num2 = res ^ res2;
        System.out.println(res2 + " " + num2);


    }

    public static int find(int num) {
        for (int i = 0; i <= 31; i++) {
            int res = num & 1 << i;
            if (res != 0) {

                return res;

            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] test2 = {3, 34, 34, 55, 55, -5, -5, 77, 77, 8, 8, -9, -9, 0, 0, 6, 6, 6, 3, 3, 3, 4, 4, 4};
        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2, 2, 5};

        doubleNum(arr2);
    }

}
