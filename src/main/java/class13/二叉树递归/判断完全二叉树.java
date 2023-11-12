package class13.二叉树递归;


import class12.Node;

import java.util.LinkedList;

/**
 * 左满 右满 高度一样
 * 左完全 右满 高度左大一
 * 左满 右完全 高度一样
 */


public class 判断完全二叉树 {

    public static class Info {
        boolean isFull;
        boolean isCBT;
        int height;


        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }


    public static boolean isCBT2(Node head) {

        if (head == null) {
            return true;
        }

        return process(head).isCBT;

    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(true, true, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = true;

        if (!leftInfo.isFull || !rightInfo.isFull) {
            isFull = false;
        } else if (rightInfo.height!= leftInfo.height) {
            isFull = false;

        }

        boolean isCBT = false;

        if (rightInfo.isFull && leftInfo.isFull && rightInfo.height== leftInfo.height) {
            isCBT = true;
        } else if (rightInfo.isFull && leftInfo.isCBT && rightInfo.height+1 == leftInfo.height ) {
            isCBT = true;
        } else if (rightInfo.isCBT && leftInfo.isFull && rightInfo.height == leftInfo.height) {
            isCBT = true;

        }

        return new Info(isFull, isCBT, height);

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
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null))
                            ||
                            (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

}



