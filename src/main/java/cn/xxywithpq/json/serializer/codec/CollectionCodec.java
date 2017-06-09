package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Collection 解析器
 * Created by panqian on 2017/6/6.
 */
public class CollectionCodec extends AbstractSerializer implements ISerializer {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(",", "[", "]");
        Collection c = (Collection) o;
        collectionHandle(sj, c);
        return sj.toString();
    }
}
