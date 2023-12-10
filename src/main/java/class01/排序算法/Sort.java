package class01.排序算法;

import java.util.Arrays;


public class Sort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int j = arr.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }

            }
        }


    }


    //插入排序

    public static void insertSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);

            }

        }


    }

    // 选择排序

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int min = i;//要确定最小值的位置
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }

            }
            swap(arr, i, min);

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
        int maxSize = 100;
        int maxValue = 100;


//        for (int s:ints){
//            System.out.println(s);
//        }
//
//        System.out.println(ints.length);

        for (int i = 0; i < testCount; i++) {

            int[] ints = generateArr(maxSize, maxValue);
            int[] tar = Arrays.copyOf(ints, ints.length);
            int[] ints1 = Arrays.copyOf(ints, ints.length);

            test(ints);
            selectSort(ints1);
//            bubbleSort(ints1);

            boolean equals = Arrays.equals(ints, ints1);

            if (equals) {
                System.out.println("nice");
            } else {
                System.out.println("no");
                System.out.println(Arrays.toString(tar));
                return;

            }

        }


    }


}
