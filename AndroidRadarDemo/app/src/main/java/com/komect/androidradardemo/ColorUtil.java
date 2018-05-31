package com.komect.androidradardemo;

/**
 * Created by xieyusheng on 2018/5/31.
 */

public class ColorUtil {
    /**
     * 添加透明度 100全透明 0不透明
     */
    public static String addTrans(int transparency, int baseColor) {
        return "#" + Integer.toHexString((transparency * 256 / 100) << 24 | baseColor);
    }
}
