package cn.xxywithpq.date;

import cn.xxywithpq.common.Const;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by panqian on 2017/6/9.
 */
public class LongToDateCodec {

    private LocalDate localDate;
    private SimpleDateFormat simpleDateFormat;
    private DateTimeFormatter formatter;

    public Date convertStringToLocalDate(String date) {
        return convertStringToLocalDate(date, Const.YYYYMMDDHHMM);
    }

    public Date convertStringToLocalDate(String date, String pattern) {
        return Date.from(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern)).atZone(ZoneId.systemDefault()).toInstant());
    }
}
