package class12;


//1 左数满 右数满 左右高度一样
//2 根据节点数量来判断 只有满二叉树满足 : 2 ^ h - 1 == n
public class 判断满二叉树 {

    // 1 左数满 右数满 左右高度一样
    public static class Info1 {
        int height;
        boolean isFull;

        public Info1(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }


    public static boolean isFull1(Node head) {

        if (head == null) {
            return true;
        }

        return process1(head).isFull;


    }

    public static Info1 process1(Node x) {
        if (x == null) {
            return new Info1(0, true);
        }

        Info1 leftInfo = process1(x.left);
        Info1 rightInfo = process1(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = true;

        if (!leftInfo.isFull || !rightInfo.isFull) {
            isFull = false;
        } else if (rightInfo.height!= leftInfo.height) {
            isFull = false;
        }

        return new Info1(height, isFull);


    }

    //2 根据节点数量来判断 只有满二叉树满足 : 2 ^ h - 1 == n
    public static class Info2 {
        int height;
        int nodes;

        public Info2(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }


    }


    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }

        Info2 all = process2(head);

        return Math.pow(2, all.height) - 1 == all.nodes;

//        方法只需要跑一遍
//        return Math.pow(2, process2(head).height) - 1 == process2(head).nodes;

    }

    public static Info2 process2(Node x) {
        if (x == null) {
            return new Info2(0, 0);
        }
        Info2 leftInfo = process2(x.left);
        Info2 rightInfo = process2(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = rightInfo.nodes + leftInfo.nodes + 1;

        return new Info2(height, nodes);

    }


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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }


}
