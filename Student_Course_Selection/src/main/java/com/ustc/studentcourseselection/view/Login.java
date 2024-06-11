package com.ustc.studentcourseselection.view;

import com.ustc.studentcourseselection.controller.LoginUtils;
import com.ustc.studentcourseselection.model.Administrator;
import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.mainmenu.manager.ManagerMenu;
import com.ustc.studentcourseselection.view.mainmenu.student.StudentMenu;
import com.ustc.studentcourseselection.view.mainmenu.teacher.TeacherMenu;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;


/**
 * 登录界面
 *
 * @author 孟梓晗
 */
public class Login extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public Login() {
        setFont(new Font("楷体", Font.PLAIN, 12));
        setTitle("选课系统登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 430, 554);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);


        ImageIcon ustcTitle = UIUtil.loadPic("src/main/resources/images/USTC_Title.png", 380, 100);
        JLabel ustcLogo = new JLabel(ustcTitle);
        ustcLogo.setBounds(5, 5, 406, 80);
        ustcLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(ustcLogo);

        JLabel lblNewLabel = new JLabel("统一身份认证系统");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 21));
        lblNewLabel.setBounds(125, 120, 180, 28);
        contentPane.add(lblNewLabel);

        JTextField numberField = new JTextField();
        numberField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        numberField.setBorder(border);
        numberField.setColumns(10);
        numberField.setBounds(90, 201, 290, 28);
        contentPane.add(numberField);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField.setBorder(border);
        passwordField.setColumns(10);
        passwordField.setBounds(90, 259, 290, 28);
        contentPane.add(passwordField);


        JLabel userNameBt = new JLabel("用户名:");
        userNameBt.setForeground(new Color(0, 0, 0));
        userNameBt.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        userNameBt.setBounds(36, 200, 95, 28);
        contentPane.add(userNameBt);


        JLabel userPasswordLb = new JLabel("密码:");
        userPasswordLb.setForeground(Color.BLACK);
        userPasswordLb.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        userPasswordLb.setBounds(50, 257, 95, 28);
        contentPane.add(userPasswordLb);

        JButton loginBt = getjButton(numberField, passwordField);
        contentPane.add(loginBt);

        JLabel findPasswordBt = new JLabel("找回密码");
        findPasswordBt.setFont(new Font("微软雅黑", Font.PLAIN, 11));
        findPasswordBt.setForeground(Color.BLACK);
        findPasswordBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        findPasswordBt.setBounds(238, 320, 46, 34);
        contentPane.add(findPasswordBt);


        JLabel updatePasswordBt = new JLabel("修改");
        updatePasswordBt.setForeground(Color.BLACK);
        updatePasswordBt.setFont(new Font("微软雅黑", Font.PLAIN, 11));
        updatePasswordBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updatePasswordBt.setBounds(211, 320, 33, 34);
        contentPane.add(updatePasswordBt);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @NotNull
    private JButton getjButton(JTextField numberField, JPasswordField passwordField) {
        JButton loginBt = new JButton("登录");
        loginBt.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        loginBt.setBounds(285, 318, 95, 34);
        loginBt.setBorder(null);
        loginBt.setFocusPainted(false);
        loginBt.addActionListener(e -> {

            String numberInput = numberField.getText();
            String password = new String(passwordField.getPassword());
            BaseObject object = LoginUtils.loginConfirm(numberInput, password);
            if (object instanceof Student student) {
                StudentMenu mainWindow = new StudentMenu(student);
                mainWindow.setVisible(true);
                // 关闭登录界面
                dispose();
            } else if (object instanceof Teacher teacher) {
                TeacherMenu mainWindow = new TeacherMenu(teacher);
                mainWindow.setVisible(true);
                // 关闭登录界面
                dispose();
            } else if (object instanceof Administrator administrator) {
                ManagerMenu mainWindow = new ManagerMenu();
                mainWindow.setVisible(true);
                dispose();
            } else {
                // 如果验证失败，显示错误消息
                JOptionPane.showMessageDialog(Login.this, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        });
        return loginBt;
    }


}


