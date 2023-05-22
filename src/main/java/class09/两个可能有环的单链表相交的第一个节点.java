package class09;


/**
 * 定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
 * 【要求】
 * 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
 *
 * @author wlkq
 * @date 2023-03-30 14:46
 */
public class 两个可能有环的单链表相交的第一个节点 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    //主函数
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node loopNode = getLoopNode(head1);
        Node loopNode1 = getLoopNode(head2);

        if (loopNode == null && loopNode1 == null) {
            return noLoop(head1, head2);
        } else if (loopNode != null && loopNode1 != null) {
            return bothLoop(head1, loopNode, head2, loopNode1);
        }

        return null;


    }


    //1
    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {

        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // n1 慢  n2 快
        Node slow = head.next; // n1 -> slow
        Node fast = head.next.next; // n2 -> fast

        while (fast != slow) {
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;

            } else {
                return null;
            }

        }

        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;


    }


    //2.1
    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public static Node noLoop(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }


        Integer n = 0;
        Node cur1 = head1;
        Node cur2 = head2;

        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }


        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);

        while (n > 0) {
            cur1 = cur1.next;
            n--;

        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;


    }

//   2.2 一个链表有环一个链表无环
//    这种情况下不可能相交


    // 2.3两个有环链表，返回第一个相交节点，如果不想交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {

        if (loop1 == loop2) {// 相交的点在入环节点之前--》 不管环 以无环节点相交做
            Integer n = 0;
            Node cur1 = head1;
            Node cur2 = head2;

            while (cur1.next != loop1) {
                n++;
                cur1 = cur1.next;
            }


            while (cur2.next != loop2) {
                n--;
                cur2 = cur2.next;
            }

            if (cur1 != cur2) {
                return null;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);

            while (n > 0) {
                cur1 = cur1.next;
                n--;

            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;


        } else {  // 1分别独立 2 入环节点不是一个

            Node cur = loop1.next;

            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }

            return null;

        }


    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value); //无环相交

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value); //入环节点前相交

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value); // 入环节点不一样

        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2)); // 不相交


    }


}
