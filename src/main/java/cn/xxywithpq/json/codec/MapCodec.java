package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.JsonException;
import cn.xxywithpq.json.parse.JsonObject;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

        Class<?> rawType = getParameterTypes(m);
        Type t = getActualTypeArguments(m);
        Map<Object, Object> p = createMap(rawType);
        JsonObject jo = (JsonObject) o;

        Set<String> keys = jo.keySet();

        if (null != jo && jo.size() > 0) {
            for (Object key : keys) {
                Object oo = jo.get(key);
                try {
                    Class<?> aClass = Class.forName(t.getTypeName());
                    IJson suitableHandler = getSuitableParseHandler(aClass);
                    Object parse = suitableHandler.parse(oo, m);
                    p.put(key, parse);
                } catch (ClassNotFoundException e) {
                    throw new JsonException("class not found ", e);
                }
            }
        }
        return p;
    }

    private Map<Object, Object> createMap(Type type) {
        // TODO: 2017/7/5  Properties key可能不为String，要增加这个Properties类型的测试用例
        if (type == Properties.class) {
            return new Properties();
        }

        if (type == Hashtable.class) {
            return new Hashtable();
        }

        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }

        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }

        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }

        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }

        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            Type rawType = parameterizedType.getRawType();
            if (EnumMap.class.equals(rawType)) {
                Type[] actualArgs = parameterizedType.getActualTypeArguments();
                return new EnumMap((Class) actualArgs[0]);
            }

            return createMap(rawType);
        }

        Class<?> clazz = (Class<?>) type;
        if (clazz.isInterface()) {
            throw new JsonException("unsupport type " + type);
        }

        try {
            return (Map<Object, Object>) clazz.newInstance();
        } catch (Exception e) {
            throw new JsonException("unsupport type " + type, e);
        }
    }
}
