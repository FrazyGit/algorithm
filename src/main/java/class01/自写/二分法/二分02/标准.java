package class01.自写.二分法.二分02;

import java.util.Arrays;

public class 标准 {

    public static boolean search(int arr[], int num) {
        if (arr == null || arr.length < 1) {
            return false;
        }


        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = (r - l) / 2 + l;

            if (arr[mid] == num) {
                return true;
            }

            if (arr[mid] < num) {
                l = mid + 1;

            } else if (arr[mid] > num) {
                r = mid - 1;
            }

        }

        return arr[l] == num;
    }


    public static boolean search2(int arr[], int num) {
        if (arr == null || arr.length < 1) {
            return false;
        }


        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = (r - l) / 2 + l;

            if (arr[mid] == num) {
                return true;
            }

            if (arr[mid] < num) {
                l = mid + 1;

            } else if (arr[mid] > num) {
                r = mid - 1;
            }

        }

        return false;
    }


    public static boolean test(int[] arr, int num) {
        int i = Arrays.binarySearch(arr, num);

        if (i < 0) {
            return false;
        }
        return true;

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
        int maxValue = 1000;


        for (int i = 0; i < testCount; i++) {

            int[] ints = generateArr(maxSize, maxValue);
            Arrays.sort(ints);

            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());


            if (test(ints, value) == search(ints, value)) {
                System.out.println("nice");
            } else {
                System.out.println("no");
                System.out.println(Arrays.toString(ints));
                System.out.println(value);
                System.out.println(search(ints, value));
                return;
            }

            if (test(ints, value) == search2(ints, value)) {
                System.out.println("nice2");
            } else {
                System.out.println("no2");
                System.out.println(Arrays.toString(ints));
                System.out.println(value);
                System.out.println(search(ints, value));
                return;
            }
        }

    }
}
