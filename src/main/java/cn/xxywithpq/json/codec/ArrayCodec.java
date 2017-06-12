package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

import java.util.*;

/**
 * Array 解析器
 * Created by panqian on 2017/6/12.
 */
public class ArrayCodec extends AbstractSerializer implements ISerializer {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(",", "[", "]");
        Object[] o1 = (Object[]) o;
        List<Object> objects = Arrays.asList(o1);
        collectionHandle(sj, objects);
        return sj.toString();
    }
}
