package com.ustc.studentcourseselection.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ����������
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
}
