package class12;

import java.util.ArrayList;


/**
 * 1) X左树是BST
 * 2) X右树是BST
 * 3) X左树最大值max<X
 * 4) X右树最小值min > X
 */
public class 判断搜索二叉树 {

    public static class Info {
        public boolean isBst;
        public int max;
        public int min;

        public Info(boolean isBst, int max, int min) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }
    }


    public static boolean isBst2(Node head) {

        if (head == null) {
            return true;
        }

        return process(head).isBst;

    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);


        int max = x.value;//默认设置

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);//如果左树不为空,看看是否能将Max从默认值推高一点
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
        }

        int min = x.value;

        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);//如果左树不为空,看看是否能将Max从默认值推高一点
        }

        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
        }

        boolean isBst = true;

        if (rightInfo != null && !rightInfo.isBst) {
            isBst = false;
        }

        if (leftInfo != null && !leftInfo.isBst) {
            isBst = false;
        }

        if (rightInfo != null && rightInfo.min <= x.value) { //当前的BTS模式是不认为有相同的节点
            isBst = false;
        }

        if (leftInfo != null && leftInfo.max >= x.value) {
            isBst = false;
        }

        return new Info(isBst, max, min);


    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBst2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
