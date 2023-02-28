package class02;

/**
 *
 * 关键 异或是无进位相加
 * @author wlkq
 * @date 2023-02-26 10:52
 */
public class 异或性质 {


    public static void main(String[] args) {

        int i=78;
        int j=88;


        print(1<<1);
        System.out.println(i ^ i);
        System.out.println(i ^ 0);


        print(i );
        print(i & (-i));




        i=i^j;
        j=i^j;
        i=i^j;

        System.out.println(i+" "+j);

    }

    public static void print(int num){
        for (int i=31;i>=0;i--){
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }


}
