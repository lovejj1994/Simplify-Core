package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

/**
 * Integer 解析器
 * Created by panqian on 2017/6/6.
 */
public class IntegerCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Integer i = (Integer) o;
        sb = new StringBuffer(1);
        numberHandle(sb, i);
        return sb.toString();
    }

    @Override
    public Object parse(Object o) {
        return null;
    }
}
