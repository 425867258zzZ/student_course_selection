package com.ustc.studentcourseselection.view.mainmenu.teacher;

import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.MainMenu;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.teacher.panel.CourseInfoPanelAbstract;
import com.ustc.studentcourseselection.view.mainmenu.teacher.panel.ClassInfoPanelAbstract;
import com.ustc.studentcourseselection.view.mainmenu.teacher.panel.PersonalInfoPanel;

import java.awt.*;
import javax.swing.*;
import java.io.Serial;

/**
 * ��ʦ�������˵�
 *
 * @author ������
 */
public class TeacherMenu extends MainMenu {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public TeacherMenu(Teacher teacher) {
        super();
        JButton personInfoButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/personal_white.png", "    ������Ϣ", 160);
        JButton courseChosenButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/information_white.png", "    �γ���Ϣ", 215);
        JButton allCourseButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/course_white.png", "    �༶��Ϣ", 270);
        JButton logout = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/logout_white.png", "    �˳���¼", 325);

        JPanel personalInfoPanel = new PersonalInfoPanel(teacher);
        mainPanel.add(personalInfoPanel, "PersonalInfo");
        personInfoButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, personalInfoPanel, "        ������Ϣ        "));

        JPanel courseInfoPanel = new CourseInfoPanelAbstract(teacher);
        courseInfoPanel.setBackground(Color.WHITE);
        mainPanel.add(courseInfoPanel, "CourseInfo");
        courseChosenButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, courseInfoPanel, "        �γ���Ϣ        "));

        JPanel classInfoPanel = new ClassInfoPanelAbstract(teacher);
        classInfoPanel.setBackground(Color.WHITE);
        mainPanel.add(classInfoPanel, "ClassInfo");
        allCourseButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, classInfoPanel, "        �༶��Ϣ        "));

        logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(contentPane, "ȷ��Ҫ�˳���¼��?", "�˳���¼", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}