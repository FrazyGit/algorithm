package class17;

/**
 * @author gc
 * @date 2023-12-02 9:20
 */
public class 汉诺塔 {

    /**
     * N个汉诺塔步骤抽象
     *
     * @param N
     */

    public static void leftToMiddle(int N) {
        if (N == 1) {
            System.out.println("Move 1 from left to middle");
            return;
        }
        leftToRight(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from left to middle");
        rightToMiddle(N - 1);

    }

    private static void rightToMiddle(int N) {
        if (N == 1) {
            System.out.println("Move 1 from right to middle");
            return;
        }
        rightToLeft(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from right to middle");
        leftToMiddle(N - 1);
    }

    private static void rightToLeft(int N) {
        if (N == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMiddle(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from right to left");
        middleToLeft(N - 1);
    }

    private static void middleToRight(int N) {
        if (N == 1) {
            System.out.println("Move 1 from middle to right");
            return;
        }
        middleToLeft(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from middle to right");
        leftToRight(N - 1);
    }

    private static void middleToLeft(int N) {
        if (N == 1) {
            System.out.println("Move 1 from middle to left");
            return;
        }
        middleToRight(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from middle to left");
        rightToLeft(N - 1);
    }

    private static void leftToRight(int N) {
        if (N == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMiddle(N - 1);
        //上面移开之后走
        System.out.println("Move " + N + " from left to right");
        middleToRight(N - 1);
    }


    /**
     * 增加参数抽象左中右
     *
     * @param N
     * @param from
     * @param to
     * @param other
     */
    public static void move(int N, String from, String to, String other) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
            return;
        }
        move(N - 1, from, other, to);
        System.out.println("Move " + N + " from " + from + " to " + to);
        move(N - 1, other, to, from);

    }


    public static void main(String[] args) {
        int n = 3;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
        System.out.println("============");
//		hanoi3(n);
    }

    private static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void hanoi2(int n) {
        move(n, "left", "right", "middle");
    }
}
