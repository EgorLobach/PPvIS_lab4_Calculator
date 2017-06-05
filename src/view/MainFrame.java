package view;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anonymous on 05.06.2017.
 */
public class MainFrame {
    private Controller controller;
    private JFrame headFrame = new JFrame();
    private JPanel calculatingPanel = new JPanel(new BorderLayout());
    private JPanel treePanel = new JPanel();
    private JTextField scoreboard = new JTextField(20);
    JPanel buttonPanel = new JPanel(new GridLayout(8,3));
    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton buttonDot = new JButton(".");
    JButton buttonLeft = new JButton("(");
    JButton buttonRight = new JButton(")");
    JButton buttonSum = new JButton("+");
    JButton buttonSub = new JButton("-");
    JButton buttonMul = new JButton("*");
    JButton buttonDivide = new JButton("/");
    JButton buttonSQRT = new JButton("sqrt");
    JButton buttonMod = new JButton("%");
    JButton buttonInverse = new JButton("1/x");
    JButton buttonBack = new JButton("C");
    JButton buttonStart = new JButton("=");

    public MainFrame(String frame, Dimension dimension, Controller controller) {
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException exception) {
            exception.printStackTrace();
        }
        this.controller =controller;
        headFrame.setTitle(frame);
        headFrame.setSize(dimension);
        headFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        headFrame.setLayout(new BorderLayout());
        scoreboard.setFont(new Font("Scoreboard", Font.ITALIC, 30));

    }

    public void initMainFrame() {
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



        calculatingPanel.add(scoreboard, BorderLayout.NORTH);
        calculatingPanel.add(buttonPanel, BorderLayout.CENTER);

        headFrame.add(treePanel, BorderLayout.WEST);
        headFrame.add(calculatingPanel, BorderLayout.CENTER);
        headFrame.setVisible(true);
    }
}
