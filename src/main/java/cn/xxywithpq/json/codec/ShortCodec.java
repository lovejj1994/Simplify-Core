package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Short 解析器
 * Created by panqian on 2017/6/6.
 */
public class ShortCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Short s = (Short) o;
        sb = new StringBuffer(1);
        numberHandle(sb, s);
        return sb.toString();
    }
}
