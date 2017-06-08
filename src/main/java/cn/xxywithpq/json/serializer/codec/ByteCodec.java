package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Byte 解析器
 * Created by panqian on 2017/6/6.
 */
public class ByteCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Byte b = (Byte) o;
        sb = new StringBuffer(1);
        numberHandle(sb, b);
        return sb.toString();
    }
}
