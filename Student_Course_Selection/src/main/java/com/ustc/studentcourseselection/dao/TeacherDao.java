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
 * @author 潘义良
 */

public class TeacherDao {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //add测试
        /*
        try {
            BaseObject object = Factory.createModel(Model.Teacher,2,"丁箐","PT23110003","男","计算机科学与技术","程序设计进阶与实践","123456",BaseUtils.getTime(),BaseUtils.getTime());
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            dao.add(object);
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Creation failed");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //del测试
        /*
        try {
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            dao.del("PT23110003"); // 删除工号为 "PT23110003" 的教师
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Deletion failed"); // 删除失败时输出错误信息
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //update测试
        /*
        try {
            TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
            boolean updateResult = dao.update("PT23110002","Name","丁箐");
            if (updateResult) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("更新失败");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/

        //query测试
        /*String Number = "PT23110002";

        // 调用查询方法
        TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
        BaseObject result = dao.query(Number);

        // 打印查询结果
        if (result != null && result instanceof Teacher) {
            Teacher teacher = (Teacher) result;
            System.out.println("查询到教师信息：");
            System.out.println("教师ID: " + teacher.getId());
            System.out.println("姓名: " + teacher.getName());
            System.out.println("编号: " + teacher.getNumber());
            System.out.println("性别: " + teacher.getGender());
            System.out.println("所属部门: " + teacher.getDepartment());
            System.out.println("所授课程: " + teacher.getCourse());
            System.out.println("创建时间: " + teacher.getCreateTime());
            System.out.println("更新时间: " + teacher.getUpdateTime());
        } else {
            System.out.println("未找到匹配的教师信息。");
        }*/

        //queryAll测试
        /*TeacherDao dao = (TeacherDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Teacher.name() + "Dao").getConstructor().newInstance();
        Vector<Vector> teachersData = dao.queryAll();
        // 打印查询结果
        System.out.println("所有教师信息：");
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
     * 添加老师
     *
     * @param baseObject 老师
     * @return 布尔值
     */

    public boolean add(BaseObject baseObject) {
        Teacher teacher = (Teacher) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO teachers(Id,Name,Number,Gender,Department,Course,Password,CreateTime,UpdateTime) VALUES(?,?,?,?,?,?,?,?,?)";
        return sqlDeal(teacher, connection, sql1);
    }

    /**
     * 删除老师
     *
     * @param number 工号
     * @return 布尔值
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
     * 更新老师信息
     *
     * @param number       工号
     * @param columnName   要更新的列名
     * @param columnValue  要更新的列的新值
     * @return 更新是否成功的布尔值
     */

    public boolean update(String number, String columnName, String columnValue) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = DBconnection.getConnection();

            // 编写 SQL 语句
            String sql = "UPDATE teachers SET " + columnName + " = ? , UpdateTime = ?  WHERE Number = ?";

            // 创建 PreparedStatement 对象
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            }

            // 设置参数值
            if (preparedStatement != null) {
                preparedStatement.setString(1, columnValue);
                preparedStatement.setString(2, BaseUtils.getTime());
                preparedStatement.setString(3, number);
            }

            // 执行更新操作
            int rowsAffected = 0;
            if (preparedStatement != null) {
                rowsAffected = preparedStatement.executeUpdate();
            }

            // 根据受影响的行数判断更新是否成功
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // 更新失败
            return false;
        } finally {
            // 关闭连接和 PreparedStatement 对象
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
     * 查询单个老师
     *
     * @param number 学号
     * @return 老师
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
     * 返回所有老师
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
