package class12;


/**
 * 在一个二叉树中, 每一颗子树, 左树的高度和右树的高度差不超过1
 * 以X为头的树, 要满足平衡性, 要满足以下条件:
 *
 * 左树是平衡二叉树
 * 右树是平衡二叉树
 * 左树和右树的高度差不要超过1
 */
public class 判断平衡二叉树 {


    public static class Info {
        public boolean isBalanced;
        public Integer height;

        public Info(boolean isBalanced, Integer height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }


    public static Boolean isBalanced2(Node head) {

        if (head == null) {
            return true;
        }

        return process(head).isBalanced;

    }


    public static Info process(Node x) {

        if (x == null) {
            return new Info(true, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;

        if (!leftInfo.isBalanced || !rightInfo.isBalanced) {
            isBalanced = false;
        } else if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }

        return new Info(isBalanced, height);

    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
