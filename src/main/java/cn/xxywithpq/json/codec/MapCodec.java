package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Map 解析器
 * Created by panqian on 2017/6/6.
 */
public class MapCodec extends AbstractSerializer implements IJson {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACE, Const.POST_BRACE);
        Map m = (Map) o;
        mapHandle(sj, m);
        return sj.toString();
    }

    @Override
    public Object parse(Object o) {
        return null;
    }
}
