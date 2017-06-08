package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Float 解析器
 * Created by panqian on 2017/6/6.
 */
public class FloatCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Float f = (Float) o;
        sb = new StringBuffer(1);
        numberHandle(sb, f);
        return sb.toString();
    }
}
