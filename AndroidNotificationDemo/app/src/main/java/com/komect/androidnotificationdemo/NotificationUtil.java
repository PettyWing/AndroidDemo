package com.komect.androidnotificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

/**
 * Created by xieyusheng on 2018/2/11.
 */

public class NotificationUtil {

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    private Context context;
    private int messageId = -1; // 同一个messageId的消息会相互覆盖！！

    public NotificationUtil(Context context) {
        this.context = context;
        mBuilder = new NotificationCompat.Builder(context);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public NotificationUtil(Context context, int messageId) {
        this.context = context;
        mBuilder = new NotificationCompat.Builder(context);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.messageId = messageId;
    }

    /**
     * 只显示基础信息的show方法
     *
     * @param content     通知栏详细信息
     * @param ticker      状态栏文字
     * @param title       通知栏标题
     * @param contentInfo 通知栏右侧的文本
     */
    public void show(String content, String ticker, String title, String contentInfo) {

        createBaseInfo(content, ticker, title, contentInfo);
        send();
    }

    /**
     * 只显示基础信息的show方法
     *
     * @param content     通知栏详细信息
     * @param ticker      状态栏文字
     * @param title       通知栏标题
     * @param contentInfo 通知栏右侧的文本
     * @param pIntent     点击后的操作
     */
    public void show(String content, String ticker, String title, String contentInfo, PendingIntent pIntent) {
        createBaseInfo(content, ticker, title, contentInfo);
        mBuilder.setContentIntent(pIntent);
        send();
    }


    /**
     * 通知栏的基础信息
     *
     * @param content     通知栏详细信息
     * @param ticker      状态栏文字
     * @param title       通知栏标题
     * @param contentInfo 通知栏右侧的文本
     */
    private void createBaseInfo(String content, String ticker, String title, String contentInfo) {

        /*必须要设置的属性*/

        //通知栏小图标
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        //通知栏大图标
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        //通知栏详细信息
        mBuilder.setContentText(content);

        /*非必填属性*/

        //状态栏文字，当通知出现时候，会在顶端有一个介绍，就像我们用QQ，当某人发来信息的时候，会出现"您有一条新的信息"字样
        mBuilder.setTicker(ticker);
        //通知栏标题，
        mBuilder.setContentTitle(title);
        //显示通知栏右侧的文本
        mBuilder.setContentInfo(contentInfo);
        //5.0及以上版本smallIcon的背景色,默认为灰色
        mBuilder.setColor(Color.TRANSPARENT);
        //设置该条通知时间,默认为当前时间
        mBuilder.setWhen(System.currentTimeMillis());
        //设置为true，点击该条通知会自动删除，false时只能通过滑动来删除
        mBuilder.setAutoCancel(true);
        //设置优先级，级别高的排在前面
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        //设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
        mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        //设置为一个正在进行的通知，此时用户无法清除通知
        mBuilder.setOngoing(false);
    }

    /**
     * 发送
     */
    private void send() {
        mNotificationManager.notify(messageId, mBuilder.build());
    }

}
