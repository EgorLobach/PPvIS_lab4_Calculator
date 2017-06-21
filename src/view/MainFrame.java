package view;

import controller.Controller;
import model.Operators;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;

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
    private JPanel controllingExpressionPanel = new JPanel();
    private JButton clottingButton = new JButton("<");
    private JButton deploymentButton = new JButton(">");
    private JTree tree;

    public MainFrame(String frame, Dimension dimension, Controller controller) {
        this.controller = controller;
        headFrame.setTitle(frame);
        headFrame.setSize(dimension);
        headFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        headFrame.setLayout(new BorderLayout());
        scoreboard.setFont(new Font("Scoreboard", Font.ITALIC, 30));
        scoreboard.setEditable(false);
        scoreboard.setBackground(Color.WHITE);
        scoreboard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        expression.setFont(new Font("Expression", Font.ITALIC, 20));
        expression.setEditable(false);
        expression.setBackground(Color.WHITE);
        expression.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonXSquared.setEnabled(false);
        buttonXDegreeY.setEnabled(false);
        clottingButton.setEnabled(false);
        deploymentButton.setEnabled(false);
        treePanel.setLayout(new BorderLayout());
    }

    public void initMainFrame() {

        controllingExpressionAction();
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
        scoreboard.requestFocusInWindow();
    }

    private JPanel initTreePanel() {
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 500));
        treePanel.add(expression, BorderLayout.NORTH);
        treePanel.add(scrollPane, BorderLayout.CENTER);
        controllingExpressionPanel.add(clottingButton);
        controllingExpressionPanel.add(deploymentButton);
        treePanel.add(controllingExpressionPanel, BorderLayout.SOUTH);
        return treePanel;
    }

    private void controllingExpressionAction() {
        clottingButton.addActionListener(e -> {
            controller.collapseTree();
            treePanel.removeAll();
            tree = new JTree(controller.buildTree());
            for (int i = 0; i < tree.getRowCount(); i++) tree.expandRow(i);
            result = getExpression((DefaultMutableTreeNode) tree.getPathForRow(0).getLastPathComponent());
            expression.setText(result);
            headFrame.add(initTreePanel(), BorderLayout.WEST);
            headFrame.validate();
            headFrame.repaint();
            scoreboard.requestFocusInWindow();
        });
        deploymentButton.addActionListener(e -> {
            controller.deployTree();
            treePanel.removeAll();
            tree = new JTree(controller.buildTree());
            for (int i = 0; i < tree.getRowCount(); i++) tree.expandRow(i);
            result = getExpression((DefaultMutableTreeNode) tree.getPathForRow(0).getLastPathComponent());
            expression.setText(result);
            headFrame.add(initTreePanel(), BorderLayout.WEST);
            headFrame.validate();
            headFrame.repaint();
            scoreboard.requestFocusInWindow();
        });
    }

    private void buttonAction() {
        buttonStart.addActionListener(e -> {
            if (!scoreboard.getText().isEmpty() && countBrackets == 0) {
                scoreboard.setText(controller.startCalc(scoreboard.getText()));
                dot = true;
                treePanel.removeAll();
                tree = new JTree(controller.buildTree());
                for (int i = 0; i < tree.getRowCount(); i++) tree.expandRow(i);
                result = getExpression((DefaultMutableTreeNode) tree.getPathForRow(0).getLastPathComponent());
                expression.setText(result);
                headFrame.add(initTreePanel(), BorderLayout.WEST);
                headFrame.validate();
                headFrame.repaint();
                clottingButton.setEnabled(true);
                deploymentButton.setEnabled(true);
                scoreboard.requestFocusInWindow();
            }
        });
        buttonDegree.addActionListener(e -> {
            buttonXSquared.setEnabled(!buttonXSquared.isEnabled());
            buttonXDegreeY.setEnabled(!buttonXDegreeY.isEnabled());
            scoreboard.requestFocusInWindow();
        });
        button0.addActionListener(e -> action0());
        button1.addActionListener(e -> actionNumber("1"));
        button2.addActionListener(e -> actionNumber("2"));
        button3.addActionListener(e -> actionNumber("3"));
        button4.addActionListener(e -> actionNumber("4"));
        button5.addActionListener(e -> actionNumber("5"));
        button6.addActionListener(e -> actionNumber("6"));
        button7.addActionListener(e -> actionNumber("7"));
        button8.addActionListener(e -> actionNumber("8"));
        button9.addActionListener(e -> actionNumber("9"));
        buttonDot.addActionListener(e -> actionDot());
        buttonBack.addActionListener(e -> actionBack());
        buttonSum.addActionListener(e -> actionOperator("+"));
        buttonSub.addActionListener(e -> actionOperator("-"));
        buttonMul.addActionListener(e -> actionOperator("*"));
        buttonDivide.addActionListener(e -> actionOperator("/"));
        buttonMod.addActionListener(e -> actionOperator("%"));
        buttonLeft.addActionListener(e -> actionLeft());
        buttonRight.addActionListener(e -> actionRight());
        buttonSQRT.addActionListener(e -> {
            if (isOperation() || scoreboard.getText().equals("")) {
                scoreboard.setText(scoreboard.getText() + "sqrt(");
                countBrackets++;
            }
            scoreboard.requestFocusInWindow();
        });
        buttonInverse.addActionListener(e -> {
            if (!scoreboard.getText().equals("") && countBrackets == 0 &&
                    scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                scoreboard.setText("1/(" + scoreboard.getText() + ")");
            }
            scoreboard.requestFocusInWindow();
        });
        buttonXSquared.addActionListener(e -> {
            if (!scoreboard.getText().equals("")) {
                if (!isOperation()) {
                    scoreboard.setText(scoreboard.getText() + "^2");
                }
            }
            scoreboard.requestFocusInWindow();
        });
        buttonXDegreeY.addActionListener(e -> actionXDegreeY());
        scoreboard.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_9 && e.isShiftDown()) {
                    actionLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_0 && e.isShiftDown()) {
                    actionRight();
                }
                if (e.getKeyCode() == KeyEvent.VK_PERIOD || e.getKeyCode() == KeyEvent.VK_DECIMAL) {
                    actionDot();
                }
                if ((e.getKeyCode() == KeyEvent.VK_0 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                    action0();
                }
                if ((e.getKeyCode() == KeyEvent.VK_1 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    actionNumber("1");
                }
                if ((e.getKeyCode() == KeyEvent.VK_2 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    actionNumber("2");
                }
                if ((e.getKeyCode() == KeyEvent.VK_3 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    actionNumber("3");
                }
                if ((e.getKeyCode() == KeyEvent.VK_4 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    actionNumber("4");
                }
                if ((e.getKeyCode() == KeyEvent.VK_5 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    actionNumber("5");
                }
                if ((e.getKeyCode() == KeyEvent.VK_6 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    actionNumber("6");
                }
                if ((e.getKeyCode() == KeyEvent.VK_7 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                    actionNumber("7");
                }
                if ((e.getKeyCode() == KeyEvent.VK_8 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    actionNumber("8");
                }
                if ((e.getKeyCode() == KeyEvent.VK_9 && !e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                    actionNumber("9");
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    actionBack();
                }
                if ((e.getKeyCode() == KeyEvent.VK_EQUALS && e.isShiftDown()) || e.getKeyCode() == 107) {
                    actionOperator("+");
                }
                if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    actionOperator("-");
                }
                if ((e.getKeyCode() == KeyEvent.VK_8 && e.isShiftDown()) || e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                    actionOperator("*");
                }
                if (e.getKeyCode() == KeyEvent.VK_SLASH || e.getKeyCode() == KeyEvent.VK_DIVIDE) {
                    actionOperator("/");
                }
                if (e.getKeyCode() == KeyEvent.VK_5 && e.isShiftDown()) {
                    actionOperator("%");
                }
                if (e.getKeyCode() == KeyEvent.VK_6 && e.isShiftDown()) {
                    actionOperator("^");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void action0() {
        if (scoreboard.getText().length() == 0)
            scoreboard.setText("0");
        else if (!scoreboard.getText().equals("0") && !isAddZero()
                && scoreboard.getText().charAt(scoreboard.getText().length() - 1) != ')') {
            scoreboard.setText(scoreboard.getText() + "0");
        }
        scoreboard.requestFocusInWindow();
    }

    private void actionNumber(String number) {
        if (scoreboard.getText().equals("0")) {
            scoreboard.setText(number);
        } else if (isAddZero()) {
            scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().lastIndexOf("0")) + "9");
        } else scoreboard.setText(scoreboard.getText() + number);
        scoreboard.requestFocusInWindow();
    }

    private void actionDot() {
        if (!scoreboard.getText().equals("") && !isOperation() && scoreboard.getText().charAt(scoreboard.getText().length() - 1) != ')') {
            if (!dot) {
                scoreboard.setText(scoreboard.getText() + ".");
                dot = true;
            }
        }
        scoreboard.requestFocusInWindow();
    }

    private void actionBack() {
        if (!scoreboard.getText().equals("")) {
            if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == '.')
                dot = false;
            if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')')
                countBrackets++;
            if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) == '(')
                countBrackets--;
            scoreboard.setText(scoreboard.getText().substring(0, scoreboard.getText().length() - 1));
        }
        scoreboard.requestFocusInWindow();
    }

    private void actionOperator(String operator) {
        if (!scoreboard.getText().equals("")) {
            if (!isOperation() || scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
                if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.') {
                    scoreboard.setText(scoreboard.getText() + operator);
                    dot = false;
                }
            }
        }
        scoreboard.requestFocusInWindow();
    }

    private void actionLeft() {
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
        scoreboard.requestFocusInWindow();
    }

    private void actionRight() {
        if (countBrackets > 0) {
            if (scoreboard.getText().charAt(scoreboard.getText().length() - 1) != '.' && !isOperation()) {
                scoreboard.setText(scoreboard.getText() + ")");
                dot = false;
                countBrackets--;
            }
        }
        scoreboard.requestFocusInWindow();
    }

    private void actionXDegreeY() {
        if (!scoreboard.getText().equals("")) {
            if (!isOperation()) {
                scoreboard.setText(scoreboard.getText() + "^");
            }
        }
        scoreboard.requestFocusInWindow();
    }

    private boolean isAddZero() {
        if (scoreboard.getText().length() > 1) {
            String temp = scoreboard.getText().substring(scoreboard.getText().length() - 2, scoreboard.getText().length());
            return (temp.equals("+0") || temp.equals("-0") || temp.equals("*0") ||
                    temp.equals("/0") || temp.equals("(0") || temp.equals("%0") || temp.equals("^0"));
        } else return false;
    }

    private boolean isOperation() {
        if (scoreboard.getText().length() > 0) {
            String temp = scoreboard.getText().substring(scoreboard.getText().length() - 1, scoreboard.getText().length());
            return (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") ||
                    temp.equals("(") || temp.equals("%") || temp.equals("^"));
        } else return false;
    }

    private String getExpression(DefaultMutableTreeNode currentNode) {
        String firstOperand;
        String secondOperand;
        if (currentNode.isLeaf()) return currentNode.getUserObject().toString();
        else {
            DefaultMutableTreeNode firstChild = (DefaultMutableTreeNode) currentNode.getChildAt(0);
            DefaultMutableTreeNode secondChild = (DefaultMutableTreeNode) currentNode.getChildAt(1);
            if (Operators.ALL_OPERATORS.contains(firstChild.getUserObject().toString())) {
                firstOperand = getExpression(firstChild);
            } else {
                firstOperand = (firstChild.getUserObject().toString());
            }
            if (Operators.ALL_OPERATORS.contains(secondChild.getUserObject().toString())) {
                secondOperand = getExpression(secondChild);
            } else {
                secondOperand = (secondChild.getUserObject().toString());
            }
            switch (currentNode.getUserObject().toString().charAt(0)) {
                case Operators.PLUS:
                    return "(" + firstOperand + "+" + secondOperand + ")";
                case Operators.MINUS:
                    return "(" + firstOperand + "-" + secondOperand + ")";
                case Operators.MULTIPLY:
                    return "(" + firstOperand + "*" + secondOperand + ")";
                case Operators.DIVIDE:
                    return "(" + firstOperand + "/" + secondOperand + ")";
                case Operators.MOD:
                    return "(" + firstOperand + "%" + secondOperand + ")";
                case Operators.DEGREE:
                    return "(" + firstOperand + "^" + secondOperand + ")";
                default:
                    System.out.println("Oops");
                    return "";
            }
        }
    }
}
