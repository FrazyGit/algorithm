package class03.write.W02;

public class DoubleNode<T>
{

    private T value;
    public DoubleNode next;
    public DoubleNode pre;

    public DoubleNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
