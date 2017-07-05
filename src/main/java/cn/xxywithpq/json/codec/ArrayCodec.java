package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Array 解析器
 * Created by panqian on 2017/6/12.
 */
public class ArrayCodec extends AbstractJson implements IJson {

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
    public Object parse(Object o, Method m) {
        ArrayList al = (ArrayList) o;
        ListIterator listIterator = al.listIterator();
        IJson suitableParseHandler = getSuitableParseHandler(m.getParameterTypes()[0].getComponentType());
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            Object parse = suitableParseHandler.parse(next, m);
            listIterator.set(parse);
        }
        Object[] o1 = (Object[]) Array.newInstance(m.getParameterTypes()[0].getComponentType(), al.size());
        Object[] objects = al.toArray(o1);
        return objects;
    }
}
