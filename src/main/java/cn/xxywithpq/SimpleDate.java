package cn.xxywithpq;

import cn.xxywithpq.date.DateToStringCodec;
import cn.xxywithpq.date.ObjectToDateCodec;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by panqian on 2017/6/9.
 */
public class SimpleDate {
    private SimpleDate() {
    }

    public static String dateToString(LocalDate ld, String pattern) {
        DateToStringCodec dateToStringCodec = new DateToStringCodec();
        return dateToStringCodec.convertLocalDateToString(ld, pattern);
    }

    public static String dateToString(LocalDateTime ldt, String pattern) {
        DateToStringCodec dateToStringCodec = new DateToStringCodec();
        return dateToStringCodec.convertLocalDateTimeToString(ldt, pattern);
    }

    public static Date objectToDate(String date) {
        ObjectToDateCodec stringToDateCodec = new ObjectToDateCodec();
        return stringToDateCodec.convertObjectToDate(date);
    }

    public static Date objectToDate(Long date) {
        ObjectToDateCodec objectToDateCodec = new ObjectToDateCodec();
        return objectToDateCodec.convertObjectToDate(date);
    }
}
