package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anonymous on 14.06.2017.
 */
public class Node {
    private String value;
    private char operator;
    private Node left;
    private Node right;
    private boolean leaf;

    public Node(Character character) {
        this.operator= character;
        this.leaf=false;
    }

    public Node(String operand) {
        this.value=operand;
        this.leaf=true;
        this.operator='0';
    }

    public boolean isLeaf() {
        return leaf;
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

    public void setValue(String value) {
        this.value = value;
    }

    public char getOperator() {
        return operator;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<Node> getAdjacentNodes() {
        List<Node> nodeList = new LinkedList<>();
        nodeList.add(left);
        nodeList.add(right);
        return nodeList;
    }
}
