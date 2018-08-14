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

    private static final String TAG = "DbPresenter";

    /**
     * 保存
     *
     * @param name
     * @param account
     * @param password
     * @param values
     */
    public static boolean save(String name, String account, String password, ArrayList<AccountDetail> values) {
        AccountInfo accountInfo = new AccountInfo(name, account, password);
        accountInfo.setValuesStr(list2Str(values));
        return accountInfo.save();
    }

    /**
     * 更新
     *
     * @param id
     * @param name
     * @param account
     * @param password
     * @param values
     * @return
     */
    public static boolean save(int id, String name, String account, String password, ArrayList<AccountDetail> values) {
        AccountInfo newInfo = DataSupport.find(AccountInfo.class, id);
        newInfo.setName(name);
        newInfo.setAccount(account);
        newInfo.setPassword(password);
        newInfo.setValuesStr(list2Str(values));
        return newInfo.save();
    }

    /**
     * 删除
     *
     * @param accountInfo
     */
    public static void delete(AccountInfo accountInfo) {
        accountInfo.delete();
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
