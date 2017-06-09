package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Boolean 解析器
 * Created by panqian on 2017/6/6.
 */
public class BooleanCodec extends AbstractSerializer implements ISerializer {

    @Override
    public Object writeJsonString(Object o) {
        Boolean b = (Boolean) o;
        return b.toString();
    }
}
