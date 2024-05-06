package com.ustc.studentcourseselection.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author 孟梓晗
 */
public class MainMenu extends JFrame {
    public JPanel contentPane = new JPanel();
    public JPanel leftSidePanel = new JPanel();
    public JTabbedPane tabbedPane = new JTabbedPane();
    CardLayout cardLayout = new CardLayout();
    public JPanel mainPanel = new JPanel(cardLayout);

    public MainMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("学生选课系统");
        setBounds(100, 100, 1200, 700);
        setResizable(false);
        UIUtil.mainMenuInit(contentPane, leftSidePanel, tabbedPane, mainPanel);
        setContentPane(contentPane);
    }
}
