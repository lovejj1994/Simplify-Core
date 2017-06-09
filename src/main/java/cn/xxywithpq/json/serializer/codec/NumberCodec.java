package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Number 解析器
 * Created by panqian on 2017/6/6.
 */
public class NumberCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Number n = (Number) o;
        sb = new StringBuffer(n.toString().length());
        numberHandle(sb, n);
        return sb.toString();
    }
}
