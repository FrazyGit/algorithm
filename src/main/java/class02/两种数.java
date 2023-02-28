package class02;

/**
 * @author wlkq
 * @date 2023-02-26 16:14
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
 */
public class 两种数 {


    public static void search(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }


        int eor = 0;
        for (int i = 0; i < arr.length; i++) {

            eor = eor ^ arr[i];

        }

        int b = 0;
        int a = eor & (-eor);
        for (int i = 0; i < arr.length; i++) {

            if ((arr[i] & a) != 0) {
                b = b ^ arr[i];

            }

        }

        int c = b ^ eor;

        System.out.println(b + "  " + c);


    }

//    public static int[] generateArr(int maxSize, int maxValue) {
//
//    int []arr=new int [(maxSize-2)*7+2*4];
//
//      int a=  (int) (Math.random() * maxValue + 1);
//      int b=(int) (Math.random() * maxValue + 1);
//     while (b==a){
//         b=(int) (Math.random() * maxValue + 1);
//     }
//
//     for (int i=0;i<4;i++){
//
//     }


    public static void main(String[] args) {
        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2, 2, 5};
        search(arr2);
    }


}
