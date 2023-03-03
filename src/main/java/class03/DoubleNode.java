package class03;

/**
 * @author wlkq
 * @date 2023-02-28 14:05
 */
public class DoubleNode<T> {

    public T value;

    public DoubleNode next;

    public DoubleNode last;

    public DoubleNode(T value) {
        this.value = value;
    }

    public DoubleNode() {
    }
}
