package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.time.LocalDateTime;

/**
 * LocalDate 解析器
 * Created by panqian on 2017/6/6.
 */
public class LocalDateTimeCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalDateTime ld = (LocalDateTime) o;
        sb = new StringBuffer(23);
        localDateTimeHandle(sb, ld);
        return sb.toString();
    }
}
