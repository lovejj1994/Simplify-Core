package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.time.LocalDate;

/**
 * LocalDate 解析器
 * Created by panqian on 2017/6/6.
 */
public class LocalDateCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalDate ld = (LocalDate) o;
        sb = new StringBuffer(12);
        localDateHandle(sb, ld);
        return sb.toString();
    }
}
