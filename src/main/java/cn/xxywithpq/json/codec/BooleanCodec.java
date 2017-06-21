package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

/**
 * Boolean 解析器
 * Created by panqian on 2017/6/6.
 */
public class BooleanCodec extends AbstractSerializer implements IJson {

    @Override
    public Object writeJsonString(Object o) {
        Boolean b = (Boolean) o;
        return b.toString();
    }

    @Override
    public Object parse(Object o) {
        return null;
    }
}
