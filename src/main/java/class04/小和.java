package class04;

import java.util.Arrays;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * <p>
 * <p>
 * 相当于一个数右边有多少个数比他大
 * <p>
 * 左组拷贝产生小和 相等先动右边的
 *
 * @author wlkq
 * @date 2023-03-08 10:19
 */
public class 小和 {



    public static int process(int[] arr, int l, int r) {

        if (arr == null || arr.length < 2) {  //不是可有可无 边界
            return 0;
        }

        if (r == l) {
            return 0;
        }

        int mid = l + (r - l) / 2;

        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(l, r, mid, arr);


    }

    //算和填数据分开进行
    public static int merge1(int l, int r, int mid, int[] arr) {

        int[] help = new int[r - l + 1];

        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        int res = 0;
        int windowR = mid + 1;

        for (int k = l; k <= mid; k++) { //算结果 窗口的前提是单调性
            while (windowR <= r && arr[k] >= arr[windowR]) {
                windowR++;
            }
            res += ((r - windowR) + 1) * arr[k];

        }

        while (p1 <= mid && p2 <= r) {//填数据 保证下个执行的merger的单调性


            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];//算和填分开，相等先拷贝哪边不影响，主要是保证单调性


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




        return res;

    }


    //算和填数据一起进行
    public static int merge(int l, int r, int m, int arr[]) {
        int[] help = new int[r - l + 1];

        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        int res = 0;


        while (p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 相等先拷贝那边需要看情况，保证算时数据不被跳过

        }


        while (p1 <= m) {


            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

        return res;


    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
        int testTime = 10000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int r1 = process(arr1, 0, arr1.length - 1);
            int r2 = comparator(arr2);

            if (r1!=r2) {
                succeed = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(r1);
                System.out.println(r2);

                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
