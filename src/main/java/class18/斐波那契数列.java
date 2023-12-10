package class18;

/**
 * @author gc
 * @date 2023-12-08 15:46
 */
public class 斐波那契数列 {

    public static int F(int i) {
        if (i == 1 || i == 2) {
            return 1;

        }
        return F(i - 1) + F(i - 2);

    }
}
