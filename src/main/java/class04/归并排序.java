package class04;

import java.util.Arrays;

/**
 * @author wlkq
 * @date 2023-03-05 19:46
 */
public class 归并排序 {

    public static void mergeSort(int[] arr, int l, int r) {

        if (r == l) {//base case
            return;
        }

        int mid = l + (r - l) / 2;

        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);


    }

    public static void mergeSort2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }


        int step = 1;


        while (step < arr.length) {
            int l = 0;
            while (l < arr.length) {// l<arr.length-1 也对 但step最后一个没有考虑到 但没有影响 因为只要一个数

                int m = l + step - 1;

                if (m > arr.length - 1) {
                    break;
                }

                int r = Math.min(m + step, arr.length - 1);


                merge(arr, l, m, r);

                l = r + 1;

            }

            if (step > arr.length / 2) {//防止数组长度极大时溢出
                break;
            }
            step *= 2;


        }


    }


    // merge是关键
    //参数 左组第一个 l 左组最后一个 mid 右组最后一个 r
    public static void merge(int[] arr, int l, int mid, int r) {

        int[] help = new int[r - l + 1];

        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= r) {


            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 相等先拷贝那边需要看情况 普通排序情况下先左边右边都行


        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

    }

    public static void test(int[] arr) {

        Arrays.sort(arr);

    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    public static int[] generateArr(int maxSize, int maxValue) {
        int length = (int) (Math.random() * maxSize + 1);
        int[] arr1 = new int[length];


        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);

        }

        return arr1;

    }

    public static void main(String[] args) {
        int testCount = 100000;
        int maxSize = 1000;
        int maxValue = 100;


        for (int i = 0; i < testCount; i++) {

            int[] ints = generateArr(maxSize, maxValue);
            int[] tar = Arrays.copyOf(ints, ints.length);
            int[] ints1 = Arrays.copyOf(ints, ints.length);

//            System.out.println(Arrays.toString(ints1));
            test(ints);
//            mergeSort(ints1, 0, ints1.length - 1);
            mergeSort2(ints1);

            boolean equals = Arrays.equals(ints, ints1);

            if (equals) {

            } else {
                System.out.println("no");
                System.out.println(Arrays.toString(tar));
                return;

            }

        }


    }


}
