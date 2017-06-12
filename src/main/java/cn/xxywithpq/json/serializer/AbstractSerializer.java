package cn.xxywithpq.json.serializer;

import cn.xxywithpq.SimpleDate;
import cn.xxywithpq.common.Const;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by panqian on 2017/6/6.
 */
public abstract class AbstractSerializer {

    JsonSerializer jsonSerializer;

    protected void characterHandle(StringBuffer sb, CharSequence cs) {
        sb.append(Const.SINGLE_QUOTES).append(cs).append(Const.SINGLE_QUOTES);
    }

    protected void characterHandle(StringBuffer sb, Character cs) {
        sb.append(Const.SINGLE_QUOTES).append(cs).append(Const.SINGLE_QUOTES);
    }

    protected void numberHandle(StringBuffer sb, Number cs) {
        sb.append(cs);
    }

    protected void collectionHandle(StringJoiner sj, Collection cs) {
        Iterator iterator = cs.iterator();
        jsonSerializer = new JsonSerializer();
        while (iterator.hasNext()) {
            sj.add(jsonSerializer.convertToJsonString(iterator.next()));
        }
    }


    protected void mapHandle(StringJoiner sj, Map cs) {
        Set set = cs.keySet();
        Iterator iterator = set.iterator();
        jsonSerializer = new JsonSerializer();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            sj.add(Const.SINGLE_QUOTES + next + Const.SINGLE_QUOTES + Const.COLON +
                    jsonSerializer.convertToJsonString(cs.get(next)));
        }
    }

    protected void dateHandle(StringBuffer sb, Date d) {
        sb.append(d.getTime());
    }

    protected void localDateHandle(StringBuffer sb, LocalDate ld) {
        sb.append(Const.SINGLE_QUOTES + SimpleDate.dateToString(ld, Const.YYYYMMDD) + Const.SINGLE_QUOTES);
    }

    protected void localDateTimeHandle(StringBuffer sb, LocalDateTime ldt) {
        sb.append(Const.SINGLE_QUOTES + SimpleDate.dateToStringForJson(ldt) + Const.SINGLE_QUOTES);
    }

    protected void localTimeHandle(StringBuffer sb, LocalTime lt) {
        sb.append(Const.SINGLE_QUOTES + SimpleDate.timeToStringForJson(lt) + Const.SINGLE_QUOTES);
    }


}
