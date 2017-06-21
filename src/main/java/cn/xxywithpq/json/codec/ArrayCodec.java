package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Array 解析器
 * Created by panqian on 2017/6/12.
 */
public class ArrayCodec extends AbstractSerializer implements IJson {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACKET, Const.POST_BRACKET);
        Object[] o1 = (Object[]) o;
        List<Object> objects = Arrays.asList(o1);
        collectionHandle(sj, objects);
        return sj.toString();
    }

    @Override
    public Object parse(Object o) {
        return null;
    }
}
