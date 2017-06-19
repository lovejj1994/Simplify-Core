package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
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
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACKET, Const.POST_BRACKET);
        Collection c = (Collection) o;
        collectionHandle(sj, c);
        return sj.toString();
    }
}
