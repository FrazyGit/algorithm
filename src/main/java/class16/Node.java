package class16;

import java.util.ArrayList;

/**
 * @author gc
 * @date 2023-11-12 15:38
 */
public class Node {
    public int value;
    public int in;
    public int out;

    public ArrayList<Node> next;

    public ArrayList<Edge> edges;


    public Node(int value){
        this.value=value;
        in=0;
        out=0;
        next=new ArrayList<>();
        edges=new ArrayList<>();

    }


}
