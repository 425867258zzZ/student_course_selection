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
 * 学生界面主菜单
 *
 * @author 孟梓晗
 */
public class StudentMenu extends MainMenu {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Create the frame.
     */
    public StudentMenu(Student student) {
        super();
        JButton personInfoButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/personal_white.png", "    个人信息", 160);
        JButton courseChosenButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/information_white.png", "    已选课程", 215);
        JButton allCourseButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/course_white.png", "    所有课程", 270);
        JButton logout = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/logout_white.png", "    退出登录", 325);

        JPanel personalInfoPanel = new personalInfoPanel(student);
        mainPanel.add(personalInfoPanel, "personalInfo");
        personInfoButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, personalInfoPanel, "        个人信息        "));

        JPanel courseChosenPanel = new JPanel();
        courseChosenPanel.setBackground(Color.WHITE);
        courseChosenPanel.add(new JLabel("这里是已选课程面板"));
        mainPanel.add(courseChosenPanel, "courseChosen");
        courseChosenButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, courseChosenPanel, "        已选课程        "));

        JPanel allCoursePanel = new CourseChoosePanel(student);
        mainPanel.add(allCoursePanel, "allCourse");
        allCourseButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, allCoursePanel, "        所有课程        "));

        logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(contentPane, "确定要退出登录吗?", "退出登录", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}