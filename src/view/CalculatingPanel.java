package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anonymous on 05.06.2017.
 */
public class CalculatingPanel {
    private JPanel calculatingPanel = new JPanel(new BorderLayout());
    private JTextField scoreboard = new JTextField(20);
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

    public CalculatingPanel() {
        scoreboard.setFont(new Font("Scoreboard", Font.ITALIC, 30));
        buttonXSquared.setEnabled(false);
        buttonXDegreeY.setEnabled(false);
    }

    public JPanel initCalculatingPanel() {
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

        return calculatingPanel;
    }

    private void buttonAction() {
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
                if (scoreboard.getText().equals("0") || isAddZero()
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
                if (scoreboard.getText().equals("")) {}
                else if (isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {}
                else if (!dot) {
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
                    if (!isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
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
                    if (!isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
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
                    if (!isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
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
                    if (!isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
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
                    if (!isOperation()||scoreboard.getText().charAt(scoreboard.getText().length() - 1) == ')') {
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
                if (scoreboard.getText().length()>0) {
                    if (isOperation()&&scoreboard.getText().charAt(scoreboard.getText().length() - 1) != ')') {
                        scoreboard.setText(scoreboard.getText() + "(");
                        dot = false;
                        countBrackets++;
                    }
                }
                else {
                    scoreboard.setText(scoreboard.getText()+"(");
                    dot = false;
                    countBrackets++;
                }
            }
        });
        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(countBrackets>0){
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
                    if (isOperation()||scoreboard.getText().equals("")) {
                        scoreboard.setText(scoreboard.getText() + "sqrt(");
                        countBrackets++;
                }
            }
        });
        buttonInverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isOperation()||scoreboard.getText().equals("")) {
                    scoreboard.setText(scoreboard.getText() + "1/");
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
}
