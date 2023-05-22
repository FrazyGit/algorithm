package class03.write;



/**
 * @author wlkq
 * @date 2023-03-19 16:05
 */
public class 链表练习二 {

    public static DoubleNode reverse(DoubleNode head) {

        DoubleNode next = null;
        DoubleNode pre = null;


        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;


    }

    public static DoubleNode deleteNode(DoubleNode head, Integer num) {


        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }


        DoubleNode cur = head;
        DoubleNode pre = head;

        while (cur != null) {

            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;

        }


        return head;


    }

    public static void main(String[] args) {
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(5);
        DoubleNode node6 = new DoubleNode(6);
        DoubleNode node7 = new DoubleNode(6);
        DoubleNode node8 = new DoubleNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;


        DoubleNode reverse = reverse(node1);

//        print(reverse);

        DoubleNode node = deleteNode(reverse, 6);

        print(node);

    }

    private static void print(DoubleNode current) {
        if (current == null) {
            return;
        }
        System.out.println(current.value);
        print(current.next);
    }


}
