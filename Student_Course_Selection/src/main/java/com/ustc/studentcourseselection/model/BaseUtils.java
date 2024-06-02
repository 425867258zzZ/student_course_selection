package com.ustc.studentcourseselection.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ����������
 *
 * @author ������
 */
public final class BaseUtils {
    /**
     * ��ȡ��ǰʱ��
     *
     * @return ��ǰʱ��ĸ�ʽ���ַ���, ͳһ����"yyyy-MM-dd HH:mm:ss"��ʽ
     */
    public static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * ��ȡĳһ�γ̵�ʱ��
     *
     * @param courseTime �γ�ʱ�䡣���밴�ո�ʽҪ��
     * @return (5, 13)��ά����, ��һά��ʾ���ڣ��ڶ�ά��ʾ�Ͽ�ʱ��
     */
    public static int[][] readCourseTime(String courseTime) {
        int[][] time = new int[5][13];
        String[] fCourseTimes = courseTime.split("��");
        for (String fTime : fCourseTimes) {
            //��ȡ������һ��
            String day = fTime.substring(0, 2);
            //��ȡ�Ͽν�����{1,2,3}String����
            String[] timeRange = fTime.substring(3, fTime.length() - 1).split("��");
            int rowindex;
            switch (day) {
                case "��һ" -> rowindex = 0;
                case "�ܶ�" -> rowindex = 1;
                case "����" -> rowindex = 2;
                case "����" -> rowindex = 3;
                default -> rowindex = 4;
            }
            //time���б��1
            for (String t : timeRange) {
                time[rowindex][Integer.parseInt(t) - 1] = 1;
            }
        }
        return time;
    }
}
