package com.xyc.componentizationtest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xyc.base.AppConfig;
import com.xyc.base.BaseApp;

/**
 * Created by xieyusheng on 2018/12/20.
 */

// 主 Module 的 Applicaiton
public class MainApplication extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 ARouter
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

        // 初始化组件 Application
        initModuleApp(this);
        // 所有 Application 初始化后的操作
        initModuleData(this);

    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void initModuleApp(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleApp(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initModuleData(Application application) {
        for (String moduleApp : AppConfig.moduleApps) {
            try {
                Class clazz = Class.forName(moduleApp);
                BaseApp baseApp = (BaseApp) clazz.newInstance();
                baseApp.initModuleData(this);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}