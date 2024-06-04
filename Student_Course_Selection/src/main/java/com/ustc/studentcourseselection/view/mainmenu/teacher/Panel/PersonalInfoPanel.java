package com.ustc.studentcourseselection.view.mainmenu.teacher.panel;

import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author ������
 */
public class PersonalInfoPanel extends JPanel {
    public PersonalInfoPanel(Teacher teacher) {
        setBounds(200, 0, 994, 667);
        setLayout(null);
        setBackground(new Color(240, 240, 200));

        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(null);
        basicPanel.setBackground(new Color(240, 240, 200));
        basicPanel.setBounds(50, 40, 280, 500);
        add(basicPanel);

        JLabel lTitle = new JLabel("������Ϣ");
        lTitle.setForeground(new Color(10, 100, 128));
        lTitle.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        lTitle.setBounds(12, 5, 100, 30);
        basicPanel.add(lTitle);

        ImageIcon personImg = UIUtil.loadPic("src/main/resources/images/personal-bg.png", 100, 100);
        JLabel img = new JLabel(personImg);
        img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setBounds(80, 80, 120, 150);
        img.setBackground(Color.WHITE);
        basicPanel.add(img);

        JLabel name = new JLabel("������" + teacher.getName());
        name.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        name.setBounds(90, 260, 200, 30);
        basicPanel.add(name);

        JLabel number = new JLabel("���ţ�" + teacher.getNumber());
        number.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        number.setBounds(90, 290, 200, 30);
        basicPanel.add(number);

        JLabel gender = new JLabel("�Ա�" + teacher.getGender());
        gender.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        gender.setBounds(90, 320, 200, 30);
        basicPanel.add(gender);

        JLabel grade = new JLabel("ѧԺ��" + teacher.getDepartment());
        grade.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        grade.setBounds(90, 350, 200, 30);
        basicPanel.add(grade);


        JPanel majorPanel = new JPanel();
        majorPanel.setLayout(null);
        majorPanel.setBackground(new Color(240, 240, 200));
        majorPanel.setBounds(400, 40, 530, 500);
        add(majorPanel);

        JLabel rTitle = new JLabel("��ʦ����");
        rTitle.setForeground(new Color(10, 100, 128));
        rTitle.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        rTitle.setBounds(12, 5, 100, 30);
        majorPanel.add(rTitle);

        JLabel info = getjLabel();
        majorPanel.add(info);

        JLabel rTitle1 = new JLabel("�ڿ���Ϣ");
        rTitle1.setForeground(new Color(10, 100, 128));
        rTitle1.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        rTitle1.setBounds(12, 300, 200, 30);
        majorPanel.add(rTitle1);


    }

    @NotNull
    private static JLabel getjLabel() {
        String text = "����";
        JLabel info = new JLabel(text);
        info.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        info.setBounds(20, 20, 450, 305);
        return info;
    }
}
