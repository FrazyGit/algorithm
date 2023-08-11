package class11;

import java.util.LinkedList;
import java.util.Queue;

public class 按层遍历 {

    public static void byLevel(TreeNode head){

        if(head==null){
            return;
        }

        Queue<TreeNode> queue =new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();

            System.out.println(poll.value);

            if (poll.left!=null){
                queue.add(poll.left);
            }

            if (poll.right!=null){
                queue.add(poll.right);
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

        byLevel(head);
        System.out.println("========");



    }
}
