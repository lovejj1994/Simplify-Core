package cn.xxywithpq;

import cn.xxywithpq.date.DateToStringCodec;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
