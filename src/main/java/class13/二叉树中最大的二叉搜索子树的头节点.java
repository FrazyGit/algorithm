package class13;

import class12.Node;

import java.util.ArrayList;

import static class12.打印二叉树.printTree;

public class 二叉树中最大的二叉搜索子树的头节点 {

    public static class Info {
        int max;
        int min;
        int size;
        int maxSubBstSize;

        Node maxHead;

        public Info(int max, int min, int size, int maxSubBstSize, Node maxHead) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.maxSubBstSize = maxSubBstSize;
            this.maxHead = maxHead;
        }
    }


    public static Node getMaxSubHead(Node head) {
        if (head == null) {
            return null;
        }

        return process(head).maxHead;

    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }


        Node maxHead=null;
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

        int p3 = -1;


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



      if (p1<p2){//左右相等时取右
          if (p2>p3){
              maxSubBstSize=p2;
              maxHead=right.maxHead;
          }else {
              maxSubBstSize=p3;
              maxHead=x;
          }
      }else {
          if (p1>p3){
              maxSubBstSize=p1;
              maxHead=left.maxHead;
          }else {
              maxSubBstSize=p3;
              maxHead=x;
          }

      }

        return new Info(max, min, size, maxSubBstSize,maxHead);


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

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

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

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node rs1 = maxSubBSTHead1(head);
            Node rs2 = getMaxSubHead(head);
            if ( rs1 != rs2) {
                System.out.println("Oops!");
                printTree(head);
                System.out.println(rs1.value);
                System.out.println(rs2.value);
            }
        }
        System.out.println("finish!");
    }

}
