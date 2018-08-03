package com.xyc.accountbook.bean;

import com.xyc.accountbook.util.ContactsUtils;

/**
 * Created by xieyusheng on 2018/7/27.
 */

public class AccountInfo implements Comparable<AccountInfo>{

    private String name;
    private final String mAbbreviation;
    private final String mInitial;

    public AccountInfo(String name){
        this.name = name;
        this.mAbbreviation = ContactsUtils.getAbbreviation(name);
        this.mInitial = mAbbreviation.substring(0, 1);
    }

    public String getInitial() {
        return mInitial;
    }

    public String getName() {
        return name;
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
