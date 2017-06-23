package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * TemporalAccessor 解析器
 * Created by panqian on 2017/6/6.
 */
public class TemporalAccessorCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalDate ld = (LocalDate) o;
        sb = new StringBuffer(23);
        localDateHandle(sb, ld);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Type[] trueType) {
        return null;
    }
}
