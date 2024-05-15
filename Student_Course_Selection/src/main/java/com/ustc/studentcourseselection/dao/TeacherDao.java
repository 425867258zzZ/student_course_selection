package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.util.DBconnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author ������
 */

public class TeacherDao {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //add����
        /*
        try {
            BaseObject object = Factory.createModel(Model.Teacher,2,"����","PT23110003","��","�������ѧ�뼼��","������ƽ�����ʵ��","123456",BaseUtils.getTime(),BaseUtils.getTime());
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            dao.add(object);
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Creation failed");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //del����
        /*
        try {
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            dao.del("PT23110003"); // ɾ������Ϊ "PT23110003" �Ľ�ʦ
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Deletion failed"); // ɾ��ʧ��ʱ���������Ϣ
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //update����
        /*
        try {
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            boolean updateResult = dao.update("PT23110002","Name","����");
            if (updateResult) {
                System.out.println("���³ɹ�");
            } else {
                System.out.println("����ʧ��");
            }
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("����ʧ��");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //query����
        /*String Number = "PT23110002";

        // ���ò�ѯ����
        TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
        BaseObject result = dao.query(Number);

        // ��ӡ��ѯ���
        if (result != null && result instanceof Teacher) {
            Teacher teacher = (Teacher) result;
            System.out.println("��ѯ����ʦ��Ϣ��");
            System.out.println("��ʦID: " + teacher.getId());
            System.out.println("����: " + teacher.getName());
            System.out.println("���: " + teacher.getNumber());
            System.out.println("�Ա�: " + teacher.getGender());
            System.out.println("��������: " + teacher.getDepartment());
            System.out.println("���ڿγ�: " + teacher.getCourse());
            System.out.println("����ʱ��: " + teacher.getCreateTime());
            System.out.println("����ʱ��: " + teacher.getUpdateTime());
        } else {
            System.out.println("δ�ҵ�ƥ��Ľ�ʦ��Ϣ��");
        }*/

        //queryAll����
        /*TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
        Vector<Vector> teachersData = dao.queryAll();
        // ��ӡ��ѯ���
        System.out.println("���н�ʦ��Ϣ��");
        for (Vector<String> teacherRow : teachersData) {
            for (String data : teacherRow) {
                System.out.print(data + " ");
            }
            System.out.println();
        }*/

    }
    private static boolean sqlDeal(Teacher teacher, Connection connection, String sql) {
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setInt(1, teacher.getId());
                    ps.setString(2, teacher.getName());
                    ps.setString(3, teacher.getNumber());
                    ps.setString(4, teacher.getGender());
                    ps.setString(5, teacher.getDepartment());
                    ps.setString(6, teacher.getCourse());
                    ps.setString(7, teacher.getPassword());
                    ps.setString(8, BaseUtils.getTime());
                    ps.setString(9, BaseUtils.getTime());
                }
                boolean result = ps != null && ps.executeUpdate() > 0;
                DBconnection.closeConnection(null, null, connection);
                return result;
            } else {
                System.out.println("Connection is null");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close PreparedStatement: " + e.getMessage());
                }
            }
        }
    }

    /**
     * �����ʦ
     *
     * @param baseObject ��ʦ
     * @return ����ֵ
     */

    public boolean add(BaseObject baseObject) {
        Teacher teacher = (Teacher) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO teachers(Id,Name,Number,Gender,Department,Course,Password,CreateTime,UpdateTime) VALUES(?,?,?,?,?,?,?,?,?)";
        return sqlDeal(teacher, connection, sql1);
    }

    /**
     * ɾ����ʦ
     *
     * @param number ����
     * @return ����ֵ
     */

    public boolean del(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "DELETE FROM teachers WHERE number = ?";
        try {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setString(1, number);
            }
            boolean result = false;
            if (ps != null) {
                result = ps.executeUpdate() > 0;
            }
            DBconnection.closeConnection(null, ps, connection);
            return result;
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        return false;
    }

    /**
     * ������ʦ��Ϣ
     *
     * @param number       ����
     * @param columnName   Ҫ���µ�����
     * @param columnValue  Ҫ���µ��е���ֵ
     * @return �����Ƿ�ɹ��Ĳ���ֵ
     */

    public boolean update(String number, String columnName, String columnValue) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // ��ȡ���ݿ�����
            connection = DBconnection.getConnection();

            // ��д SQL ���
            String sql = "UPDATE teachers SET " + columnName + " = ? , UpdateTime = ?  WHERE Number = ?";

            // ���� PreparedStatement ����
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }

            // ���ò���ֵ
            if (preparedStatement != null) {
                preparedStatement.setString(1, columnValue);
                preparedStatement.setString(2, BaseUtils.getTime());
                preparedStatement.setString(3, number);
            }

            // ִ�и��²���
            int rowsAffected = 0;
            if (preparedStatement != null) {
                rowsAffected = preparedStatement.executeUpdate();
            }

            // ������Ӱ��������жϸ����Ƿ�ɹ�
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // ����ʧ��
            return false;
        } finally {
            // �ر����Ӻ� PreparedStatement ����
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ��ѯ������ʦ
     *
     * @param number ѧ��
     * @return ��ʦ
     */

    public BaseObject query(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM teachers WHERE number = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Teacher teacher = new Teacher (rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Number"), rs.getString("Gender"),
                        rs.getString("Department"), rs.getString("Course"),
                        rs.getString("Password"), rs.getString("CreateTime"),
                        rs.getString("UpdateTime"));
                DBconnection.closeConnection(rs, ps, connection);
                return teacher;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����������ʦ
     *
     * @return Array<teacher>
     */

    public Vector<Vector> queryAll() {
        Connection connection = DBconnection.getConnection();
        String sql = "SELECT * FROM teachers";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Vector<Vector> teachersData = new Vector<>();
            while (rs.next()) {
                Vector<String> teacherRow = new Vector<>();
                teacherRow.add(String.valueOf(rs.getInt("Id")));
                teacherRow.add(rs.getString("Name"));
                teacherRow.add(rs.getString("Number"));
                teacherRow.add(rs.getString("Gender"));
                teacherRow.add(rs.getString("Department"));
                teacherRow.add(rs.getString("Course"));
                teacherRow.add(rs.getString("Password"));
                teacherRow.add(rs.getString("CreateTime"));
                teacherRow.add(rs.getString("UpdateTime"));
                teachersData.add(teacherRow);
            }
            DBconnection.closeConnection(rs, null, connection);
            return teachersData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
