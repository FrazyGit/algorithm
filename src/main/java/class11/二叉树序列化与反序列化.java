package class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 二叉树序列化与反序列化 {


    //前序
    public static Queue<String> preSerial(TreeNode head) {

        Queue<String> rs = new LinkedList<>();


        sPre(head, rs);
        return rs;


    }


    public static void sPre(TreeNode node, Queue<String> rs) {

        if (node == null) {
            rs.add(null);

        } else {
            rs.add(String.valueOf(node.value));
            sPre(node.left, rs);
            sPre(node.right, rs);

        }


    }

    public static TreeNode buildByPre(Queue<String> tr) {
        if (tr == null || tr.isEmpty()) {
            return null;
        }

        return bPre(tr);


    }

    public static TreeNode bPre(Queue<String> tr) {
        String poll = tr.poll();
        if (poll == null) {
            return null;
        }

        TreeNode head = new TreeNode(Integer.valueOf(poll));
        head.left = bPre(tr);
        head.right = bPre(tr);

        return head;


    }


    //后序
    public static Queue<String> posSerial(TreeNode head) {

        Queue<String> rs = new LinkedList<>();


        sPos(head, rs);
        return rs;


    }


    public static void sPos(TreeNode node, Queue<String> rs) {

        if (node == null) {
            rs.add(null);

        } else {
            sPos(node.left, rs);
            sPos(node.right, rs);
            rs.add(String.valueOf(node.value));

        }


    }

    public static TreeNode buildByPos(Queue<String> tr) {
        if (tr == null || tr.isEmpty()) {
            return null;
        }

        // 左右中  ->  stack(中右左) 必须要先建立头节点下面才能串起来
        Stack<String> stack = new Stack<>();

        while (!tr.isEmpty()) {
            stack.push(tr.poll());
        }

        return bPoss(stack);


    }

    public static TreeNode bPoss(Stack<String> tr) {
        String poll = tr.pop();
        if (poll == null) {
            return null;
        }
//        head.left=bPoss(tr);
//        head.right=bPoss(tr);
//        TreeNode<Integer> head=new TreeNode(Integer.valueOf(poll));

        TreeNode head = new TreeNode(Integer.valueOf(poll));
        head.right = bPoss(tr);
        head.left = bPoss(tr);


        return head;

    }


    //按层遍历
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> res = new LinkedList<>();

        if (head == null) {
            res.add(null);
        } else {
            res.add(String.valueOf(head.value));
            Queue<TreeNode> help = new LinkedList<>();
            help.add(head);
            while (!help.isEmpty()) {
                TreeNode poll = help.poll();

                if (poll.left != null) {
                    res.add(String.valueOf(poll.left.value));
                    help.add(poll.left);
                } else {
                    res.add(null);
                }

                if (poll.right != null) {
                    res.add(String.valueOf(poll.right.value));
                    help.add(poll.right);
                } else {
                    res.add(null);

                }

            }

        }

        return res;


    }

    public static TreeNode buildByLevel(Queue<String> tr) {
        if (tr == null || tr.size()==0) {
            return null;
        }


        TreeNode<Integer> head = genNode(tr.poll());
        Queue<TreeNode> help = new LinkedList<>();

        if (head != null) {
            help.add(head);
        }

        while (!help.isEmpty()) {
            TreeNode cur = help.poll();
            cur.left = genNode(tr.poll());
            cur.right = genNode(tr.poll());

            if (cur.left != null) {
                help.add(cur.left);
            }

            if (cur.right != null) {
                help.add(cur.right);
            }
        }

        return head;




    }

    public static TreeNode genNode(String val) {
        if (val == null) {
            return null; //应该是空 而不是挂一个值为空的节点
        }

        return new TreeNode(Integer.valueOf(val));//要求与生成二叉树值的类型一致
    }


    // for test
    // 以下两个方法是随机生成二叉树

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

    // for test
    // 要求二叉树值的类型一致
    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    //以下三个方法是打印是打印二叉树
    // for test
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    //测试逻辑 一棵树 与序列化后再反序列化后出来结果是否一样
    public static void main(String[] args) {
        int maxLevel = 6;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            TreeNode preBuild = buildByPre(pre);
            TreeNode posBuild = buildByPos(pos);
            TreeNode levelBuild = buildByLevel(level);
            if (!isSameValueStructure(levelBuild, head) ||!isSameValueStructure(preBuild, head) || !isSameValueStructure(posBuild, head) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
//                printTree(levelBuild);
//                printTree(head);

                return;


            }
        }
        System.out.println("test finish!");

    }


}
