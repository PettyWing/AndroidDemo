package com.xyc.accountbook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xieyusheng on 2019/12/2.
 */

public class TimeUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }
}
