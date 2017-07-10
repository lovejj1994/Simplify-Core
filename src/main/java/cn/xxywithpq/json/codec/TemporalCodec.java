package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.JsonException;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * jdk8 时间解析器
 * Created by panqian on 2017/6/6.
 */
public class TemporalCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        if (o instanceof LocalDate) {
            LocalDate ld = (LocalDate) o;
            sb = new StringBuffer(12);
            localDateHandle(sb, ld);
            return sb.toString();
        } else if (o instanceof LocalDateTime) {
            LocalDateTime ld = (LocalDateTime) o;
            sb = new StringBuffer(23);
            localDateTimeHandle(sb, ld);
            return sb.toString();
        } else if (o instanceof LocalTime) {
            LocalTime ld = (LocalTime) o;
            sb = new StringBuffer(23);
            localTimeHandle(sb, ld);
            return sb.toString();
        }
        throw new JsonException("unsupport type " + o.getClass());
    }

    @Override
    public Object parse(Object o, Method m) {
        return null;
    }
}
