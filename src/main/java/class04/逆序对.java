package class04;

/**
 * 一个数组中, 任何一个左边的数和任何右边的数，一旦降序就是逆序对 求有多少个
 * <p>
 * <p>
 * <p>
 * <p>
 * 1) 在一个for循环中解决 麻烦
 * <p>
 * 逆序：右到左 一个数右边有多少个数比它小  左组拷贝产生 相等动右组
 * 左到右 一个数左边有多少个数比它大  右组拷贝产生 相等动左组
 * 规律 找另一边比它小的数逆序 相等动拷贝不产生的组
 * <p>
 * 2) 算和填数无法在同一个for循环中进行
 * <p>
 * 用窗口不回退的技巧 保持O(N)
 *
 * @author wlkq
 * @date 2023-03-08 15:49
 */
public class 逆序对 {

    public static int process(int[] arr, int l, int r) {

        if (arr == null || arr.length < 1) {
            return 0;
        }


        if (l == r) {
            return 0;
        }

        int mid = l + (r - l) / 2;

        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);

    }


//    public static int merge(int[] arr, int l, int m, int r) {
//
//        int[] help = new int[r - l + 1];
//
//        int i = help.length - 1;
//        int p1 = m;
//        int p2 = r;
//
//        int res = 0;
//
//        while (p1 >= l && p2 >= m + 1) {
//
//            res += arr[p1] > arr[p2] ? ((p2 - (m + 1)) + 1) : 0;
//            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
//
//        }
//
//        while (p1 >= l) {
//            help[i--] = arr[p1--];
//        }
//
//        while (p2 >= m + 1) {
//            help[i--] = arr[p2--];
//        }
//
//
//        for (int j = 0; j < help.length; j++) {
//            arr[l + j] = help[j];
//
//        }
//        return res;
//
//    }

    public static int merge(int[] arr, int l, int mid, int r) {

        int[] help = new int[r - l + 1];

        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        int res = 0;
        int windowR = mid + 1;

        for (int k = l; k <= mid; k++) {
            while (windowR <= r && arr[k] > arr[windowR]) {
                windowR++;
            }
            res += windowR - mid - 1;

        }

        while (p1 <= mid && p2 <= r) {


            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];


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

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
        int testTime = 500000;
        int maxSize = 1000;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
//            printArray(arr1);
            if (process(arr1, 0, arr1.length - 1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
