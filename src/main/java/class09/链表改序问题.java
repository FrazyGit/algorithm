package class09;

import class03.write.W01.Node;

/**
 * 给你链表头结点H, 链表长度为偶数, 比如长度为8, 规定前4个节点编号 L1, L2, L3, L4,
 * 后四个节点编号R1, R2, R3, R4, 请把把链表调整为:L1->R4->L2->R3->L3->R2->L4->R1的顺序
 *
 * 用回文思路
 *
 * @author wlkq
 * @date 2023-03-29 19:05
 */
public class 链表改序问题 {


    public static Node process(Node head) {


        //三个变量一直在复用
        Node node1 = head; //slow
        Node node2 = head; //fast

        while (node2.next != null && node2.next.next != null) {
            node1 = node1.next;
            node2 = node2.next.next;
        }

        node2 = node1.next;
        node1.next = null;

        Node node3 = null;
        while (node2 != null) {
            node3 = node2.next; //next
            node2.next = node1;
            node1 = node2; //pre
            node2 = node3; //head

        }

        node2 = head;// 左边头

        while (node2 != null && node1 != null) {
            Node next = node2.next;
            node2.next = node1;
            node2 = next;

            next = node1.next;
            node1.next = node2;
            node1 = next;

        }

        return head;

    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node process = process(test);

        printLinkedList(process);

    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

}
