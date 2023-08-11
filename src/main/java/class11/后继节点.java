package class11;

public class 后继节点 {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSubsequent(Node x) {

        if (x==null){
            return null;
        }

        Node res = null;
        if (x.right != null) {
            res = x.right;
            while (res.left != null) {
                res = res.left;
            }

            return res;
        } else {
            if (x == x.parent.right) {
                res = x.parent;
                while (res.parent != null) {
                    if (res == res.parent.left) {
                        return res.parent;
                    }
                    res=res.parent;
                }

                return null;
            } else {
                return x.parent;
            }
        }

    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSubsequent(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSubsequent(test));
    }


}
