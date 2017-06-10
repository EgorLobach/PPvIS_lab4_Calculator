package view;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import controller.Controller;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anonymous on 05.06.2017.
 */
public class MainFrame {
    private Controller controller;
    private JFrame headFrame = new JFrame();
    private CalculatingPanel calculatingPanel = new CalculatingPanel();
    private JPanel treePanel = new JPanel();



    public MainFrame(String frame, Dimension dimension, Controller controller) {
        this.controller =controller;
        headFrame.setTitle(frame);
        headFrame.setSize(dimension);
        headFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        headFrame.setLayout(new BorderLayout());
    }

    public void initMainFrame() {


        headFrame.add(treePanel, BorderLayout.WEST);
        headFrame.add(calculatingPanel.initCalculatingPanel(), BorderLayout.CENTER);
        headFrame.setVisible(true);
    }


}
