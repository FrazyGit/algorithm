package class02.write;

import java.util.Arrays;

/**
 * @author wlkq
 * @date 2023-03-15 9:21
 */
public class 异或性质 {

    public static void swap(int[] arr, int j, int k) {
        arr[j] = arr[k] ^ arr[j];
        arr[k] = arr[k] ^ arr[j];
        arr[j] = arr[k] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] test = {1, 2};

        swap(test, 0, 1);

        System.out.println(Arrays.toString(test));

        System.out.println(3 ^ 3);
        System.out.println(3 ^ 0);

        System.out.println(3 ^ 3 ^ 4 ^ 5);
        System.out.println(4 ^ 5 ^ 3 ^ 3);


        int[] test2 = {3, 34, 34, 55, 55, -5, -5, 77, 77, 8, 8, -9, -9, 0, 0};


        print(3);
        print(-3);
        print(3&(-3));//取最左边的一

        int res = 0;
        for (int i = 0; i < test2.length; i++) {
            res = res ^ test2[i];
        }

        System.out.println(res);



    }


    public static void find(int num) {
        for (int i = 0; i <= 31; i++) {
            int res = num & 1 << i;
            if (res != 0) {
                print(res);
                return;

            }
        }
    }

    public static void find1(int num) {
        for (int i = 31; i >=0; i--) {
            int res = num & 1 << i;
            if (res != 0) {
                print(res);
                return;

            }
        }
    }


    public static void print(int num) {

        for (int i = 31; i >= 0; i--) {
            System.out.print((num >>> i & 1) == 1 ? "1" : "0");
        }
        System.out.println();
    }


}
