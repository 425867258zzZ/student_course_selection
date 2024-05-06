package com.ustc.studentcourseselection.view;

import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * UI����Ĺ��߷���
 *
 * @author ������
 */
public class UIUtil {
    /**
     * ���ز�����ͼ��
     *
     * @param path      ͼƬ·��
     * @param picWidth  Ŀ���
     * @param picHeight Ŀ��߶�
     * @return the image icon
     * @author ������
     */
    public static ImageIcon loadPic(String path, int picWidth, int picHeight) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(picWidth, picHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }


    /**
     * �����������Ӳ�߰�ť
     *
     * @param leftSidePanel �����
     * @param picPath       ·Ƭ·��
     * @param text          ����
     * @param buttonY       ��ť��yֵ
     * @author ������
     */
    public static JButton addSideButton(JPanel leftSidePanel, String picPath, String text, int buttonY) {
        JButton button = new JButton("   " + text);
        button.setFocusPainted(false);
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        button.setBackground(new Color(0, 64, 128));

        //����55�ǰ�ť�ĸ߶�,ÿ�������µİ�ťʱ��,buttonY��ֵ��Ҫ��55
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
                // �������ʱ������ǳ��ɫ����
                button.setBackground(new Color(173, 216, 230)); // ǳ��ɫ
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // ����Ƴ�ʱ���ָ�ȫ��ɫ����
                button.setBackground(new Color(0, 64, 128)); // ȫ��ɫ
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // ��갴��ʱ�����ø������ɫ����
                button.setBackground(new Color(135, 206, 235)); // ����ɫ
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // ����ͷ�ʱ���ָ�Ϊǳ��ɫ����
                button.setBackground(new Color(173, 216, 230)); // ǳ��ɫ
            }
        });
        leftSidePanel.add(button);
        return button;
    }

    /**
     * ʵ��ѡ��߼�,����ǰ��ť��Ӧ��岻��ѡ�������ʾ�����ѡ�,��֮��ֱ����ת����Ӧ���
     *
     * @param mainPanel   �����
     * @param tabbedPane  ѡ����
     * @param targetPanel Ŀ�����
     * @param title       ������
     * @author ������
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
     * ����ʱ�����
     *
     * @param leftSidePanel �����
     * @param x             x
     * @param y             y
     * @author ������
     */
    public static void addSideTimePanel(JPanel leftSidePanel, int x, int y) {
        JLabel timeLabel = new JLabel();
        timeLabel.setBackground(new Color(0, 64, 128));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("΢���ź�", Font.PLAIN, 35));
        timeLabel.setBounds(x, y, 120, 30);
        String currentTime = BaseUtils.getTime().substring(11, 16);
        timeLabel.setText(currentTime);
        Timer timer = new Timer(1000, e -> timeLabel.setText(currentTime));
        timer.start();
        leftSidePanel.add(timeLabel);
    }

    /**
     * ������ʼ��,�����������Ҳ������ʽ,�����ʱ�����,ѡ����
     *
     * @param contentPane   ������
     * @param leftSidePanel �����
     * @param tabbedPane    ѡ�
     * @param mainPanel     �����
     * @author ������
     */
    public static void mainMenuInit(JPanel contentPane, JPanel leftSidePanel, JTabbedPane tabbedPane, JPanel mainPanel) {
        //�������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        //�����
        leftSidePanel.setBackground(new Color(0, 64, 128));
        leftSidePanel.setBounds(0, 0, 199, 677);
        contentPane.add(leftSidePanel);
        leftSidePanel.setLayout(null);

        //�α�ͼ���ʱ�����
        ImageIcon logo = UIUtil.loadPic("src/main/resources/images/logo.png", 80, 64);
        JLabel ustcLogo = new JLabel(logo);
        ustcLogo.setBounds(5, 5, 185, 80);
        ustcLogo.setHorizontalAlignment(SwingConstants.CENTER);
        leftSidePanel.add(ustcLogo);
        UIUtil.addSideTimePanel(leftSidePanel, 53, 100);

        //����忨���ֺ�ѡ�
        tabbedPane.setBounds(199, 0, 800, 677);
        tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        tabbedPane.setBackground(new Color(0, 64, 128));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        mainPanel.add(tabbedPane);
        mainPanel.setBounds(200, 0, 994, 677);
        contentPane.add(mainPanel);
    }
}

