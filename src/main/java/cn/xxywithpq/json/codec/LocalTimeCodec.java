package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalTimeCodec 解析器
 * Created by panqian on 2017/6/6.
 */
public class LocalTimeCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalTime ld = (LocalTime) o;
        sb = new StringBuffer(23);
        localTimeHandle(sb, ld);
        return sb.toString();
    }
}
