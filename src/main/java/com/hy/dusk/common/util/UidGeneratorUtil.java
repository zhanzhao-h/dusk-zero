package com.hy.dusk.common.util;

public class UidGeneratorUtil {
    private UidGeneratorUtil() {
    }

    /**
     * 简易id生成
     *
     * @return
     */
    public static Long getUidGenerator() {
        return System.currentTimeMillis();
    }
}
