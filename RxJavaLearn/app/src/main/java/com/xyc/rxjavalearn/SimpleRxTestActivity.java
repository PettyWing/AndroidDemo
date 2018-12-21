package com.xyc.rxjavalearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by xieyusheng on 2018/11/9.
 */

public class SimpleRxTestActivity extends AppCompatActivity {

    private static final String TAG = "SimpleRxTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        delayRx();
    }

    public void onLogout(View view) {
        startActivity(new Intent(SimpleRxTestActivity.this, MainActivity.class));
    }

    /**
     * 简单的创建rxjava
     */
    private void simpleRx() {
        /**
         * 基本创建
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        });

        /**
         * just（）创建
         */
//        Observable observable = Observable.just(1, 2, 3, 4);

        /**
         * fromArray创建
         */
//        Integer[] items = {1, 2, 3, 4};
//        Observable observable = Observable.fromArray(items);

        /**
         * fromIterable创建
         */
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        Observable observable = Observable.fromIterable(list);

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    private void delayRx() {
        // 该例子 = 延迟2s后，发送一个long类型数值
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
        /**
         * 发送的事件序列 = 从0开始、无限递增1的的整数序列
         */
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(3, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

        /**
         * a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
         b. 作用类似于interval（），但可指定发送的数据的数量
         * 参数说明：
         // 参数1 = 事件序列起始点；
         // 参数2 = 事件数量；
         // 参数3 = 第1次事件延迟发送时间；
         // 参数4 = 间隔时间数字；
         // 参数5 = 时间单位
         */
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

        /**
         * a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
         b. 作用类似于intervalRange（），但区别在于：无延迟发送事件s
         */
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.range(3, 10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

    }
}
