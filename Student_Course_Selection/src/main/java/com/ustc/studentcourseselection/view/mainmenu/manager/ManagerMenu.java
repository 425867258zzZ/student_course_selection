package com.ustc.studentcourseselection.view.mainmenu.manager;

import com.ustc.studentcourseselection.view.MainMenu;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.manager.panel.CourseManagementPanel;
import com.ustc.studentcourseselection.view.mainmenu.manager.panel.StudentManagementPanel;
import com.ustc.studentcourseselection.view.mainmenu.manager.panel.TeacherManagementPanel;

import javax.swing.*;

/**
 * 管理员界面主菜单
 *
 * @author 13901
 */
public class ManagerMenu extends MainMenu {
    public ManagerMenu() {
        super();
        JButton courseManagementButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/course_white.png", "    课程管理", 160);
        JButton studentManagementButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/personal_white.png", "    学生管理", 215);
        JButton teacherManagementButton = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/personal_white.png", "    老师管理", 270);
        JButton logout = UIUtil.addSideButton(leftSidePanel, "src/main/resources/images/side/logout_white.png", "    退出登录", 325);

        JPanel courseManagementPanel = new CourseManagementPanel(mainPanel, tabbedPane);
        mainPanel.add(courseManagementPanel, "courseManagement");
        courseManagementButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, courseManagementPanel, "        课程管理        "));

        JPanel studentManagementPanel = new StudentManagementPanel(mainPanel, tabbedPane);
        mainPanel.add(studentManagementPanel, "studentManagement");
        studentManagementButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, studentManagementPanel, "        学生管理        "));

        JPanel teacherManagementPanel = new TeacherManagementPanel(mainPanel, tabbedPane);
        mainPanel.add(teacherManagementPanel, "teacherManagement");
        teacherManagementButton.addActionListener(e -> UIUtil.showOrSwitchToPanel(mainPanel, tabbedPane, teacherManagementPanel, "        课程管理        "));

        logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(contentPane, "确定要退出登录吗?", "退出登录", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new ManagerMenu().setVisible(true);
    }
}