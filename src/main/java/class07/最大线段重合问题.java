package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数组[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * <p>
 * <p>
 * <p>
 * <p>
 * 当把线段开始位置 由小到大拍完序之后, 依次考察每一个线段
 * 遍历到任何一个线段时, 在小根堆里把所有<=开始位置的值都弹出,
 * 然后把此时结束位置加入小根堆, 此时小跟堆里有几个数, 就是这线段的答案
 * 所有线段的答案都求出来, 最大的那个就是最大线段重合数
 * <p>
 * 任何一个重合区域, 重合区域的左边界必是某个线段的左边界
 *
 *
 *
 */
public class 最大线段重合问题 {

    public static int Max(int[][] m) {

        Line[] lines = new Line[m.length];

        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);

        }
        Arrays.sort(lines, new MyComparator());

        int max = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < lines.length; i++) {

            while (!priorityQueue.isEmpty()&&priorityQueue.peek() <= lines[i].start  ) {
                priorityQueue.poll();

            }
            priorityQueue.add(lines[i].end);
            max = Math.max(max, priorityQueue.size());

        }

        return max;


    }

    public static void main(String[] args) {
//
//        Line l1 = new Line(4, 9);
//        Line l2 = new Line(1, 4);
//        Line l3 = new Line(7, 15);
//        Line l4 = new Line(2, 4);
//        Line l5 = new Line(4, 6);
//        Line l6 = new Line(3, 7);
//
//        // 底层堆结构，heap
//        PriorityQueue<Line> heap = new PriorityQueue<>(new MyComparator());
//        heap.add(l1);
//        heap.add(l2);
//        heap.add(l3);
//        heap.add(l4);
//        heap.add(l5);
//        heap.add(l6);
//
//        while (!heap.isEmpty()) {
//            Line cur = heap.poll();
//            System.out.println(cur.start + "," + cur.end);
//        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = Max(lines);
            if (ans1 != ans2 ) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static class MyComparator implements Comparator<Line> {


        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class Line {

        Integer start;
        Integer end;

        public Line(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }


}
