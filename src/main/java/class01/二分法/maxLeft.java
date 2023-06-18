package class01.二分法;

import java.util.Arrays;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 *
 * @author wlkq
 * @date 2023-02-25 14:08
 */
public class maxLeft {

    public static int search(int[] arr, int num) {

        int res = -1;
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {

            int min = l + (r - l) / 2;

            if (arr[min] >= num) {
                res=min;
                r = min - 1; //一直检查到结束
            } else {
                l = min + 1;
            }


        }
//        l < r时还剩一个数没检查
//        if (arr[l]>=num){
//            res=l;
//        }

        return res;


    }

    public static int test (int[] arr, int num){

        int res =-1;

        for (int i=arr.length-1;i>=0;i--){
            if (arr[i]>=num){
                res=i;
            }
        }

        return res;

    }


    public static void main(String[] args) {
        int testCount = 50000;
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
                System.out.println(test(ints, value));
                return;
            }
        }

    }

    public static int[] generateArr(int maxSize, int maxValue) {
        int length = (int) (Math.random() * maxSize + 1);
        int[] arr1 = new int[length];


        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);

        }

        return arr1;

    }


}
