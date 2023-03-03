package class03;

/**
 * @author wlkq
 * @date 2023-02-28 14:04
 */
public class Node<T> {

    public T value;

    public Node next;

    public Node(T value) {
        this.value = value;
    }

    public Node() {
    }
}
