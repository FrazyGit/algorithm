package class10;

import java.util.Stack;

public class 基础遍历 {


    //递归序
    public static void f(TreeNode head) {

        if (head == null) {
            return;
        }
        System.out.println(head.value);

        f(head.left);
        System.out.println(head.value);

        f(head.right);
        System.out.println(head.value);

    }


    public static void f1(TreeNode head) {

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
    public static void noRecursion(TreeNode head) {

        if (head!=null){//边界条件
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);

            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
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
    public static void noRecursion2(TreeNode head) {

        if(head!=null){
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> help = new Stack<>();
            stack.push(head);

            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
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

    // 中续遍历非递归
    public static void noRecursion3(TreeNode head){

        if (head!=null){
            Stack<TreeNode> stack=new Stack<>();

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
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        noRecursion3(head);
        System.out.println("========");
        f1(head);
        System.out.println("========");


    }


}
