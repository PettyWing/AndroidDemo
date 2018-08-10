package com.xyc.accountbook.presenter;

import com.xyc.accountbook.bean.AccountInfo;

import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xieyusheng on 2018/8/10.
 */

public class DbPresenter {
    public static void save(String name, String account, String password, HashMap<String, String> values) {
        AccountInfo accountInfo = new AccountInfo(name, account, password);
        accountInfo.setValuesStr(map2Str(values));
        accountInfo.save();
    }

    public static List<AccountInfo> findAll() {
        return DataSupport.findAll(AccountInfo.class);
    }

    private static String map2Str(HashMap<String, String> values) {
        if (values.size() == 0) {
            return "";
        }
        JSONObject json = new JSONObject(values);
        return json.toString();
    }

}
