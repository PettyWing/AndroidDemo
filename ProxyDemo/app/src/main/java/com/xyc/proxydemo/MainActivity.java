package com.xyc.proxydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new TestApi().log();

        TestApi2 api = new TestApi2();
        // 构建一个动态代理
        HockTestApiProxy proxy = new HockTestApiProxy(api);
        // 获取被代理的ClassLoader
        ClassLoader loader = api.getClass().getClassLoader();
        // 构造打理函数
        Api test = (Api) Proxy.newProxyInstance(loader, new Class[]{Api.class}, proxy);
        test.log();
        test.log2();


    }

}
