package com.ustc.studentcourseselection.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 基本工具类
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
}
