package class10;

import java.util.Stack;

public class 基础遍历 {


    //递归序
    public static void f(treeNode head) {

        if (head == null) {
            return;
        }
        System.out.println(head.value);

        f(head.left);
        System.out.println(head.value);

        f(head.right);
        System.out.println(head.value);

    }


    public static void f1(treeNode head) {

        if (head == null) {
            return;
        }
//        先序
//        System.out.println(head.value);

        f1(head.left);

//        中序
        System.out.println(head.value);

        f1(head.right);

//        后序
//        System.out.println(head.value);


    }

    //先序非递归
    public static void noRecursion(treeNode head) {

        if (head!=null){//边界条件
            Stack<treeNode> stack = new Stack<>();
            stack.push(head);

            while (!stack.isEmpty()) {
                treeNode cur = stack.pop();
                System.out.println(cur.value);


                if (cur.right != null) {
                    stack.push(cur.right);

                }

                if (cur.left != null) {
                    stack.push(cur.left);
                }

            }
        }

    }


    //    后续遍历非递归
    public static void noRecursion2(treeNode head) {

        if(head!=null){
            Stack<treeNode> stack = new Stack<>();
            Stack<treeNode> help = new Stack<>();
            stack.push(head);

            while (!stack.isEmpty()) {
                treeNode cur = stack.pop();
                help.push(cur);

                // 头 右 左
                if (cur.left != null) {
                    stack.push(cur.left);
                }

                if (cur.right != null) {
                    stack.push(cur.right);

                }


            }
            //左 右 头
            while (!help.isEmpty()) {
                System.out.println(help.pop().value);
            }
        }

    }

    public static void noRecursion3(treeNode head){

        if (head!=null){
            Stack<treeNode> stack=new Stack<>();

            while (!stack.isEmpty()|| head!=null){//栈为空或者弹出的节点已经没有右节点时结束
                if(head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head=head.right;
                }
            }
        }





    }



    public static void main(String[] args) {
        treeNode head = new treeNode(1);
        head.left = new treeNode(2);
        head.right = new treeNode(3);
        head.left.left = new treeNode(4);
        head.left.right = new treeNode(5);
        head.right.left = new treeNode(6);
        head.right.right = new treeNode(7);

        noRecursion3(head);
        System.out.println("========");
        f1(head);
        System.out.println("========");


    }


}
