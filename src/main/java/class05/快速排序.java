package class05;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 在arr[L..R]范围上，进行快速排序的过程：
 * 1）在这个范围上，随机选一个数记为num，
 * 2）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。
 * 假设== num的数所在范围是[a,b]
 * 3）对arr[L..a-1]进行快速排序(递归)
 * 4）对arr[b+1..R]进行快速排序(递归)
 *
 * @author wlkq
 * @date 2023-03-19 16:20
 */
public class 快速排序 {


    public static int[] partition(int[] arr, int l, int r) {

        if (l > r) {
            return new int[]{-1, -1};
        }

        if (l == r) {
            return new int[]{l, l};
        }


        int index = l;
        int left = l - 1;
        int right = r;

        while (index < right) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --right);
            } else {
                swap(arr, index++, ++left);
            }
        }

        swap(arr, r, right++);

        return new int[]{left + 1, right - 1};


    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    public static void process(int[] arr, int l, int r) {


        if (l >= r) {//边界
            return;
        }


        int[] flag = partition(arr, l, r);

        process(arr, l, flag[0] - 1);//当flag返回[0,0]时，r为复数
        process(arr, flag[1] + 1, r);//最后一个数最大时flag[1] + 1>r


    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);

    }

    public static class Help {

        public Integer l;
        public Integer r;

        public Help(Integer l, Integer r) {
            this.l = l;
            this.r = r;
        }
    }


    //非递归版
    public static void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;

        swap(arr, (int) (Math.random() * N), N-1);//随机一个数当目标数

        int[] flag = partition(arr, 0, N-1);

        int al = flag[0];
        int ar = flag[1];


        Stack myStack = new Stack();

        myStack.push(new Help(0, al - 1));
        myStack.push(new Help(ar + 1, N-1));

        while (!myStack.isEmpty()) {
            Help pop = (Help) myStack.pop();

            if (pop.l < pop.r) {//边界

                swap(arr,pop.r,getRandomNumInRange(pop.r, pop.l));//随机一个数当目标数
                int[] flag1 = partition(arr, pop.l, pop.r);

                myStack.push(new Help(pop.l, flag1[0] - 1));
                myStack.push(new Help(flag1[1] + 1, pop.r));


            }


        }


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
            sort2(ints1);

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
    public void test(){

        for (int i=0;i<1000;i++) {
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


    public static int getRandomNumInRange(int max,int min) {
        return (int) (min+(Math.random() * (max-min + 1)));
    }

}
