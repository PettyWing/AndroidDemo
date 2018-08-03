package com.komect.androidbinderdemo.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by xieyusheng on 2018/7/17.
 */

public class TCPServerService extends Service {

    private static final String TAG = "TCPServerService";
    private boolean isServiceDestoryed = false;
    private String[] msgs = new String[]{
            "a", "b", "c", "d"
    };

    @Override
    public void onCreate() {
        new Thread(new TCPServer()).start();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isServiceDestoryed = true;
        super.onDestroy();
    }

    private class TCPServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Log.d(TAG, "run: "+e.getMessage());
                return;
            }

            while (!isServiceDestoryed) {
                try {
                    // 接受服务端消息
                    final Socket client = serverSocket.accept();
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    Log.d(TAG, "run: "+e.getMessage());
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接受客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        while (!isServiceDestoryed) {
            String str = in.readLine();
            if (str == null) {
                // 客户端断开连接
                break;
            }
            // 随机返回msg
            int i = new Random().nextInt(msgs.length);
            String msg = msgs[i];
            out.println(msg);
        }
        in.close();
        out.close();
        client.close();
    }
}
