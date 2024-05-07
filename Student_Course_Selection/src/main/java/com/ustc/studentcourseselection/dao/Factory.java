package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ������,���ڴ���һ��modelʵ��
 *
 * @author ������
 */
public class Factory {
    /**
     * ����ʵ����
     *
     * @param model ʵ����
     * @param args  ���캯����Ӧ�Ĳ���
     * @return ʵ����
     */
    public static BaseObject createModel(Model model, Object... args) {
        try {
            Class<?> clazz = Class.forName("com.ustc.studentcourseselection.model." + model.name());
            Constructor<?> constructor = clazz.getConstructor(getParameterTypes(args));
            return (BaseObject) constructor.newInstance(args);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * ���ز�����ԭʼ������������
     */
    private static Class<?>[] getParameterTypes(Object[] args) {
        Class<?>[] parameterType = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Integer) {
                parameterType[i] = int.class;
            } else if (args[i] instanceof Boolean) {
                parameterType[i] = boolean.class;
            } else {
                parameterType[i] = args[i].getClass();
            }
        }
        return parameterType;
    }

}
