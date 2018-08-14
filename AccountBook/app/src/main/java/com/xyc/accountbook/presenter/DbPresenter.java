package com.xyc.accountbook.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyc.accountbook.bean.AccountDetail;
import com.xyc.accountbook.bean.AccountInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2018/8/10.
 */

public class DbPresenter {
    public static void save(String name, String account, String password, ArrayList<AccountDetail> values) {
        AccountInfo accountInfo = new AccountInfo(name, account, password);
        accountInfo.setValuesStr(list2Str(values));
        accountInfo.save();
    }

    public static List<AccountInfo> findAll() {
        return DataSupport.findAll(AccountInfo.class);
    }

    public static String list2Str(ArrayList<AccountDetail> values) {
        if (values.size() == 0) {
            return "";
        }

        return new Gson().toJson(values);
    }

    public static ArrayList<AccountDetail> str2List(String str) {
        ArrayList<AccountDetail> list = new Gson().fromJson(str, new TypeToken<List<AccountDetail>>() {
        }.getType());

        return list;
    }

}
