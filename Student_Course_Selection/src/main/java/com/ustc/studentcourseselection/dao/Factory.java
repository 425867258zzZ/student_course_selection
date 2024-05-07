package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 工厂类,用于创建一个model实例
 *
 * @author 孟梓晗
 */
public class Factory {
    /**
     * 创建实体类
     *
     * @param model 实体类
     * @param args  构造函数对应的参数
     * @return 实体类
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
     * 返回参数的原始数据类型数组
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
