package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Map 解析器
 * Created by panqian on 2017/6/6.
 */
public class MapCodec extends AbstractJson implements IJson {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACE, Const.POST_BRACE);
        Map m = (Map) o;
        mapHandle(sj, m);
        return sj.toString();
    }

    @Override
    public Object parse(Object o, Type[] trueType) {
        Map<String, Object> p = (HashMap) o;

        Set<String> keys = p.keySet();

        if (null != keys && keys.size() > 0) {
            for (String key : keys) {
                Object oo = p.get(key);
                IJson suitableHandler = getSuitableParseHandler(oo.getClass(), trueType);
                Object parse = suitableHandler.parse(oo, trueType);
                p.replace(key, parse);
            }
        }
        return p;
    }
}
