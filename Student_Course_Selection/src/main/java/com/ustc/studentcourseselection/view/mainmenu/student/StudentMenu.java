package com.ustc.studentcourseselection.view.mainmenu.student;

import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.MainMenu;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.ChooseCoursePanelAbstract;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.CourseChosenPanelAbstract;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.PersonalInfoPanel;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.StudentSchedule;

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
        JButton scheduleButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/statistics_white.png", "    ѧ���α�", 325);
        JButton logout = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/logout_white.png", "    �˳���¼", 380);

        JPanel personalInfoPanel = new PersonalInfoPanel(student);
        mainPanel.add(personalInfoPanel, "personalInfo");
        personInfoButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, personalInfoPanel, "        ������Ϣ        "));

        JPanel courseChosenPanel = new CourseChosenPanelAbstract(student);
        mainPanel.add(courseChosenPanel, "courseChosen");
        courseChosenButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, courseChosenPanel, "        ��ѡ�γ�        "));

        JPanel chooseCoursePanel = new ChooseCoursePanelAbstract(student);
        mainPanel.add(chooseCoursePanel, "allCourse");
        allCourseButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, chooseCoursePanel, "        ���пγ�        "));

        JPanel studentSchedule = new StudentSchedule(student);
        mainPanel.add(studentSchedule, "student-schedule");
        scheduleButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, studentSchedule, "        ѧ���α�        "));

        logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(contentPane, "ȷ��Ҫ�˳���¼��?", "�˳���¼", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}