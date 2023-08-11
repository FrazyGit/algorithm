package class11;

public class 纸带打印 {


    // 当前你来了一个节点，脑海中想象的！
    // 这个节点在第i层，一共有N层，N固定不变的
    // 这个节点如果是凹的话，down = T
    // 这个节点如果是凸的话，down = F
    // 函数的功能：中序打印以你想象的节点为头的整棵树！
    //所有左子树头凹
    //所有右子树头凸

    public static void printNode(int N,int i,boolean down){

        if (i>N){ //bc
            return;
        }

        printNode(N,i+1,true);
        System.out.print(down ? "凹 " : "凸 ");//中序
        printNode(N,i+1,false);

    }

    public static void main(String[] args) {
        int N=3;
        printNode(N,1,true);
    }
}
