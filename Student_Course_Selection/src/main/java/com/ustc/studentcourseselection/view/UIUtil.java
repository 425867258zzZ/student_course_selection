package com.ustc.studentcourseselection.view;

import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * UI组件的工具方法
 *
 * @author 孟梓晗
 */
public class UIUtil {
    /**
     * 加载并缩放图标
     *
     * @param path      图片路径
     * @param picWidth  目标宽
     * @param picHeight 目标高度
     * @return 图标
     * @author 孟梓晗
     */
    public static ImageIcon loadPic(String path, int picWidth, int picHeight) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(picWidth, picHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * 在主界面增加侧边按钮
     *
     * @param leftSidePanel 侧边栏
     * @param picPath       路片路径
     * @param text          文字
     * @param buttonY       按钮的y值
     * @author 孟梓晗
     */
    public static JButton addSideButton(JPanel leftSidePanel, String picPath, String text, int buttonY) {
        JButton button = new JButton("   " + text);
        button.setFocusPainted(false);
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        button.setBackground(new Color(0, 64, 128));

        //这里55是按钮的高度,每次设置新的按钮时候,buttonY的值都要加55
        button.setBounds(0, buttonY, 199, 55);
        button.setBorderPainted(false);
        button.setLayout(null);
        button.setBorderPainted(false);
        ImageIcon personalMsg = UIUtil.loadPic(picPath, 40, 40);
        JLabel person = new JLabel(personalMsg);
        person.setBounds(15, -25, 100, 100);
        button.add(person);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 鼠标移入时，设置浅蓝色背景
                button.setBackground(new Color(173, 216, 230)); // 浅蓝色
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 鼠标移出时，恢复全蓝色背景
                button.setBackground(new Color(0, 64, 128)); // 全蓝色
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // 鼠标按下时，设置更深的蓝色背景
                button.setBackground(new Color(135, 206, 235)); // 深蓝色
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 鼠标释放时，恢复为浅蓝色背景
                button.setBackground(new Color(173, 216, 230)); // 浅蓝色
            }
        });
        leftSidePanel.add(button);
        return button;
    }

    /**
     * 实现选项卡逻辑,若当前按钮对应面板不在选项卡中则显示并添加选项卡,反之则直接跳转至对应面板
     *
     * @param mainPanel   主面板
     * @param tabbedPane  选项卡面板
     * @param targetPanel 目标面板
     * @param title       面板标题
     * @author 孟梓晗
     */
    public static void showOrSwitchToPanel(JPanel mainPanel, JTabbedPane tabbedPane, JPanel targetPanel, String title) {
        int index = tabbedPane.indexOfComponent(targetPanel);
        if (index != -1) {
            tabbedPane.setSelectedIndex(index);
        } else {
            mainPanel.add(targetPanel);
            tabbedPane.add(title, targetPanel);
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
        }
    }

    /**
     * 增加时间面板
     *
     * @param leftSidePanel 侧边栏
     * @param x             x
     * @param y             y
     * @author 孟梓晗
     */
    public static void addSideTimePanel(JPanel leftSidePanel, int x, int y) {
        JLabel timeLabel = new JLabel();
        timeLabel.setBackground(new Color(0, 64, 128));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 35));
        timeLabel.setBounds(x, y, 120, 30);
        String currentTime = BaseUtils.getTime().substring(11, 16);
        timeLabel.setText(currentTime);
        Timer timer = new Timer(1000, e -> timeLabel.setText(currentTime));
        timer.start();
        leftSidePanel.add(timeLabel);
    }

    /**
     * 主面板初始化,包括加载左右侧面板样式,侧边栏时间面板,选项卡组件
     *
     * @param contentPane   主容器
     * @param leftSidePanel 侧面板
     * @param tabbedPane    选项卡
     * @param mainPanel     主面板
     * @author 孟梓晗
     */
    public static void mainMenuInit(JPanel contentPane, JPanel leftSidePanel, JTabbedPane tabbedPane, JPanel mainPanel) {
        //容器面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        //侧边栏
        leftSidePanel.setBackground(new Color(0, 64, 128));
        leftSidePanel.setBounds(0, 0, 199, 677);
        contentPane.add(leftSidePanel);
        leftSidePanel.setLayout(null);

        //课本图标和时间组件
        ImageIcon logo = UIUtil.loadPic("src/main/resources/images/logo.png", 80, 64);
        JLabel ustcLogo = new JLabel(logo);
        ustcLogo.setBounds(5, 5, 185, 80);
        ustcLogo.setHorizontalAlignment(SwingConstants.CENTER);
        leftSidePanel.add(ustcLogo);
        UIUtil.addSideTimePanel(leftSidePanel, 53, 100);

        //主面板卡布局和选项卡
        tabbedPane.setBounds(199, 0, 800, 677);
        tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        tabbedPane.setBackground(new Color(0, 64, 128));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        mainPanel.add(tabbedPane);
        mainPanel.setBounds(200, 0, 994, 677);
        contentPane.add(mainPanel);
    }

    /**
     * 创建一个表格，放在scrollPane中
     * 如果需要修改列宽，需要对table某一列设置宽度
     * 返回表格table后，不要忘了scrollPane.setViewportView(table)使表格可见
     *
     * @param header 表头，如果每一行末尾有按钮如：“编辑”“选课”等，表头中不要漏了“操作”一列
     * @param datas  数据，每一列与表头对应
     * @return 表格
     */
    public static JTable creatTable(Vector<String> header, Vector<Vector<String>> datas) {
        JTable table = new JTable();
        // 设置表格属性
        table.setBackground(Color.WHITE);
        table.setModel(new DefaultTableModel(datas, header));
        table.setRowHeight(25);
        table.setDefaultEditor(Object.class, null);
        table.setFocusable(false);
        table.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        // 居中显示
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //鼠标移动到某一表格式，会显示表格内容，避免因长度丢失内容
        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int column = table.columnAtPoint(evt.getPoint());

                // 获取单元格中的内容
                Object value = table.getValueAt(row, column);

                // 将内容设置为工具提示
                table.setToolTipText((String) value);
            }
        });

        return table;
    }

}

