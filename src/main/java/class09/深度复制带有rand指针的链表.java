package class09;


import java.util.HashMap;

/**
 * 一种特殊的单链表节点类描述如下
 * <p>
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * <p>
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 *
 * @author wlkq
 * @date 2023-03-30 9:34
 */
public class 深度复制带有rand指针的链表 {

    public static Node copyRandomList(Node head) {

        HashMap<Node, Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {


            map.get(cur).next = map.get(cur.next);  //null
            map.get(cur).random = map.get(cur.random);

            cur = cur.next;


        }

        return map.get(head);


    }

    public static Node copyRandomList2(Node head) {

        if (head == null) {
            return null;
        }

        Node cur = head;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node copy = null;
        while (cur != null) {
            copy=cur.next;
            copy.random = cur.random == null ? null : cur.random.next;
            cur = copy.next;

        }

        cur = head;
        Node res=head.next;
        while (cur != null) {
            copy = cur.next;
            cur.next = copy.next;
            copy.next = cur.next == null ? null : cur.next.next;
            cur = cur.next;
        }

        return res;


    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {

    }


}
