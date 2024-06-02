package com.ustc.studentcourseselection.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 基本工具类
 *
 * @author 孟梓晗
 */
public final class BaseUtils {
    /**
     * 获取当前时间
     *
     * @return 当前时间的格式化字符串, 统一依照"yyyy-MM-dd HH:mm:ss"格式
     */
    public static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 读取某一课程的时间
     *
     * @param courseTime 课程时间。必须按照格式要求
     * @return (5, 13)二维数组, 第一维表示星期，第二维表示上课时间
     */
    public static int[][] readCourseTime(String courseTime) {
        int[][] time = new int[5][13];
        String[] fCourseTimes = courseTime.split("，");
        for (String fTime : fCourseTimes) {
            //获取具体哪一天
            String day = fTime.substring(0, 2);
            //获取上课节数，{1,2,3}String数组
            String[] timeRange = fTime.substring(3, fTime.length() - 1).split("、");
            int rowindex;
            switch (day) {
                case "周一" -> rowindex = 0;
                case "周二" -> rowindex = 1;
                case "周三" -> rowindex = 2;
                case "周四" -> rowindex = 3;
                default -> rowindex = 4;
            }
            //time表中标记1
            for (String t : timeRange) {
                time[rowindex][Integer.parseInt(t) - 1] = 1;
            }
        }
        return time;
    }
}
