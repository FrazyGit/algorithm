package class09;

import class03.write.Node;

import java.util.ArrayList;

/**
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * <p>
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * <p>
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * <p>
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 * @author wlkq
 * @date 2023-03-29 10:20
 */
public class 链表快慢指针 {

    public static Node getMid1(Node head) {

        if (head == null) {
            return head;
        }

        Node fast = head;
        Node slow = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;


    }


    public static Node getMid2(Node head) {

        if (head == null && head.getNext() != null) {
            return head;
        }

        Node fast = head.getNext();
        Node slow = head.getNext();

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;


    }

    public static Node getMid3(Node head) {

        if (head == null && head.getNext() != null && head.getNext().getNext() != null) {
            return null;
        }

        Node slow = head;
        Node fast = head.getNext().getNext();

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;


    }


    //?
    public static Node getMid4(Node head) {

        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }


        Node slow = head;
        Node fast = head.getNext();

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;


    }

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
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
//            test.next.next.next.next.next.next.next = new Node(7);
//            test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = getMid1(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = getMid2(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = getMid3(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

        ans1 = getMid4(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.value : "无");
        System.out.println(ans2 != null ? ans2.value : "无");

    }


}
