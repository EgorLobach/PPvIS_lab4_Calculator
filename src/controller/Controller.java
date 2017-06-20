package controller;

import model.DataBase;
import model.Node;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * Created by anonymous on 05.06.2017.
 */
public class Controller {
    DataBase dataBase;
    private Node node;

    public Controller(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String startCalc(String inputString) {
        node = dataBase.buildNodes(inputString);
        return node.getValue();
    }

    public DefaultMutableTreeNode buildTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        if (node.isLeaf())
            root.setUserObject(node.getValue());
        else root.setUserObject(node.getOperator());
        if (!node.isLeaf())
            DFS(node, root);
        return root;
    }

    private void DFS(Node node, DefaultMutableTreeNode root) {
        List<Node> adjacentNodes = node.getAdjacentNodes();
        for (Node adjacentNode : adjacentNodes) {
            if (adjacentNode.isLeaf()) {
                DefaultMutableTreeNode adjacentNodeView = new DefaultMutableTreeNode(adjacentNode.getValue());
                root.add(adjacentNodeView);
            } else {
                DefaultMutableTreeNode adjacentNodeView = new DefaultMutableTreeNode(adjacentNode.getOperator());
                root.add(adjacentNodeView);
                DFS(adjacentNode, adjacentNodeView);
            }
        }
    }

    public void collapseTree() {
        collapse(node);
    }

    public void deployTree() {
        deploy(node);
    }

    private void collapse(Node node) {
        if (node.getRight().isLeaf() && node.getLeft().isLeaf()) {
            node.setLeaf(true);
        } else {
            if (!node.getLeft().isLeaf()) collapse(node.getLeft());
            else if (!node.getRight().isLeaf()) collapse(node.getRight());
        }
    }

    private boolean deploy(Node node) {
        if (node.isLeaf() && node.getOperator()!='0') {
            node.setLeaf(false);
            return true;
        } else {
            if (node.getRight().getOperator()!='0') {
                if (!deploy(node.getRight()) && node.getLeft().getOperator() != '0') {
                    return deploy(node.getLeft());
                }
            }
            else if (node.getLeft().getOperator() != '0'){
                return deploy(node.getLeft());
            }
            return false;
        }
    }
}
