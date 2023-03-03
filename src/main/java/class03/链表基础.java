package class03;

import javafx.beans.binding.When;

/**
 * @author wlkq
 * @date 2023-02-28 14:03
 */
public class 链表基础 {

    public static Node reverseNode(Node head) {

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

    public static DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;

    }


    private static void print(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.value);
        print(current.next);
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(1);

        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next=node5;

        Node delete = delete(node, 2);
        print(delete);

        Node node1 = reverseNode(node);

        print(node1);


    }


    //    删除链表中指定的值
    public static Node delete(Node head, int num) {

        while (head != null) {
            if ((int)head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;


        while (cur != null) {
            if ((int)cur.value == num) {
                pre.next = cur.next;

            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;


    }


}
