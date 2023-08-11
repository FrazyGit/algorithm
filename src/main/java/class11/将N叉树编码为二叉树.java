package class11;

import java.util.ArrayList;
import java.util.List;


//线上测试
public class 将N叉树编码为二叉树 {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    //多叉转二叉
    public static TreeNode enCode(Node head) {

        if (head == null) {
            return null;
        }

        TreeNode treeNode = new TreeNode(head.val);

        treeNode.left = en(head.children);

        return treeNode;


    }

    private static TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;

        for (Node ch : children) {
            TreeNode treeNode = new TreeNode(ch.val);
            if (head == null) {
                head = treeNode;
            } else {
                cur.right = treeNode;
            }

            cur = treeNode;
            cur.left = en(ch.children);


        }
        return head;


    }

    //二叉转多叉
    public static Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }

        return new Node(root.val, de(root.left));
    }

    private static List<Node> de(TreeNode left) {
        List<Node> children = new ArrayList<>();

        while (left != null) {
            Node node = new Node(left.val, de(left.left));
            children.add(node);
            left = left.right;
        }


        return children;
    }


}
