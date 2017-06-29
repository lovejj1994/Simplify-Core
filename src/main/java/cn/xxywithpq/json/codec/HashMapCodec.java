package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * HashMapCodec 解析器
 * Created by panqian on 2017/6/6.
 */
public class HashMapCodec extends AbstractJson implements IJson {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACE, Const.POST_BRACE);
        Map m = (Map) o;
        mapHandle(sj, m);
        return sj.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        Map<String, Object> p = (HashMap) o;

        Set<String> keys = p.keySet();

        Type[] genericParameterTypes = m.getGenericParameterTypes();
        Type t = getActualTypeArguments(genericParameterTypes);

        if (null != keys && keys.size() > 0) {
            for (String key : keys) {
                Object oo = p.get(key);
                IJson suitableHandler = getSuitableParseHandler(t.getClass());
                Object parse = suitableHandler.parse(oo, m);
                p.replace(key, parse);
            }
        }
        return p;
    }
}
