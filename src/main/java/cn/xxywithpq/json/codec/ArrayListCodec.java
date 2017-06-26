package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Collection 解析器
 * Created by panqian on 2017/6/6.
 */
public class ArrayListCodec extends AbstractJson implements IJson {

    StringJoiner sj;

    @Override
    public Object writeJsonString(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACKET, Const.POST_BRACKET);
        Collection c = (Collection) o;
        collectionHandle(sj, c);
        return sj.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        ArrayList al = (ArrayList) o;
        Type[] genericParameterTypes = m.getGenericParameterTypes();
        Type t = null;
        for (Type type : genericParameterTypes) {
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                for (Type t1 : ((ParameterizedType) type).getActualTypeArguments()) {
                    t = t1;
                }
            }
        }
        if (Objects.nonNull(t)) {
            ListIterator listIterator = al.listIterator();
            IJson suitableParseHandler = getSuitableParseHandler(t.getClass());
            while (listIterator.hasNext()) {
                Object next = listIterator.next();
                Object parse = suitableParseHandler.parse(next, m);
                listIterator.set(parse);
            }
        }
        return al;
    }
}
