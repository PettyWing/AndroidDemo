package com.xyc.accountbook.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.xyc.accountbook.bean.UserState;

/**
 * Created by xieyusheng on 2018/8/15.
 */

public class PasswordPresenter {

    private String oldPsw;
    private Context context;

    public PasswordPresenter(Context context) {
        this.context = context;
        oldPsw = UserState.newInstance(context).getPassword();
    }

    /**
     * 是否有旧的密码
     *
     * @return
     */
    public boolean hasOldPsw() {
        return !TextUtils.isEmpty(oldPsw);
    }

    public boolean save(String oldPsw, String newPsw) {
        if (!verifyPswFormat(newPsw)) {
            Toast.makeText(context, "新的密码格式不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hasOldPsw() && !TextUtils.equals(oldPsw, this.oldPsw)) {
            Toast.makeText(context, "当前密码错误，请重试", Toast.LENGTH_SHORT).show();
            return false;
        }
        UserState.newInstance(context).setPassword(newPsw);
        return true;
    }

    /**
     * 验证密码是否正确
     *
     * @param psw
     */
    public boolean verifyPsw(String psw) {
        if (!TextUtils.equals(this.oldPsw, psw)) {
            return false;
        }
        return true;
    }

    private boolean verifyPswFormat(String psw) {
        return psw.matches("^[0-9]{4,8}$");
    }
}
