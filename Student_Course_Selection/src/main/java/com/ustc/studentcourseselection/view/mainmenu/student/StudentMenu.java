package com.ustc.studentcourseselection.view.mainmenu.student;

import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.MainMenu;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.CourseChoosePanel;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.personalInfoPanel;

import java.awt.*;
import javax.swing.*;
import java.io.Serial;

/**
 * ѧ���������˵�
 *
 * @author ������
 */
public class StudentMenu extends MainMenu {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public StudentMenu(Student student) {
        super();
        JButton personInfoButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/personal_white.png", "    ������Ϣ", 160);
        JButton courseChosenButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/information_white.png", "    ��ѡ�γ�", 215);
        JButton allCourseButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/course_white.png", "    ���пγ�", 270);
        JButton logout = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/logout_white.png", "    �˳���¼", 325);

        JPanel personalInfoPanel = new personalInfoPanel(student);
        mainPanel.add(personalInfoPanel, "personalInfo");
        personInfoButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, personalInfoPanel, "        ������Ϣ        "));

        JPanel courseChosenPanel = new JPanel();
        courseChosenPanel.setBackground(Color.WHITE);
        courseChosenPanel.add(new JLabel("��������ѡ�γ����"));
        mainPanel.add(courseChosenPanel, "courseChosen");
        courseChosenButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, courseChosenPanel, "        ��ѡ�γ�        "));

        JPanel allCoursePanel = new CourseChoosePanel(student);
        mainPanel.add(allCoursePanel, "allCourse");
        allCourseButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, allCoursePanel, "        ���пγ�        "));

        logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(contentPane, "ȷ��Ҫ�˳���¼��?", "�˳���¼", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}