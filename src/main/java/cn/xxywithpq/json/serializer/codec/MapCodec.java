package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Map 解析器
 * Created by panqian on 2017/6/6.
 */
public class MapCodec extends AbstractSerializer implements ISerializer {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(",", "{", "}");
        Map m = (Map) o;
        mapHandle(sj, m);
        return sj.toString();
    }
}
