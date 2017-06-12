package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.util.Date;

/**
 * Date 解析器
 * Created by panqian on 2017/6/6.
 */
public class DateCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Date d = (Date) o;
        sb = new StringBuffer(((Long)d.getTime()).toString().length());
        dateHandle(sb, d);
        return sb.toString();
    }
}
