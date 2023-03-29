package class01.自写.排序;

import java.util.Arrays;

/**
 * @author wlkq
 * @date 2023-03-14 14:27
 */
public class sort {

    public static void sort1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = arr.length - 1; i >= 0; i--) {
            int max = i;
            for (int j = 0; j <= i; j++) {

                if (arr[j] > arr[max]) {
                    max = j;
                }
            }

            swap(arr, i, max);
        }

    }

    public static void sort2(int[] arr) {


        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {

                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            swap(arr, i, min);
        }

    }


    public static void sort3(int[] arr) {


        //0-i 想有序的
        for (int i = 1; i < arr.length; i++) {


            for (int j = i - 1; j >= 0; j--) {

                //从新进入的数开始向前看
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }

            }



        }

    }


    public static void swap(int[] arr, int j, int k) {
        int temp = arr[j];
        arr[j] = arr[k];
        arr[k] = temp;

    }


    public static void main(String[] args) {
        int testCount = 100000;
        int maxValue = 100;
        int maxSize = 100;

        for (int i = 0; i < testCount; i++) {
            int[] ints = generateArr(maxValue, maxSize);
            int[] ints1 = Arrays.copyOf(ints, ints.length);

            System.out.println(Arrays.toString(ints));


            sort3(ints);
            Arrays.sort(ints1);

            if (Arrays.equals(ints, ints1)) {
                System.out.println("nice");
            } else {
                System.out.println("vice");
                return;
            }
        }


    }

    public static int[] generateArr(int maxValue, int maxSize) {
        int[] arr = new int[(int) (Math.random() * maxSize + 1)];//(int) (Math.random()*maxSize+1) 前后都有括号

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int) (Math.random() * maxValue + 1)) - ((int) (Math.random() * maxValue + 1));
        }
        return arr;

    }


}
