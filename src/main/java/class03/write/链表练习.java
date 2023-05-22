package class03.write;

/**
 * @author wlkq
 * @date 2023-03-17 8:23
 */
public class 链表练习 {

    public static Node reverse(Node head) {

        Node next = null;
        Node last = null;



        while (head != null) {
            next = head.getNext();
            head.setNext(last);
            last = head;
            head = next;
        }

        return last;


    }



    public static Node deleteNum(Node head, Integer num) {

        while (head.getValue() == num) {

            if (head == null) {
                break;
            }
            head = head.getNext();
        }


        Node pre = head;
        Node cur = head;


        while (cur!=null) {
            if (cur.getValue()==num){//跳过中间所有
                pre.setNext(cur.getNext());
            }else {
                pre=cur;
            }

            cur=cur.getNext();
        }


        return head;


    }


    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(3);
        Node node4 = new Node(1);
        Node node5 = new Node(1);

        node.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        Node node6 = deleteNum(node, 1);

//        Node node1 = reverse(node6);

        print(node6);


    }


    private static void print(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getValue());
        print(current.getNext());
    }


}
