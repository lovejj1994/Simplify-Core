package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.util.Date;

/**
 * Date 解析器
 * Created by panqian on 2017/6/6.
 */
public class DateCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Date d = (Date) o;
        sb = new StringBuffer(((Long) d.getTime()).toString().length());
        dateHandle(sb, d);
        return sb.toString();
    }

    @Override
    public Object parse(Object o) {
        return null;
    }
}
