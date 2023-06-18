package class01.二分法;

import java.util.Arrays;

/**
 *
 * 难
 *
 *
 * 在一个无序数组中, 值有可能正, 负, 或者零, 数组中任由两个相邻的数一定不相等.
 * 定义局部最小:
 * 1.长度为1，arr[0]就是局部最小；
 * 2.数组的开头，如果arr[0] < arr[1] ，arr[0]被定义为局部最小。
 * 3.数组的结尾，如果arr[N-1] < arr[N-2] ，arr[N-1]被定义为局部最小。
 * 任何一个中间位置i, 即数组下标1~N-2之间, 必须满足arr[i-1] < arr[i] <arr[i+1] ,叫找到一个局部最小。
 * 请找到任意一个局部最小并返回。
 *
 * @author wlkq
 * @date 2023-02-25 15:20
 */
public class min {

    public static int getMin(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {//一开始就去除了标准版r越界的可能
            return 0;
        }

        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }


        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int min = l + (r - l) / 2;
            if (arr[min] < arr[min + 1] && arr[min] < arr[min - 1]) {
                return min;
            }

            if (arr[min] > arr[min + 1]) {
                l = min + 1;

            }else {
                r = min - 1;
            }




        }
        System.out.println(r);
        System.out.println(l);

        return r;//出while的情况是l=r 随便选一个是


    }


    // 验证得到的结果，是不是局部最小  思路打开不一定要两种方法 只要能验证答案是对的就行
    public static boolean test(int[] arr,int index) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];


    }

    public static int[] generateArr(int maxSize, int maxValue) {
        int length = (int) (Math.random() * maxSize + 1);
        int[] arr1 = new int[length];


        arr1[0] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);

        for (int i = 1; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
            while (arr1[i]==arr1[i-1]){
                arr1[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);

            }

        }

        return arr1;

    }

    public static void main(String[] args) {
        int testCount = 500000;
        int maxSize = 1000;
        int maxValue = 1000;


        for (int i = 0; i < testCount; i++) {

            int[] ints = generateArr(maxSize, maxValue);


            if (test(ints,getMin(ints))) {
                System.out.println("nice");
            } else {
                System.out.println("no");
                System.out.println(Arrays.toString(ints));
                System.out.println(getMin(ints));
                return;
            }
        }

    }




}
