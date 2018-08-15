package com.xyc.accountbook.presenter;

import android.app.Activity;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

/**
 * Created by xieyusheng on 2018/8/15.
 */

public class FingerPointManager {

    private FingerprintManagerCompat mFingerprintManager;
    private FingerPrintResultListener listener;

    public FingerPointManager(Activity activity) {
        mFingerprintManager = FingerprintManagerCompat.from(activity);
    }

    public void startAuthenticate() {
        if (isFingerOpen() && isHardwareDected()) {
            mFingerprintManager.authenticate(null, 0, null, new MyFingerDiscentListener(), null);
        }
    }

    public void setFingerPrintResultListener(FingerPrintResultListener listener) {
        this.listener = listener;
    }

    //手机硬件是否支持指纹
    public Boolean isHardwareDected() {
        try {
            return mFingerprintManager.isHardwareDetected();
        } catch (Exception e) {
            return false;
        }
    }

    //是否录入指纹，有些设备即使录入指纹，但是没有开启锁屏密码的话此方法还是返回false
    public Boolean isFingerOpen() {
        try {
            return mFingerprintManager.hasEnrolledFingerprints();
        } catch (Exception e) {
            return false;
        }
    }

    public interface FingerPrintResultListener {
        void onSuccess();

        void onFailed();
    }

    private class MyFingerDiscentListener extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            listener.onFailed();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            listener.onSuccess();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            listener.onFailed();
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
        }
    }

}
