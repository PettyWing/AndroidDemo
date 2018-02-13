package com.komect.androidsharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * 对系统API SharedPreferences的封装
 * Author by hf
 * Create on 16/9/18
 */
public class SPUtil {
    private final SharedPreferences sp;

    /**
     * @param context 上下文
     */
    public SPUtil(@NonNull Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * @param context 上下文
     * @param file    文件名称
     * @param mode    加载模式,例如Context.MODE_PRIVATE
     */
    public SPUtil(@NonNull Context context, @NonNull String file,
                  @NonNull int mode) {
        sp = context.getSharedPreferences(file, mode);
    }

    /**
     * 删除对应的键值对
     * @param key 待删除的key
     */
    public void remove(String key) {
        Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final int value) {
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final Integer value) {
        if (null != value) {
            Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final long value) {
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final Long value) {
        if (null != value) {
            Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final float value) {
        Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final Float value) {
        if (null != value) {
            Editor editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putString(final String key, final String value) {
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final boolean value) {
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final Boolean value) {
        if (null != value) {
            Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }
}