package com.komect.androidbinderdemo.socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.komect.androidbinderdemo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by xieyusheng on 2018/7/17.
 */

public class TCPClientActivity extends Activity {
    private static final String TAG = "TCPClientActivity";
    public static final int MSG_RECEIVE_NEW_MSG = 1;
    public static final int MSG_SOKCET_CONNECTED = 2;

    private TextView msgTextView;
    private Button mSendButton;

    private Socket mClientSocket;
    private PrintWriter printWriter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);
        msgTextView = (TextView) findViewById(R.id.msg_container);
        mSendButton = (Button) findViewById(R.id.send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (printWriter != null) {
                    printWriter.println("测试数据");
                    msgTextView.setText("self -- " + "测试数据" + "\n");
                }
            }
        });
        startService();
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    private void connectTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MSG_SOKCET_CONNECTED);
                // 服务连接成功
            } catch (IOException e) {
                SystemClock.sleep(1000);
                Log.d(TAG, "connectTCPServer: 连接服务器重试");
                e.printStackTrace();
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                if (msg != null) {
                    String showMsg = "server -- " + msg + "\n";
                    mHandler.obtainMessage(MSG_RECEIVE_NEW_MSG, showMsg).sendToTarget();
                }
            }
            printWriter.close();
            br.close();
            socket.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void startService() {
        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RECEIVE_NEW_MSG:
                    msgTextView.setText(msgTextView.getText() + (String) msg.obj);
                    break;
                case MSG_SOKCET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

}
