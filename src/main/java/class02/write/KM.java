package class02.write;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 难
 * <p>
 * 一个数组中有一种数出现K次，其他数都出现了M次，
 * M > 1, K < M(防止为倍数)
 * 找到，出现了K次的数，
 * 要求，额外空间复杂度O(1)，时间复杂度O(N)
 *
 * @author wlkq
 * @date 2023-03-16 9:33
 */
public class KM {

    public static int km(int[] arr, int k, int m) {
        int temp = 0;
        int[] help = new int[32];


        //装填数据
        for (int i : arr) {
            int[] print = print(i);
            for (int j = 0; j < 32; j++) {
                help[j] += print[j];
            }

        }


        //装填答案
        int res = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % m == k) {
                res = res ^ 1 << (31 - i);
            }
        }

        return res;


    }
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = k;
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // 为了测试
    // [-range, +range]
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    public static int[] print(int num) {
        int help[] = new int[32];
        for (int i = 31; i >= 0; i--) {
            if ((num >>> i & 1) == 1) {
                help[31 - i] = 1;
            }
        }
        return help;
    }

    public static void main(String[] args) {
        int kinds = 100;
        int range = 300;
        int testTime = 100000;
        int max = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = km(arr, k, m);
            if (ans1 != ans2) {

                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                System.out.println(m);
                return;
            }
        }
        System.out.println("测试结束");
    }


}
