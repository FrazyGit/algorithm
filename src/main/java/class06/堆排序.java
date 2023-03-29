package class06;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1 建堆一
 * 先整个数组把所有数调成大根堆
 * 假设一个一个数给你
 * 从上往下
 * <p>
 * 2 建堆二
 * 数组Array是一次全部给你了
 * 从下往上
 * <p>
 * <p>
 * 最大值在0位置跟N-1位置的数交换(最大值到N-1), 堆大小减1, 0位置做 heapify
 * <p>
 * 0~N-2位置是大根堆, 再0~N-3, 一直到heapsize减到0, 整个数组全有序了
 * 0位置的数来到最后堆大小减少，重新调堆，再把0位置的就来到这个范围上最后,
 * 堆大小减少，重新调堆。
 *
 * @author wlkq
 * @date 2023-03-21 9:02
 */


//复杂度

public class 堆排序 {

    private static void insert(int index, int[] heap) {
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    private static void heapify(int index, int[] heap, int size) {

        int l = 2 * index + 1;
        while (l < size) {
            int max = l + 1 < size && heap[l + 1] > heap[l] ? l + 1 : l;
            max = heap[max] < heap[index] ? index : max;

            if (max == index) {
                return;
            }

            swap(heap, max, index);
            index = max;
            l = 2 * index + 1;


        }


    }

    public static void sort(int[] arr) {

        int N = arr.length;

        if (arr == null || N < 2) {
            return;
        }


//        for (int i = 0; i < N; i++) {
//            insert(i, arr);
//        }


        for (int i = N - 1; i >= 0; i--) {
            heapify(i, arr, N);
        }

        swap(arr, 0, N - 1);


        while (N > 1) {
            heapify(0, arr, --N);
            swap(arr, 0, N - 1);
        }


    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    public static void main(String[] args) {
        int testCount = 500000;
        int maxSize = 100;
        int maxValue = 100;


        for (int i = 0; i < testCount; i++) {

            int[] ints = generateArr(maxSize, maxValue);
            int[] tar = Arrays.copyOf(ints, ints.length);
            int[] ints1 = Arrays.copyOf(ints, ints.length);


            test(ints);
            sort(ints1);

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

    @Test
    public void test() {

        for (int i = 0; i < 1000; i++) {
            System.out.println(getRandomNumInRange(30, 10));
        }

        System.out.println(-1 / 2);
    }


    public static void test(int[] arr) {

        Arrays.sort(arr);

    }

    public static int[] generateArr(int maxSize, int maxValue) {
        int length = getRandomNum(maxSize);
        int[] arr1 = new int[length];


        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = getRandomNum(maxValue) - getRandomNum(maxValue);

        }

        return arr1;

    }

    public static int getRandomNum(int max) {
        return (int) (Math.random() * max + 1);
    }


    public static int getRandomNumInRange(int max, int min) {
        return (int) (min + (Math.random() * (max - min + 1)));
    }


}
