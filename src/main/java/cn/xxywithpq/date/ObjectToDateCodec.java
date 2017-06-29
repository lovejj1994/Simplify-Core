package cn.xxywithpq.date;

import cn.xxywithpq.common.Const;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by panqian on 2017/6/9.
 */
public class ObjectToDateCodec {

    private LocalDate localDate;
    private SimpleDateFormat simpleDateFormat;
    private DateTimeFormatter formatter;

    public Date convertObjectToDate(String date) {
        return convertObjectToDate(date, Const.YYYYMMDDHHMM);
    }

    public Date convertObjectToDate(String date, String pattern) {
        return Date.from(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)).atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date convertObjectToDate(Long date) {
        return Date.from(Instant.ofEpochMilli(date));
    }
}
