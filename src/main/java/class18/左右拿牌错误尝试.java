package class18;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gc
 * @date 2023-12-03 16:22
 *
 * 题意理解都有问题
 */
public class 左右拿牌错误尝试 {

    public static int win(Deque<Integer> cards) {
        int ans = 0;
        process(cards, 0, 0, true, ans);

        return ans;
    }

    public static void process(Deque<Integer> cards, int a, int b, boolean isA, int ans) {
        if (cards.size() == 0) {
            ans = a > b ? a : b;
            return;
        }

        if (isA) {
            Integer first = cards.removeFirst();
            process(cards, a + first, b, false, ans);
            cards.addLast(first);

            Integer last = cards.removeLast();
            process(cards, a + last, b, false, ans);
            cards.addLast(last);
        }

        Integer first = cards.removeFirst();
        process(cards, a, b + first, true, ans);
        cards.addLast(first);

        Integer last = cards.removeLast();
        process(cards, a, b + last, true, ans);
        cards.addLast(last);

    }


    // 根据规则，返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    // // arr[L..R]，后手获得的最好分数返回
    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
        return Math.min(p1, p2);
    }


//    public static void main(String[] args) {
//        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
//        Integer[] arr2 = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
//        Deque<Integer> integers = new LinkedList<>();
//        Collections.addAll(integers,arr2);
//        System.out.println(win1(arr));
//        System.out.println(win(integers));
//
//
//    }

    public static class Person {
        private String name;
        // 省略构造函数、Getter&Setter方法

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");
        swap(xiaoZhang, xiaoLi);
        System.out.println("xiaoZhang:" + xiaoZhang.getName());
        System.out.println("xiaoLi:" + xiaoLi.getName());
    }

    public static void swap(Person person1, Person person2) {
        String temp = person1.name;
        person1.name = person2.name;
        person2.name = temp;
        System.out.println("person1:" + person1.getName());
        System.out.println("person2:" + person2.getName());
    }


}
