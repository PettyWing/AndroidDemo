### 1. 基础属性
- smallIcon -->小图标  （必选）
- largeIcon -->标题  （必选）
- contentText -->详细内容  （必选）
- ticker --> 状态栏文字，当通知出现时候，会在顶端有一个介绍，就像我们用QQ，当某人发来信息的时候，会出现"您有一条新的信息"字样
- contentTitle -->标题
- contentInfo -->标题栏右侧的文本 ==(魅蓝note3上面会显示在标题栏下方！！，必要的话要做兼容性适配)==
- color --> 5.0及以上版本smallIcon的背景色,默认为灰色
- when --> 设置该条通知时间,默认为当前时间
- autoCancle --> 设置为true，点击该条通知会自动删除，false时只能通过滑动来删除
- priority --> 设置优先级，级别高的排在前面
- default --> 设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
- ongoing --> 设置为一个正在进行的通知，此时用户无法清除通知    
- 其他属性详见 [Android Developer Notification](https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html)

### 2.单文本通知
* step1 初始化builder和manager
    ```
    mBuilder = new NotificationCompat.Builder(context);
    mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    ```
 
*  step2 添加通知的基础数据

    ```
     /**
     * 通知栏的基础信息
     *
     * @param content     通知栏详细信息
     * @param ticker      状态栏文字
     * @param title       通知栏标题
     * @param contentInfo 通知栏右侧的文本
     */
    private void createBaseInfo(String content, String ticker, String title, String contentInfo) {
        //必须要设置的属性
        
        //通知栏小图标
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        //通知栏大图标
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        //通知栏详细信息
        mBuilder.setContentText(content);

        //非必填属性

        //状态栏文字，当通知出现时候，会在顶端有一个介绍，就像我们用QQ，当某人发来信息的时候，会出现"您有一条新的信息"字样
        mBuilder.setTicker(ticker);
        //通知栏标题，
        mBuilder.setContentTitle(title);
        //显示通知栏右侧的文本 (魅蓝note3上面会显示在标题栏下方！！，必要的话要做兼容性适配)
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
    ```
    
* step3 发送消息

    
    ```
    /**
     * 发送
     * @param notification    
     */
    private void send(Notification notification) {
        mNotificationManager.notify(messageId, notification);
    }
    ```

### 3. 带PendIntent的通知
    
* 可以通过设置pendIntent属性给通知添加OnClick事件
* pendIntent

    ```
    PendingIntent pIntent = PendingIntent.getActivity(
                            MainActivity.this,
                            (int) SystemClock.uptimeMillis(),
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
    ```

   flags有四种不同的值：
1. FLAG_CANCEL_CURRENT：如果构建的PendingIntent已经存在，则取消前一个，重新构建一个。
1. FLAG_NO_CREATE：如果前一个PendingIntent已经不存在了，将不再构建它。
1. FLAG_ONE_SHOT：表明这里构建的PendingIntent只能使用一次。
1. FLAG_UPDATE_CURRENT：如果构建的PendingIntent已经存在，那么系统将不会重复创建，只是把之前不同的传值替换掉。通常做法就是在构建PendingIntent的时候传入不一样的requestCode来更新PendingIntent  
* 通过设置ContentIntent属性添加事件
  
    ```
    mBuilder.setContentIntent(pIntent);
    ```
### 4. 显示多行消息的通知
  api16开始，Android支持大图，多行文字的通知
  
-   多行文字
  
    ```
    /**
         * 显示多行消息的show方法
         *
         * @param content     通知栏详细信息
         * @param ticker      状态栏文字
         * @param title       通知栏标题
         * @param contentInfo 通知栏右侧的文本
         */
        public void showMoreLine(String content, String ticker, String title, String contentInfo) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                Toast.makeText(context, "您的手机低于Android 4.1.2，不支持！！", Toast.LENGTH_SHORT).show();
                return;
            }
            createBaseInfo(content, ticker, title, contentInfo);
            Notification notification = new NotificationCompat
                    .BigTextStyle(mBuilder)
                    .bigText(content)
                    .build();
            send(notification);
        }
    ```
### 5. 注意点
`
mNotificationManager.notify(messageId, notification);
`
==这里的messageId是个很关键的参数，一般的demo都是提供一个具体的数值，但是这个数值相当于是消息的唯一识别id，如果不改变这个值，新产生的通知只会覆盖上一个通知！！！==

