package cn.xxywithpq.json;

import cn.xxywithpq.SimpleDate;
import cn.xxywithpq.common.Const;
import cn.xxywithpq.date.ext.DateTimeFormatterExt;
import cn.xxywithpq.json.codec.*;
import cn.xxywithpq.json.serializer.JsonSerializer;
import cn.xxywithpq.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.*;

/**
 * Created by panqian on 2017/6/6.
 */
public abstract class AbstractJson {

    private JsonSerializer jsonSerializer = JsonSerializer.getInstance();

    protected void characterHandle(StringBuffer sb, CharSequence cs) {
        sb.append(Const.SINGLE_QUOTES).append(cs).append(Const.SINGLE_QUOTES);
    }

    protected void characterHandle(StringBuffer sb, Character cs) {

        sb.append(Const.SINGLE_QUOTES).append(cs.charValue()).append(Const.SINGLE_QUOTES);
    }

    protected void numberHandle(StringBuffer sb, Number cs) {
        sb.append(cs);
    }

    protected void collectionHandle(StringJoiner sj, Collection cs) {
        Iterator iterator = cs.iterator();
        while (iterator.hasNext()) {
            sj.add(jsonSerializer.convertToJsonString(iterator.next()));
        }
    }


    protected void mapHandle(StringJoiner sj, Map cs) {
        Set set = cs.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            sj.add(Const.SINGLE_QUOTES + next + Const.SINGLE_QUOTES + Const.COLON +
                    jsonSerializer.convertToJsonString(cs.get(next)));
        }
    }

    protected void dateHandle(StringBuffer sb, Date d) {
        sb.append(d.getTime());
    }

    protected void localDateHandle(StringBuffer sb, LocalDate ld) {
        sb.append(Const.SINGLE_QUOTES + SimpleDate.dateToString(ld, Const.YYYYMMDD) + Const.SINGLE_QUOTES);
    }

    protected void localDateTimeHandle(StringBuffer sb, LocalDateTime ldt) {
        DateTimeFormatter isoLocalDateTime = DateTimeFormatterExt.ISO_LOCAL_DATE_TIME;
        sb.append(Const.SINGLE_QUOTES + isoLocalDateTime.format(ldt) + Const.SINGLE_QUOTES);
    }

    protected void localTimeHandle(StringBuffer sb, LocalTime lt) {
        DateTimeFormatter isoTime = DateTimeFormatterExt.ISO_LOCAL_TIME;
        sb.append(Const.SINGLE_QUOTES + isoTime.format(lt) + Const.SINGLE_QUOTES);
    }

    protected IJson getSuitableHandler(Class c) {

        if (Collection.class.isAssignableFrom(c)) {
//            c = Collection.class;
            return new CollectionCodec();
        }

        if (Map.class.isAssignableFrom(c)) {
//            c = Map.class;
            return new MapCodec();
        }

        if (Number.class.isAssignableFrom(c)) {
//            c = Number.class;
            return new NumberCodec();
        }

        if (c.isArray()) {
            return new ArrayCodec();
        }
        if (Temporal.class.isAssignableFrom(c)) {
            return new TemporalCodec();
        }

        if (c == char.class || c == Character.class) {
            return new CharCodec();
        }

        if (c == boolean.class || c == Boolean.class) {
            return new BooleanCodec();
        }

        if (c == String.class) {
            return new StringCodec();
        }

        if (c == Date.class) {
            return new DateCodec();
        }

//        switch (c.getTypeName()) {
//            case Const.NUMBER_TYPE:
//                return new NumberCodec();
//            case Const.COLLECTION_TYPE:
//                return new CollectionCodec();
//            case Const.MAP_TYPE:
//                return new MapCodec();
////            case Const.STRING_TYPE:
////                return new StringCodec();
////            case Const.BOOLEAN_TYPE:
////                return new BooleanCodec();
////            case Const.CHAR_TYPE:
////                return new CharCodec();
//            case Const.DATE_TYPE:
//                return new DateCodec();
//            default:
        return new ObjectCodec();
//        }
    }

//    @Deprecated
//    protected IJson getSuitableParseHandler(Class c) {
//
//        if (c.isArray()) {
//            return new ArrayCodec();
//        }
//
//        switch (c.getTypeName()) {
//            case Const.LONG_TYPE:
//                return new LongCodec();
//            case Const.INTEGER_TYPE:
//                return new IntegerCodec();
//            case Const.LIST_TYPE:
//                return new CollectionCodec();
//            case Const.COLLECTION_TYPE:
//                return new CollectionCodec();
//            case Const.MAP_TYPE:
//                return new MapCodec();
//            case Const.STRING_TYPE:
//                return new StringCodec();
//            case Const.BOOLEAN_TYPE:
//                return new BooleanCodec();
//            case Const.CHAR_TYPE:
//                return new CharCodec();
//            case Const.DATE_TYPE:
//                return new DateCodec();
//            case Const.LOCALDATE_TYPE:
//                return new LocalDateCodec();
//            case Const.LOCALDATETIME_TYPE:
//                return new LocalDateTimeCodec();
//            case Const.LOCALTIME_TYPE:
//                return new LocalTimeCodec();
//            case Const.BIGDECIMAL_TYPE:
//                return new BigDecimalCodec();
//            default:
//                return new ObjectCodec();
//        }
//    }

    /**
     * 查找泛型类型
     *
     * @param m
     * @return
     */
    protected Type getActualTypeArguments(Method m) {
        Type[] genericParameterTypes = m.getGenericParameterTypes();
        for (Type type : genericParameterTypes) {
            Type[] actualTypeArguments = ReflectionUtils.getActualTypeArguments(type);
            if (null == actualTypeArguments || actualTypeArguments.length == 0) {
                return null;
            }
            //Map 取value的泛型
            if (actualTypeArguments.length > 1) {
                return actualTypeArguments[1];
                //Collection 取泛型
            } else {
                return actualTypeArguments[0];
            }
        }
        return null;
    }

    protected Class<?> getParameterTypes(Method m) {
        Class<?>[] parameterTypes = m.getParameterTypes();
        return parameterTypes[0];
    }

}
