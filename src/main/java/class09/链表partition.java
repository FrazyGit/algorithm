package class09;

import class03.write.W01.Node;


/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * @author wlkq
 * @date 2023-03-29 16:15
 */
public class 链表partition {

    public static Node partition1(Node head, int value) {


        if (head == null) {
            return null;
        }

        int i = 0;
        Node cur = head;
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        Node[] help = new Node[i];

        cur = head;

        for (int j = 0; j < help.length && cur != null; j++) {
            help[j] = cur;
            cur = cur.next;

        }

        flag(help, value);

        for (int j = 0; j < help.length - 1; j++) {
            help[j].next = help[j + 1];
        }

        help[help.length - 1].next = null;

        return help[0];


    }


    public static Node partition2(Node head, int value) {

        if (head == null) {
            return null;
        }

        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;

        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;

            if (head.value < value) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == value) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = mT.next;
                }
            }

            head = next;

        }


        // 情况多
        if (sT != null) {
            if (eT != null) {
                sT.next = eH;
                eT.next = mH;
            }else {
                sT.next=mH;
            }
            return sH;
        }

        if (eT != null){
            eT.next=mH;
            return eH;
        }

        return mH;


    }

    public static void flag(Node[] nodes, int value) {

        int l = -1;
        int r = nodes.length;
        int index = 0;

        while (index < r) {
            if (nodes[index].value == value) {
                index++;
            } else if (nodes[index].value < value) {
                swap(nodes, ++l, index++);
            } else {
                swap(nodes, --r, index);
            }
        }


    }

    public static void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;

    }


    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
//        Node partition = partition1(head1, 2);
        Node partition2 = partition2(head1,9 );
//        head1 = listPartition2(head1, 5);
        printLinkedList(partition2);

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
