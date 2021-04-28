package com.wlxk.core.tool;

import java.util.Date;

/**
 * 日期工具类
 *
 * @author sxz
 * @date 2021/4/16 17:25
 */
public class WlDateUtil {

    /**
     * 一天时长
     */
    public static final int ONE_DAY_MILLIS = 86400000;
    /**
     * 东八区偏移量
     */
    public static final int EAST_EIGHT_OFFSET = 28800000;

    /**
     * 比较两个时间的当天起始时间 -- 时分秒置零
     *
     * @param fromDate 入参
     * @return 结果
     */
    public static int compareToCurrentByDayBegin(Date fromDate) {
        return getDayBeginMillis(fromDate).compareTo(getCurrentZeroTimeMillis());
    }

    /**
     * 比较两个时间的当天起始时间 -- 时分秒置零
     *
     * @param fromDate 入参
     * @param toDate   入参
     * @return 结果
     */
    public static int compareByDayBegin(Date fromDate, Date toDate) {
        return getDayBeginMillis(fromDate).compareTo(getDayBeginMillis(toDate));
    }

    /**
     * 获取当天开始时间--时分秒位置零
     *
     * @return 结果
     */
    public static Date getCurrentDayBegin() {
        return new Date(getCurrentZeroTimeMillis());
    }

    /**
     * 获取某天开始时间--时分秒位置零
     *
     * @param date 入参
     * @return 结果
     */
    public static Date getDayBegin(Date date) {
        return new Date(getZeroTimeMillis(date.getTime()));
    }

    /**
     * 获取某天开始时间--时分秒位置零
     *
     * @param date 入参
     * @return 结果
     */
    public static Long getDayBeginMillis(Date date) {
        return getZeroTimeMillis(date.getTime());
    }

    /**
     * 时间抹零--时分秒位置零
     *
     * @param time 入参
     * @return 结果
     */
    public static Long getZeroTimeMillis(long time) {
        return time - ((time + EAST_EIGHT_OFFSET) % (ONE_DAY_MILLIS));
    }

    /**
     * 当前时间抹零--时分秒位置零
     *
     * @return 结果
     */
    public static Long getCurrentZeroTimeMillis() {
        return getZeroTimeMillis(System.currentTimeMillis());
    }

}
