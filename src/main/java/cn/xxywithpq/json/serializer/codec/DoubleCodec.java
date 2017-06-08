package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Double 解析器
 * Created by panqian on 2017/6/6.
 */
public class DoubleCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Double d = (Double) o;
        sb = new StringBuffer(1);
        numberHandle(sb, d);
        return sb.toString();
    }
}
