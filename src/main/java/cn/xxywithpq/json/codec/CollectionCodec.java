package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.JsonException;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Collection 解析器
 * Created by panqian on 2017/6/6.
 */
public class CollectionCodec extends AbstractJson implements IJson {

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
        Type t = getActualTypeArguments(m);
        Class<?> rawClass = getParameterTypes(m);
        Collection collection = createCollection(rawClass);
        if (Objects.nonNull(t)) {
            ListIterator listIterator = al.listIterator();
            IJson suitableParseHandler = getSuitableParseHandler(t.getClass());
            while (listIterator.hasNext()) {
                Object next = listIterator.next();
                Object parse = suitableParseHandler.parse(next, m);
                collection.add(parse);
            }
        } else {
            collection.addAll(al);
        }
        return collection;
    }

    private static Collection createCollection(Class rawClass) {
//        Class<?> rawClass = getRawClass(type);
        Type type = rawClass.getGenericSuperclass();
        Collection list;
        if (rawClass == AbstractCollection.class //
                || rawClass == Collection.class) {
            list = new ArrayList();
        } else if (rawClass.isAssignableFrom(HashSet.class)) {
            list = new HashSet();
        } else if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
            list = new LinkedHashSet();
        } else if (rawClass.isAssignableFrom(TreeSet.class)) {
            list = new TreeSet();
        } else if (rawClass.isAssignableFrom(ArrayList.class)) {
            list = new ArrayList();
        } else if (rawClass.isAssignableFrom(EnumSet.class)) {
            Type itemType;
            if (type instanceof ParameterizedType) {
                itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                itemType = Object.class;
            }
            list = EnumSet.noneOf((Class<Enum>) itemType);
        } else {
            try {
                list = (Collection) rawClass.newInstance();
            } catch (Exception e) {
                throw new JsonException("create instance error, class " + rawClass.getName());
            }
        }
        return list;
    }
}
