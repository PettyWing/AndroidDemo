package com.xyc.base;

public class AppConfig {
    // 对应登录页面的application包名，用于在主module即app module的application通过反射进行初始化
    private static final String LoginApp = "com.xyc.component1.LoginApp";

    public static String[] moduleApps = {
            LoginApp
    };
}
