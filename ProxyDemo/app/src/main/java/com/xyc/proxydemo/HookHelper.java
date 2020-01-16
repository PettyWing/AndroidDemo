package com.xyc.proxydemo;

import android.location.LocationManager;

import java.lang.reflect.Field;

/**
 * Created by xieyusheng on 2020/1/3.
 */

public class HookHelper {

    public static void hookLocationManager(LocationManager locationManager) {
        try {
            Object iLocationManager = null;
            Class<?> locationManagerClazsz = Class.forName("com.xyc.proxydemo.TestApi");
            //获取LocationManager的mService成员
            iLocationManager = getField(locationManagerClazsz, locationManager, "word");

            Class<?> iLocationManagerClazz = Class.forName("com.xyc.proxydemo.TestApi");

            //创建代理类
//            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//                    new Class<?>[]{iLocationManagerClazz}, new HockTestApiProxy(iLocationManager));

            //在这里移花接木，用代理类替换掉原始的ILocationManager
//            setField(locationManagerClazsz, locationManager, "word", proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getField(Class clazz, Object target, String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }

    public static void setField(Class clazz, Object target, String name, Object value) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(target, value);
    }

}
