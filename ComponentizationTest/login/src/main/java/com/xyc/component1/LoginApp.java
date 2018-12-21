package com.xyc.component1;

import android.app.Application;

import com.xyc.base.BaseApp;
import com.xyc.componentbase.ServiceFactory;

/**
 * Created by xieyusheng on 2018/12/20.
 */

public class LoginApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        // 将 AccountService 类的实例注册到 ServiceFactory
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new AccountService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
