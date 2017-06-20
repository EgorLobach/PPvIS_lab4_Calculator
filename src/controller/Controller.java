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
    public String startCalc(String inputString){
        node = dataBase.buildNodes(inputString);
        return node.getValue();
    }

    public DefaultMutableTreeNode buildTree(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        if (node.isLeaf())
            root.setUserObject(node.getValue());
        else root.setUserObject(node.getOperator());
        if (node.getLeft()!= null&&node.getRight()!=null)
            DFS(node, root);
        return root;
    }

    private void DFS(Node node, DefaultMutableTreeNode root) {
        List<Node> adjacentNodes = node.getAdjacentNodes();
        for (Node adjacentNode : adjacentNodes){
            if(adjacentNode.isLeaf()){
                DefaultMutableTreeNode adjacentNodeView = new DefaultMutableTreeNode(adjacentNode.getValue());
                root.add(adjacentNodeView);
            }
            else {
                DefaultMutableTreeNode adjacentNodeView = new DefaultMutableTreeNode(adjacentNode.getOperator());
                root.add(adjacentNodeView);
                DFS(adjacentNode,adjacentNodeView);
            }
        }
    }
}
