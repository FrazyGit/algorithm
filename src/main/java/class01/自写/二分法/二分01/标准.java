package class01.自写.二分法.二分01;

import java.util.Arrays;

/**
 *
 * 在一个有序数组中，找某个数是否存在
 *
 * @author wlkq
 * @date 2023-03-05 15:56
 */


public class 标准 {

    public static boolean search(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                r = mid - 1;

            }else {
                l= mid+1;
            }


        }
        return arr[r]==num ;

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
        int testCount = 10000;
        int maxSize = 100;
        int maxValue = 100;


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
        }

    }
}
