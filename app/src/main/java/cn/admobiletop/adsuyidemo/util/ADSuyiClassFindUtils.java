package cn.admobiletop.adsuyidemo.util;

import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by linjiang on 2019/3/5.
 */

public class ADSuyiClassFindUtils {

    static {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Class classClazz = Class.class;
                // light greyList
                Field classLoaderField = classClazz.getDeclaredField("classLoader");
                classLoaderField.setAccessible(true);
                classLoaderField.set(ADSuyiClassFindUtils.class, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Class<?> forName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /**
     * 根据完整路径类名反射获取对象
     *
     * @param className ：完整路径类名
     * @param <T>       ：对象类型
     * @return ：反射对象
     */
    public static <T> T reflexClass(String className) {
        try {
            Class clz = Class.forName(className);
            return (T) clz.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    public static Field getDeclaredField(Class<?> clz, String name) throws NoSuchFieldException {
        return clz.getDeclaredField(name);
    }

    public static Method getDeclaredMethod(Class<?> clz, String name, Class<?>... parameterType)
            throws NoSuchMethodException {
        return clz.getDeclaredMethod(name, parameterType);
    }




}