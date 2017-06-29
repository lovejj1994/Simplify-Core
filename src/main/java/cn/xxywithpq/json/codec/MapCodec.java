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
    public Object parse(Object o, Method m) {
        Map<String, Object> p = (HashMap) o;

        Set<String> keys = p.keySet();

        Type[] genericParameterTypes = m.getGenericParameterTypes();
        Type t = getActualTypeArgumentsFromMap(genericParameterTypes);

        if (null != keys && keys.size() > 0) {
            for (String key : keys) {
                Object oo = p.get(key);
                try {
                    Class<?> aClass = Class.forName(t.getTypeName());
                    IJson suitableHandler = getSuitableParseHandler(aClass);
                    Object parse = suitableHandler.parse(oo, m);
                    p.replace(key, parse);
                } catch (ClassNotFoundException e) {
                }
            }
        }
        return p;
    }
}
