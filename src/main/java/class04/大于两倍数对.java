package class04;


/**
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * <p>
 * <p>
 * 右边
 * <p>
 *
 *     一个数右边有多少比他小
 *
 *
 * <p>
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 *
 * @author wlkq
 * @date 2023-03-13 10:58
 */
public class 大于两倍数对 {

// 算和填数无法在同一个for循环中进行 用窗口不回退的技巧 保持O(N)

    public static int merge(int arr[], int l, int m, int r) {

        int help[] = new int[r - l + 1];

        int p1 = l;
        int p2 = m + 1;
        int i = 0;

        int res = 0;
        int windowR = m + 1;

        for (int k = l; k <= m; k++) {
            while (windowR <= r && arr[k] > arr[windowR] * 2) {
                windowR++;
            }
            res += windowR - m - 1;

        }


        while (p1 <= m && p2 <= r) {

            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];

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

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int process = process(arr1, 0, arr1.length - 1);

            if (process != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
