package com.xyc.accountbook.bean;

import com.xyc.accountbook.util.ContactsUtils;

import org.litepal.annotation.Encrypt;
import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xieyusheng on 2018/7/27.
 */

public class AccountInfo extends DataSupport implements Serializable, Comparable<AccountInfo> {

    private int id;
    private String name;
    @Encrypt(algorithm = AES)
    private String account;
    @Encrypt(algorithm = AES)
    private String password;
    @Encrypt(algorithm = AES)
    private String valuesStr;
    private final String mAbbreviation;
    private final String mInitial;

    public AccountInfo(String name, String account, String pasword) {
        this.name = name;
        this.account = account;
        this.password = pasword;
        this.mAbbreviation = ContactsUtils.getAbbreviation(name);
        this.mInitial = mAbbreviation.substring(0, 1);
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValuesStr() {
        return valuesStr;
    }

    public void setValuesStr(String valuesStr) {
        this.valuesStr = valuesStr;
    }

    public String getInitial() {
        return mInitial;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(AccountInfo r) {
        if (mAbbreviation.equals(r.mAbbreviation)) {
            return 0;
        }
        boolean flag;
        if ((flag = mAbbreviation.startsWith("#")) ^ r.mAbbreviation.startsWith("#")) {
            return flag ? 1 : -1;
        }
        return getInitial().compareTo(r.getInitial());
    }
}
