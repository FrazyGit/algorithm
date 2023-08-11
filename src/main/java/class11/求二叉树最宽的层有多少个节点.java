package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 求二叉树最宽的层有多少个节点 {

    public static Integer getMaxLevelWithMap(TreeNode root) {

        Integer max = 0;
        Map<TreeNode, Integer> map = new HashMap<>();

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> help = new LinkedList<>();
        map.put(root, 1);
        help.add(root);

        Integer curLevel = 1;
        Integer curLevelMax = 0;

        while (!help.isEmpty()) {
            TreeNode poll = help.poll();

            Integer level = map.get(poll);

            if (poll.left != null) {
                help.add(poll.left);
                map.put(poll.left, level + 1);
            }

            if (poll.right != null) {
                help.add(poll.right);
                map.put(poll.right, level + 1);
            }

            if (level == curLevel) {
                curLevelMax++;
            } else {
                max = Math.max(max, curLevelMax);
                curLevel++;
                curLevelMax = 1;
            }


        }
        //有新层的时候, 才去更新上一层的结果
        //这样最后一层没有新层到来去处理
        max = Math.max(max, curLevelMax);

        return max;


    }

    public static Integer getMaxLevelWithNoMap(TreeNode root) {

        if (root == null) {
            return 0;
        }


        Queue<TreeNode> help = new LinkedList<>();
        help.add(root);

        TreeNode curEnd = root;
        TreeNode nextEnd = null;

        int max = 0;
        int curNumber = 0;

        while (!help.isEmpty()) {
            TreeNode cur = help.poll();

            if (cur.left != null) {
                help.add(cur.left);
                nextEnd = cur.left;
            }

            if (cur.right != null) {
                help.add(cur.right);
                nextEnd = cur.right;
            }

//            if (cur != curEnd) {
//                curNumber++;
//            } else {
//                curNumber++;
//                max = Math.max(curNumber, max);
//                curNumber = 0;
//                curEnd = nextEnd;
//            }
//          下面是上述代码的优化
            curNumber++;
            if (cur == curEnd) {
                max = Math.max(curNumber, max); //最后一层也算了
                curNumber = 0;
                curEnd = nextEnd;
            }
        }


        return max;


    }


    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (getMaxLevelWithMap(head) != getMaxLevelWithNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
