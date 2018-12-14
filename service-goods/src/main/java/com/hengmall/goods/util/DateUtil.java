package com.hengmall.goods.util;


import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDate dateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static String dateToDateString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Boolean sameDate(Date dt1, Date dt2) {
        org.joda.time.LocalDate ld1 = new org.joda.time.LocalDate(new org.joda.time.DateTime(dt1));
        org.joda.time.LocalDate ld2 = new org.joda.time.LocalDate(new org.joda.time.DateTime(dt2));
        return ld1.equals(ld2);
    }

    /**
     * 获取这一天剩余的时间秒
     * @return
     */
    public static Long restOfDay(){
        return 86400 - DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DATE);
    }

}
