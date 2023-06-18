package class03.write.W02;

public class 基础练习 {

    public static Node reverse(Node head) {

        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }

        return pre;


    }

    public static Node deleteNode(Node head, Integer num) {

        while (head.getValue() == num) {


            if (head == null) {
                break;
            }
            head = head.next;
        }

        Node res = head;
        Node pre = head;
        while (head != null) {
            if (head.getValue() == num) {
                pre.next = head.next;
            } else {
                pre = head;

            }

            head = head.next;

        }


        return res;


    }


    public static DoubleNode reverseDouble(DoubleNode head) {

        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head.pre = next;
            head = next;

        }

        return pre;

    }


    public static void main(String[] args) {
        Node node = new Node(2);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5 = new Node(5);
        Node node6 = new Node(2);

        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;


//        Node node1 = reverse(node);

        Node node1 = deleteNode(node, 2);

        print(node1);

//        DoubleNode node11 = new DoubleNode(1);
//        DoubleNode node12 = new DoubleNode(2);
//        DoubleNode node13 = new DoubleNode(3);
//        DoubleNode node14 = new DoubleNode(4);
//        DoubleNode node15 = new DoubleNode(5);
//        DoubleNode node16 = new DoubleNode(6);
//        DoubleNode node17 = new DoubleNode(6);
//        DoubleNode node18 = new DoubleNode(6);
//
//        node11.next = node12;
//        node12.next = node13;
//        node13.next = node14;
//        node14.next = node15;
//        node15.next = node16;
//        node16.next = node17;
//        node17.next = node18;
//
//        DoubleNode node6 = reverseDouble(node11);
//
//        print2(node6);
////       print3(node11);


    }

    private static void print(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        print(current.next);
    }

    private static void print2(DoubleNode current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        print2(current.next);
    }

    private static void print3(DoubleNode current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        print3(current.pre);
    }


}
