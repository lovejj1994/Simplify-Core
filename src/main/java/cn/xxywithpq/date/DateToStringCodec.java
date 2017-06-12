package cn.xxywithpq.date;

import cn.xxywithpq.common.Const;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by panqian on 2017/6/9.
 */
public class DateToStringCodec {

    private SimpleDateFormat simpleDateFormat;
    private DateTimeFormatter formatter;

    public String convertLocalDateToString(LocalDate ld) {
        return convertLocalDateToString(ld, Const.YYYYMMDDHHMM);
    }

    public String convertLocalDateToString(LocalDate ld, String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(ld);
    }

    public String convertLocalDateTimeToString(LocalDateTime ldt, String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(ldt);
    }
}
