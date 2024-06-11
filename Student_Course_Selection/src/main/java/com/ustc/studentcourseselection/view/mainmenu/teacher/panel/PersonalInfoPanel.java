package com.ustc.studentcourseselection.view.mainmenu.teacher.panel;

import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.UIUtil;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;


/**
 * @author 潘义良
 */
public class PersonalInfoPanel extends JPanel {

    /**
     * The constant scrollPane.
     */
    public static JScrollPane scrollPane = new JScrollPane();

    public PersonalInfoPanel(Teacher teacher) {
        setBounds(200, 0, 994, 667);
        setLayout(null);
        setBackground(new Color(240, 240, 200));

        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(null);
        basicPanel.setBackground(new Color(240, 240, 200));
        basicPanel.setBounds(50, 40, 280, 500);
        add(basicPanel);

        JLabel lTitle = new JLabel("基础信息");
        lTitle.setForeground(new Color(10, 100, 128));
        lTitle.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        lTitle.setBounds(12, 5, 100, 30);
        basicPanel.add(lTitle);

        ImageIcon personImg = UIUtil.loadPic("src/main/resources/images/personal-bg.png", 100, 100);
        JLabel img = new JLabel(personImg);
        img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        img.setHorizontalAlignment(SwingConstants.CENTER);
        img.setBounds(80, 80, 120, 150);
        img.setBackground(Color.WHITE);
        basicPanel.add(img);

        JLabel name = new JLabel("姓名：" + teacher.getName());
        name.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        name.setBounds(90, 260, 200, 30);
        basicPanel.add(name);

        JLabel number = new JLabel("工号：" + teacher.getNumber());
        number.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        number.setBounds(90, 290, 200, 30);
        basicPanel.add(number);

        JLabel gender = new JLabel("性别：" + teacher.getGender());
        gender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gender.setBounds(90, 320, 200, 30);
        basicPanel.add(gender);

        JLabel grade = new JLabel("学院：" + teacher.getDepartment());
        grade.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        grade.setBounds(90, 350, 200, 30);
        basicPanel.add(grade);


        JPanel majorPanel = new JPanel();
        majorPanel.setLayout(null);
        majorPanel.setBackground(new Color(240, 240, 200));
        majorPanel.setBounds(400, 40, 530, 500);
        add(majorPanel);

        JLabel rTitle = new JLabel("教师介绍");
        rTitle.setForeground(new Color(10, 100, 128));
        rTitle.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        rTitle.setBounds(12, 5, 100, 30);
        majorPanel.add(rTitle);

        JLabel info = getjLabel();
        majorPanel.add(info);

        JLabel rTitle1 = new JLabel("授课信息");
        rTitle1.setForeground(new Color(10, 100, 128));
        rTitle1.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        rTitle1.setBounds(12, 300, 200, 30);
        majorPanel.add(rTitle1);

        JLabel message = getMessage(teacher.getName());
        majorPanel.add(message);

        JButton searchButton = new JButton("更改密码");
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(new Color(225, 255, 255));
        searchButton.setBounds(680, 550, 180, 30);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setFocusPainted(false);
        add(searchButton);

        searchButton.addActionListener(e -> changePassword(teacher));
    }

    @NotNull
    private static JLabel getjLabel() {
        String text = "<html>请认真阅读选课规则和操作说明,核对本人专业。调整修读专业,方向,英才班等信息，请联系学生所在院系教学秘书重新置课或自主选课，专业未生效的请联系郑海宁老师，英才班和强基班未生效的请联系林晓立老师。课堂容量：个性化选课可申请人数+已选中人数（含审核通过），部分课程不开放个性化申请或限制课堂容量。选课上限人数与课堂容量由课程组和开课单位教学秘书管理。核心通识课程模块分类在进入选课后选择“推荐培养方案内课程”页面，选择核心通识模块分类进行查询。信智部的同学建议在二年级春季学期之前完成低年级课程及通识类课程等学分要求.<br>概率论与数理统计选课咨询17系63600282，mjjiang@ustc.edu.cn;大学物理实验请咨询物理实验中心63601863第一教学楼229室clh@ustc.edu.cn。光学、力学、热学、原子物理、电磁学咨询物理学院63600719，Lxh89@ustc.edu.cn。体育课选课咨询：体育教学中心 63601969，yxd1991@ustc.edu.cn;上课地点ARTS404/ARTS403基础体育为女生课；英语课选课咨询：外语教学中心 63601920，english6@ustc.edu.cn；思政课选课咨询：马克思主义学院 63607992 xfen68@ustc.edu.cn";
        JLabel info = new JLabel(text);
        info.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        info.setBounds(20, 20, 450, 305);
        return info;
    }

    /**
     * Change password.
     *
     * @param teacher the teacher
     */
    public static void changePassword (Teacher teacher){

        JFrame frame = new JFrame("修改密码");
        JLabel oldPasswordLabel = new JLabel("输入原密码:");
        oldPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        JPasswordField oldPasswordField = new JPasswordField(20);

        JLabel newPasswordLabel = new JLabel("输入新密码:");
        newPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        JPasswordField newPasswordField = new JPasswordField(20);

        //占位标签
        JLabel occupancy = new JLabel();

        JButton changeButton = new JButton("修改");
        changeButton.setForeground(Color.BLACK);
        changeButton.setBackground(new Color(225, 255, 255));
        changeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeButton.setFocusPainted(false);
        JFrame tempFrame = new JFrame();
        tempFrame.pack();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(oldPasswordLabel);
        panel.add(oldPasswordField);
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(occupancy);
        panel.add(changeButton);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        changeButton.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());

            if (teacher.getPassword().equals(oldPassword)) {
                TeacherDao.updatePassword(teacher.getId(), newPassword);
                teacher.setPassword(newPassword);
                frame.dispose();
            } else {
                Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                UIUtil.showScaledIconMessage(scrollPane, "原密码错误！", "提示", customIcon);
            }
        });
    }

    /**
     * Gets message.
     *
     * @param teacherName the teacher name
     * @return the message
     */

    public static JLabel getMessage(String teacherName) {

        String text = TeacherCourseDao.getCourseNumAndName(teacherName);
        JLabel info = new JLabel(text);
        info.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        info.setBounds(20, 200, 450, 305);
        return info;
    }

}


