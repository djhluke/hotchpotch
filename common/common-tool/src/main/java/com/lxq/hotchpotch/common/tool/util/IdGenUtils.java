package com.lxq.hotchpotch.common.tool.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.util.UUID;

/**
 * @author luxinqiang
 */
public class IdGenUtils {

    /**
     * 主键id
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 序列号
     *
     * @return
     */
    public static String serialNo() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 批次号
     *
     * @return
     */
    public static String batchNo() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 每日唯一有序id
     *
     * @return
     */
    public static String dayUniqueId() {
        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        String currentTimeMillisStr = String.valueOf(System.currentTimeMillis());
        String currentTimeMillisLastEight = currentTimeMillisStr.substring(currentTimeMillisStr.length() - 8);
        return currentTimeMillisLastEight + String.format("%04d", snowflake.nextId() & ~(-1L << 12));
    }

}
