package class03.write.W01;

/**
 * @author wlkq
 * @date 2023-03-17 8:23
 */
public class Node {


    public Node next;
    public Integer value;

    public Node(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
