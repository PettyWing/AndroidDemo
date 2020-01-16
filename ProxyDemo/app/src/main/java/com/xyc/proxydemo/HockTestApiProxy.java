package com.xyc.proxydemo;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xieyusheng on 2020/1/3.
 */

public class HockTestApiProxy implements InvocationHandler {
    private static final String TAG = "HockTestApiProxy";
    private Api obj;

    public HockTestApiProxy(Api obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, "invoke: before");
        Object result = method.invoke(obj, args);
        Log.d(TAG, "invoke: after");
        return result;
    }
}