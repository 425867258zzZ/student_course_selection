package com.ustc.studentcourseselection.view;

import com.ustc.studentcourseselection.controller.LoginUtils;
import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.mainmenu.student.StudentMenu;
import com.ustc.studentcourseselection.view.mainmenu.teacher.TeacherMenu;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;


/**
 * µÇÂ¼½çÃæ
 *
 * @author ÃÏè÷êÏ
 */
public class Login extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public Login() {
        setFont(new Font("¿¬Ìå", Font.PLAIN, 12));
        setTitle("Ñ¡¿ÎÏµÍ³µÇÂ¼");
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

        JLabel lblNewLabel = new JLabel("Í³Ò»Éí·ÝÈÏÖ¤ÏµÍ³");
        lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 21));
        lblNewLabel.setBounds(125, 120, 180, 28);
        contentPane.add(lblNewLabel);

        JTextField numberField = new JTextField();
        numberField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        numberField.setBorder(border);
        numberField.setColumns(10);
        numberField.setBounds(90, 201, 290, 28);
        contentPane.add(numberField);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
        passwordField.setBorder(border);
        passwordField.setColumns(10);
        passwordField.setBounds(90, 259, 290, 28);
        contentPane.add(passwordField);


        JLabel userNameBt = new JLabel("ÓÃ»§Ãû:");
        userNameBt.setForeground(new Color(0, 0, 0));
        userNameBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
        userNameBt.setBounds(36, 200, 95, 28);
        contentPane.add(userNameBt);


        JLabel userPasswordLb = new JLabel("ÃÜÂë:");
        userPasswordLb.setForeground(Color.BLACK);
        userPasswordLb.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
        userPasswordLb.setBounds(50, 257, 95, 28);
        contentPane.add(userPasswordLb);

        JButton loginBt = new JButton("µÇÂ¼");
        loginBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
        loginBt.setBounds(285, 318, 95, 34);
        loginBt.setBorder(null);
        loginBt.setFocusPainted(false);
        loginBt.addActionListener(e -> {

            String numberInput = numberField.getText();
            String password = new String(passwordField.getPassword());
            BaseObject object = LoginUtils.loginConfirm(numberInput, password);
            if (object instanceof Student) {
                Student student = (Student) object;
                StudentMenu mainWindow = new StudentMenu(student);
                mainWindow.setVisible(true);
                // ¹Ø±ÕµÇÂ¼½çÃæ
                dispose();
            } else if (object instanceof Teacher) {
                Teacher teacher = (Teacher) object;
                TeacherMenu mainWindow = new TeacherMenu(teacher);
                mainWindow.setVisible(true);
                // ¹Ø±ÕµÇÂ¼½çÃæ
                dispose();
            } else {
                // Èç¹ûÑéÖ¤Ê§°Ü£¬ÏÔÊ¾´íÎóÏûÏ¢
                JOptionPane.showMessageDialog(Login.this, "ÓÃ»§Ãû»òÃÜÂë´íÎó", "µÇÂ¼Ê§°Ü", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(loginBt);

        JLabel findPasswordBt = new JLabel("ÕÒ»ØÃÜÂë");
        findPasswordBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 11));
        findPasswordBt.setForeground(Color.BLACK);
        findPasswordBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        findPasswordBt.setBounds(238, 320, 46, 34);
        contentPane.add(findPasswordBt);


        JLabel updatePasswordBt = new JLabel("ÐÞ¸Ä");
        updatePasswordBt.setForeground(Color.BLACK);
        updatePasswordBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 11));
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


}


