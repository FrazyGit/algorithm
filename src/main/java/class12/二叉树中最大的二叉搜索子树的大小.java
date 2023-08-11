package class12;

import java.util.ArrayList;

public class 二叉树中最大的二叉搜索子树的大小 {

    public static class Info {
        int max;
        int min;
        int size;
        int maxSubBstSize;

        public Info(int max, int min, int size, int maxSubBstSize) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.maxSubBstSize = maxSubBstSize;
        }
    }


    public static int getMaxSub(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head).maxSubBstSize;

    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }

        Info left = process(x.left);
        Info right = process(x.right);

        int max = x.value;
        int min = x.value;
        int size = 1;
        int maxSubBstSize = 0;

        int p1 = -1;
        int p2 = -1;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            size += left.size;
            p1 = left.maxSubBstSize;
        }

        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            size += right.size;
            p2 = right.maxSubBstSize;
        }

        int p3 = 0;


        //空值处理
        boolean leftBSt = left == null ? true : (left.maxSubBstSize == left.size);
        boolean rightBSt = right == null ? true : (right.maxSubBstSize == right.size);

        if (leftBSt && rightBSt) {
            boolean leftMaxLessX = left == null ? true : (left.max < x.value);
            boolean rightMinMoreX = right == null ? true : (right.min > x.value);
            if (leftMaxLessX && rightMinMoreX) {
                int lSize= left == null ? 0 : left.size;
                int rSize= right == null ? 0 : right.size;
                p3=lSize+rSize+1;
            }
        }

        maxSubBstSize = Math.max(Math.max(p1, p2), p3);



        return new Info(max, min, size, maxSubBstSize);


    }


    // 为了验证
    // 对数器方法
    public static int right(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (getMaxSub(head) != right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }



}
