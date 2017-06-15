package view;

import controller.Controller;
import model.Operators;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anonymous on 05.06.2017.
 */
public class MainFrame {
    private Controller controller;
    private JFrame headFrame = new JFrame();
    private JPanel calculatingPanel = new JPanel(new BorderLayout());
    private JTextField scoreboard = new JTextField(20);
    private JTextField expression = new JTextField(20);
    private String result = "";
    private JPanel buttonPanel = new JPanel(new GridLayout(8, 3));
    private JButton button0 = new JButton("0");
    private JButton button1 = new JButton("1");
    private JButton button2 = new JButton("2");
    private JButton button3 = new JButton("3");
    private JButton button4 = new JButton("4");
    private JButton button5 = new JButton("5");
    private JButton button6 = new JButton("6");
    private JButton button7 = new JButton("7");
    private JButton button8 = new JButton("8");
    private JButton button9 = new JButton("9");
    private JButton buttonDot = new JButton(".");
    private JButton buttonLeft = new JButton("(");
    private JButton buttonRight = new JButton(")");
    private JButton buttonSum = new JButton("+");
    private JButton buttonSub = new JButton("-");
    private JButton buttonMul = new JButton("*");
    private JButton buttonDivide = new JButton("/");
    private JButton buttonSQRT = new JButton("sqrt");
    private JButton buttonMod = new JButton("%");
    private JButton buttonInverse = new JButton("1/x");
    private JButton buttonBack = new JButton("C");
    private JButton buttonStart = new JButton("=");
    private JPanel degreePanel = new JPanel(new GridLayout(6, 1));
    private JButton buttonDegree = new JButton("Степень");
    private JButton buttonXSquared = new JButton("x^2");
    private JButton buttonXDegreeY = new JButton("x^y");
    private boolean dot = false;
    private int countBrackets = 0;

    private JPanel treePanel = new JPanel();
    private JScrollPane scrollPane;
    private JTree tree;

    public MainFrame(String frame, Dimension dimension, Controller controller) {
        this.controller =controller;
        headFrame.setTitle(frame);
        headFrame.setSize(dimension);
        headFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        headFrame.setLayout(new BorderLayout());
        scoreboard.setFont(new Font("Scoreboard", Font.ITALIC, 30));
        expression.setFont(new Font("Expression", Font.ITALIC, 20));
        buttonXSquared.setEnabled(false);
        buttonXDegreeY.setEnabled(false);
        treePanel.setLayout(new BorderLayout());
    }

    public void initMainFrame() {


        buttonAction();
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(buttonDot);
        buttonPanel.add(button0);
        buttonPanel.add(buttonBack);
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonRight);
        buttonPanel.add(buttonSum);
        buttonPanel.add(buttonSub);
        buttonPanel.add(buttonMul);
        buttonPanel.add(buttonDivide);
        buttonPanel.add(buttonSQRT);
        buttonPanel.add(buttonMod);
        buttonPanel.add(buttonInverse);
        buttonPanel.add(new JPanel());
        buttonPanel.add(buttonStart);
        degreePanel.add(buttonDegree);
        degreePanel.add(buttonXSquared);
        degreePanel.add(buttonXDegreeY);
        calculatingPanel.add(scoreboard, BorderLayout.NORTH);
        calculatingPanel.add(buttonPanel, BorderLayout.CENTER);
        calculatingPanel.add(degreePanel, BorderLayout.EAST);
        headFrame.add(calculatingPanel, BorderLayout.CENTER);
        headFrame.add(initTreePanel(), BorderLayout.WEST);
        headFrame.setVisible(true);
    }

    public JPanel initTreePanel() {
        scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        treePanel.add(expression, BorderLayout.NORTH);
        treePanel.add(scrollPane, BorderLayout.CENTER);
        return treePanel;
    }

    private void buttonAction() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().isEmpty()&&countBrackets==0) {
                    scoreboard.setText(controller.startCalc(scoreboard.getText()));
                    dot=true;
                    treePanel.removeAll();
                    tree = new JTree(controller.buildTree());
                    for(int i = 0; i < tree.getRowCount(); i ++) tree.expandRow(i);
                    result = getExpression((DefaultMutableTreeNode) tree.getPathForRow(0).getLastPathComponent());
                    expression.setText(result);
                    treeAction();
                    headFrame.add(initTreePanel(), BorderLayout.WEST);
                    headFrame.validate();
                    headFrame.repaint();
                }
            }
        });
        buttonDegree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonXSquared.setEnabled(!buttonXSquared.isEnabled());
                buttonXDegreeY.setEnabled(!buttonXDegreeY.isEnabled());
            }
        });
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().length()==0)
                    scoreboard.setText("0");
                else if (scoreboard.getText().equals("0") || isAddZero()
                        || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                } else scoreboard.setText(scoreboard.getText() + "0");
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("1");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "1");
                } else scoreboard.setText(scoreboard.getText() + "1");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("2");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "2");
                } else scoreboard.setText(scoreboard.getText() + "2");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("3");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "3");
                } else scoreboard.setText(scoreboard.getText() + "3");
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("4");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "4");
                } else scoreboard.setText(scoreboard.getText() + "4");
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("5");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "5");
                } else scoreboard.setText(scoreboard.getText() + "5");
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("6");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "6");
                } else scoreboard.setText(scoreboard.getText() + "6");
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("7");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "7");
                } else scoreboard.setText(scoreboard.getText() + "7");
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("8");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "8");
                } else scoreboard.setText(scoreboard.getText() + "8");
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("0")) {
                    scoreboard.setText("9");
                } else if (isAddZero()) {
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "9");
                } else scoreboard.setText(scoreboard.getText() + "9");
            }
        });
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().equals("")) {
                } else if (isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                } else if (!dot) {
                    scoreboard.setText(scoreboard.getText() + ".");
                    dot = true;
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == '.')
                        dot = false;
                    if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')')
                        countBrackets++;
                    if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == '(')
                        countBrackets--;
                    scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().length() - 1));
                }
            }
        });
        buttonSum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                        if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                            scoreboard.setText(scoreboard.getText() + "+");
                            dot = false;
                        }
                    }
                }
            }
        });
        buttonSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                        if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                            scoreboard.setText(scoreboard.getText() + "-");
                            dot = false;
                        }
                    }
                }
            }
        });
        buttonMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                        if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                            scoreboard.setText(scoreboard.getText() + "*");
                            dot = false;
                        }
                    }
                }
            }
        });
        buttonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                        if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                            scoreboard.setText(scoreboard.getText() + "/");
                            dot = false;
                        }
                    }
                }
            }
        });
        buttonMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                        if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                            scoreboard.setText(scoreboard.getText() + "%");
                            dot = false;
                        }
                    }
                }
            }
        });
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scoreboard.getText().length() > 0) {
                    if (isOperation() && scoreboard.getText().charAt(scoreboard.getText().length() - 1) != ')') {
                        scoreboard.setText(scoreboard.getText() + "(");
                        dot = false;
                        countBrackets++;
                    }
                } else {
                    scoreboard.setText(scoreboard.getText() + "(");
                    dot = false;
                    countBrackets++;
                }
            }
        });
        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countBrackets > 0) {
                    if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.' && !isOperation()) {
                        scoreboard.setText(scoreboard.getText() + ")");
                        dot = false;
                        countBrackets--;
                    }
                }
            }
        });
        buttonSQRT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isOperation() || scoreboard.getText().equals("")) {
                    scoreboard.setText(scoreboard.getText() + "sqrt(");
                    countBrackets++;
                }
            }
        });
        buttonInverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("") && countBrackets == 0 &&
                        scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                    scoreboard.setText("1/(" + scoreboard.getText() + ")");
                }
            }
        });
        buttonXSquared.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation()) {
                        scoreboard.setText(scoreboard.getText() + "^2");
                    }
                }
            }
        });
        buttonXDegreeY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!scoreboard.getText().equals("")) {
                    if (!isOperation()) {
                        scoreboard.setText(scoreboard.getText() + "^");
                    }
                }
            }
        });
    }
    public void treeAction(){
        tree.addTreeExpansionListener(
                new TreeExpansionListener() {
                    public void treeExpanded(TreeExpansionEvent event) {
                        expression.setText(expression.getText().replace(
                                String.valueOf(getResult((DefaultMutableTreeNode)event.getPath().getLastPathComponent())),
                                getExpression((DefaultMutableTreeNode)event.getPath().getLastPathComponent())));
                    }

                    public void treeCollapsed(TreeExpansionEvent event) {
                        if (expression.getText().indexOf(getExpression((DefaultMutableTreeNode)event.getPath().getLastPathComponent()))<0) {
                            expression.setText(result.replace(
                                    getExpression((DefaultMutableTreeNode) event.getPath().getLastPathComponent()),
                                    String.valueOf(getResult((DefaultMutableTreeNode) event.getPath().getLastPathComponent()))));
                        }
                        else
                            expression.setText(expression.getText().replace(
                                    getExpression((DefaultMutableTreeNode) event.getPath().getLastPathComponent()),
                                    String.valueOf(getResult((DefaultMutableTreeNode) event.getPath().getLastPathComponent()))));
                    }
                }
        );
    }

    private boolean isAddZero() {
        if (scoreboard.getText().length() > 1) {
            String temp = scoreboard.getText().substring(scoreboard.getText().length() - 2, scoreboard.getText().length());
            return (temp.equals("+0") || temp.equals("-0") || temp.equals("*0") ||
                    temp.equals("/0") || temp.equals("(0") || temp.equals("%0") || temp.equals("^0"));
        } else return false;
    }

    private boolean isOperation() {
        if (scoreboard.getText().length() > 1) {
            String temp = scoreboard.getText().substring(scoreboard.getText().length() - 1, scoreboard.getText().length());
            return (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") ||
                    temp.equals("(") || temp.equals("%") || temp.equals("^"));
        } else return false;
    }

    private double getResult(DefaultMutableTreeNode currentNode){
        Double firstOperand = 0.0;
        Double secondOperand = 0.0;
        if (currentNode.isLeaf()) return Double.valueOf(currentNode.getUserObject().toString());
        else {
            DefaultMutableTreeNode firstChildr = (DefaultMutableTreeNode) currentNode.getChildAt(0);
            DefaultMutableTreeNode secondChildr = (DefaultMutableTreeNode) currentNode.getChildAt(1);
            if (Operators.ALL_OPERATORS.indexOf(firstChildr.getUserObject().toString()) > -1) {
                firstOperand = getResult(firstChildr);
            } else {
                firstOperand = Double.valueOf(firstChildr.getUserObject().toString());
            }
            if (Operators.ALL_OPERATORS.indexOf(secondChildr.getUserObject().toString()) > -1) {
                secondOperand = getResult(secondChildr);
            } else {
                secondOperand = Double.valueOf(secondChildr.getUserObject().toString());
            }
            switch (currentNode.getUserObject().toString().charAt(0)) {
                case Operators.PLUS:
                    return firstOperand + secondOperand;
                case Operators.MINUS:
                    return firstOperand - secondOperand;
                case Operators.MULTIPLY:
                    return firstOperand * secondOperand;
                case Operators.DIVIDE:
                    return firstOperand / secondOperand;
                case Operators.MOD:
                    return firstOperand % secondOperand;
                case Operators.DEGREE:
                    return Math.pow(firstOperand, secondOperand);
                default:
                    System.out.println("Oops");
                    return 0;
            }
        }
    }

    private String getExpression(DefaultMutableTreeNode currentNode){
        String firstOperand = "";
        String secondOperand = "";
        if (currentNode.isLeaf()) return currentNode.getUserObject().toString();
        else {
            DefaultMutableTreeNode firstChildr = (DefaultMutableTreeNode) currentNode.getChildAt(0);
            DefaultMutableTreeNode secondChildr = (DefaultMutableTreeNode) currentNode.getChildAt(1);
            if (Operators.ALL_OPERATORS.indexOf(firstChildr.getUserObject().toString()) > -1) {
                firstOperand = getExpression(firstChildr);
            } else {
                firstOperand = (firstChildr.getUserObject().toString());
            }
            if (Operators.ALL_OPERATORS.indexOf(secondChildr.getUserObject().toString()) > -1) {
                secondOperand = getExpression(secondChildr);
            } else {
                secondOperand = (secondChildr.getUserObject().toString());
            }
            switch (currentNode.getUserObject().toString().charAt(0)) {
                case Operators.PLUS:
                    return "(" + firstOperand +"+"+ secondOperand + ")";
                case Operators.MINUS:
                    return "(" + firstOperand +"-"+ secondOperand + ")";
                case Operators.MULTIPLY:
                    return "(" + firstOperand +"*"+ secondOperand + ")";
                case Operators.DIVIDE:
                    return "(" + firstOperand +"/"+ secondOperand + ")";
                case Operators.MOD:
                    return "(" + firstOperand +"%"+ secondOperand + ")";
                case Operators.DEGREE:
                    return "(" + firstOperand+"^"+ secondOperand + ")";
                default:
                    System.out.println("Oops");
                    return "";
            }
        }
    }
}
