package class01.二分法;



import java.util.Arrays;

/**
 *二分法
 * 关键
 *这两种写法，有的同学就问了，啥时候写 ≤ 时候写 < . 我只能说都对你如果把你的大逻辑定成至少两个数的时候，
 * 你就有这种逻辑下的边界条件。如果你把你的逻辑定成至少一个数字我就能二分，那就有这种逻辑下的边界条件，
 * 这个东西其实相当的仁者见仁智者见智，写Code扣边界条件这个过程是必不可少的
 *
 *
 * 使用情况
 * 只要你能够构建一种排他性的东西，左右两侧有一半肯定有, 有一半可能没有,不确定，
 * 如果你只找一个, 砍一半就行了，只要你能构建出类似于排他性的东西，你就能二分, 不一定要求数组有序
 *
 *
 * @author wlkq
 * @date 2023-02-22 19:09
 */
public class twoPoints {


    public static boolean search(int[] arr, int num) {

        if (arr == null || arr.length < 1) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;
        int mid;

        while (l < r) {
            mid = l + (r - l) / 2;

            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                l = mid + 1;

            } else if (arr[mid] > num) { //可能让r越界
                r = mid - 1;
            }

        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(num);
//        System.out.println(l);

        return arr[l] == num;//只能是l r会越界


    }

    public static boolean search2(int[] arr, int num) {

        if (arr == null || arr.length < 1) {
            return false;
        }

        int l = 0;
        int r = arr.length - 1;
        int mid;

        while (l <= r) {
            mid = l + (r - l) / 2;

            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                l = mid + 1;

            } else if (arr[mid] > num) {
                r = mid - 1;
            }

        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(num);
//        System.out.println(l);

        return false; //只有r>l时才会出来


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




