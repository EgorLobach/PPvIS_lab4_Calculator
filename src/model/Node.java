package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anonymous on 14.06.2017.
 */
public class Node {
    private String value;
    private Node left;
    private Node right;

    public Node(Character character) {
        this.value= String.valueOf(character);
    }

    public Node(String operand) {
        this.value=operand;
    }

    public String getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


    public List<Node> getAdjacentNodes() {
        List<Node> nodeList = new LinkedList<>();
        nodeList.add(left);
        nodeList.add(right);
        return nodeList;
    }
}
