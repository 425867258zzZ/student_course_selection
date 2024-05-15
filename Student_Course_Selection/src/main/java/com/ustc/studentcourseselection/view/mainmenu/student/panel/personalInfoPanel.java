package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author ASUS
 */
public class personalInfoPanel extends JPanel {
    public personalInfoPanel(Student student) {
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

        JLabel name = new JLabel("������" + student.getName());
        name.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        name.setBounds(90, 260, 200, 30);
        basicPanel.add(name);

        JLabel number = new JLabel("ѧ�ţ�" + student.getNumber());
        number.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        number.setBounds(90, 290, 200, 30);
        basicPanel.add(number);

        JLabel gender = new JLabel("�Ա�" + student.getGender());
        gender.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        gender.setBounds(90, 320, 200, 30);
        basicPanel.add(gender);

        JLabel grade = new JLabel("�꼶��" + student.getGrade());
        grade.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        grade.setBounds(90, 350, 200, 30);
        basicPanel.add(grade);

        JLabel degree = new JLabel("ѧ����Σ�" + student.getDegree());
        degree.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        degree.setBounds(90, 380, 200, 30);
        basicPanel.add(degree);

        JPanel majorPanel = new JPanel();
        majorPanel.setLayout(null);
        majorPanel.setBackground(new Color(240, 240, 200));
        majorPanel.setBounds(400, 40, 530, 500);
        add(majorPanel);

        JLabel rTitle = new JLabel("�޶���Ϣ");
        rTitle.setForeground(new Color(10, 100, 128));
        rTitle.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        rTitle.setBounds(12, 5, 100, 30);
        majorPanel.add(rTitle);

        JLabel info = getjLabel();
        majorPanel.add(info);

        JLabel rTitle1 = new JLabel("��ǰ�޶���Ϣ");
        rTitle1.setForeground(new Color(10, 100, 128));
        rTitle1.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        rTitle1.setBounds(12, 300, 200, 30);
        majorPanel.add(rTitle1);

        JLabel major = new JLabel("רҵ��" + student.getMajor());
        major.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        major.setBounds(15, 340, 200, 30);
        majorPanel.add(major);

        JLabel className = new JLabel("�༶��" + student.getMajor() + "ѧԺ" + student.getClassName());
        className.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        className.setBounds(15, 370, 200, 30);
        majorPanel.add(className);
    }

    @NotNull
    private static JLabel getjLabel() {
        String text = "<html>�������Ķ�ѡ�ι���Ͳ���˵��,�˶Ա���רҵ�������޶�רҵ,����,Ӣ�Ű����Ϣ������ϵѧ������Ժϵ��ѧ���������ÿλ�����ѡ�Σ�רҵδ��Ч������ϵ֣������ʦ��Ӣ�Ű��ǿ����δ��Ч������ϵ��������ʦ���������������Ի�ѡ�ο���������+��ѡ�������������ͨ���������ֿγ̲����Ÿ��Ի���������ƿ���������ѡ��������������������ɿγ���Ϳ��ε�λ��ѧ�����������ͨʶ�γ�ģ������ڽ���ѡ�κ�ѡ���Ƽ����������ڿγ̡�ҳ�棬ѡ�����ͨʶģ�������в�ѯ�����ǲ���ͬѧ�����ڶ��꼶����ѧ��֮ǰ��ɵ��꼶�γ̼�ͨʶ��γ̵�ѧ��Ҫ��.<br>������������ͳ��ѡ����ѯ17ϵ63600282��mjjiang@ustc.edu.cn;��ѧ����ʵ������ѯ����ʵ������63601863��һ��ѧ¥229��clh@ustc.edu.cn����ѧ����ѧ����ѧ��ԭ���������ѧ��ѯ����ѧԺ63600719��Lxh89@ustc.edu.cn��������ѡ����ѯ��������ѧ���� 63601969��yxd1991@ustc.edu.cn;�Ͽεص�ARTS404/ARTS403��������ΪŮ���Σ�Ӣ���ѡ����ѯ�������ѧ���� 63601920��english6@ustc.edu.cn��˼����ѡ����ѯ�����˼����ѧԺ 63607992 xfen68@ustc.edu.cn";
        JLabel info = new JLabel(text);
        info.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        info.setBounds(20, 20, 450, 305);
        return info;
    }
}
