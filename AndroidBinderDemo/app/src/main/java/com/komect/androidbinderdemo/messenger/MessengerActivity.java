package com.komect.androidbinderdemo.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.komect.androidbinderdemo.Constants;
import com.komect.androidbinderdemo.R;

/**
 * Created by xieyusheng on 2018/7/13.
 */

public class MessengerActivity extends Activity {
    private static final String TAG = "MessengerActivity";
    private Messenger mService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MSG_FROM_SERVICE:
                    // 获取消息
                    Bundle bundle = msg.getData();
                    Log.d(TAG, "handleMessage: " + bundle.get(Constants.KEY_REPLY));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    public void sendMsgClick(View view) {

        Message message = Message.obtain(null, Constants.MSG_FROM_CLIENT);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_SEND, "hello world");
        message.setData(bundle);

        // 如果需要reply，添加如下语句
        message.replyTo = mGetReplyMessenger;
        try {
            mService.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
