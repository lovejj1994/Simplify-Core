package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * LocalDate 解析器
 * Created by panqian on 2017/6/6.
 */
public class LocalDateTimeCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalDateTime ld = (LocalDateTime) o;
        sb = new StringBuffer(23);
        localDateTimeHandle(sb, ld);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Type[] trueType) {
        return null;
    }
}
