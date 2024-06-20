package com.temp.common.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 文件util
 *
 * @author Hollis
 * @since 2024/03/18 16:28
 */
public class FileUtils {

    /**
     * 获取日期文件夹
     *
     * @return java.lang.String
     */
    public static String getDateFolder() {
        Date date = new Date();
        return DateUtil.year(date)
                + "/" + (DateUtil.month(date) + 1)
                + "/" + DateUtil.dayOfMonth(date);
    }
}
