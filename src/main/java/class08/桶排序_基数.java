package class08;

import java.util.Arrays;

/**
 * 一般来讲，基数排序要求，样本是10进制的正整数
 *
 * @author wlkq
 * @date 2023-03-27 16:39
 */
public class 桶排序_基数 {


    //需要2个辅助数组
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int[] help1 = new int[arr.length];

        for (int d = 1; d <= getMaxBits(arr); d++) {
            int[] help = new int[10];
            for (int a : arr) {
                help[getDigit(a,d)]++;
            }

            for (int i = 1; i < help.length; i++) {
                help[i] = help[i - 1] + help[i];
            }

            for (int i = arr.length - 1; i >= 0; i--) {

                help1[--help[getDigit(arr[i],d)]] = arr[i];

            }

            for (int i=0;i<arr.length;i++){
                arr[i]=help1[i];
            }
        }




    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


    public static Integer getMaxBits(int[] arr) {
        int max = arr[0];
        for (int a : arr) {
            max = Math.max(max, a);
        }

        int res = 0;
        while (max > 0) {
            max /= 10;
            res++;
        }

        return res;


    }



        // for test
        public static void comparator(int[] arr) {
            Arrays.sort(arr);
        }

        // for test
        public static int[] generateRandomArray(int maxSize, int maxValue) {
            int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random());
            }
            return arr;
        }

        // for test
        public static int[] copyArray(int[] arr) {
            if (arr == null) {
                return null;
            }
            int[] res = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                res[i] = arr[i];
            }
            return res;
        }

        // for test
        public static boolean isEqual(int[] arr1, int[] arr2) {
            if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
                return false;
            }
            if (arr1 == null && arr2 == null) {
                return true;
            }
            if (arr1.length != arr2.length) {
                return false;
            }
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    return false;
                }
            }
            return true;
        }

        // for test
        public static void printArray(int[] arr) {
            if (arr == null) {
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        // for test
        public static void main(String[] args) {
            int testTime = 500000;
            int maxSize = 100;
            int maxValue = 100000;
            boolean succeed = true;
            for (int i = 0; i < testTime; i++) {
                int[] arr1 = generateRandomArray(maxSize, maxValue);
                int[] arr2 = copyArray(arr1);
//                printArray(arr1);
                sort(arr1);
                comparator(arr2);
                if (!isEqual(arr1, arr2)) {
                    succeed = false;
                    printArray(arr1);
                    printArray(arr2);
                    break;
                }
            }
            System.out.println(succeed ? "Nice!" : "Fucking fucked!");




        }



}
