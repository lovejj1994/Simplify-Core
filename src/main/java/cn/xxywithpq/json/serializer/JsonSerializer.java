package cn.xxywithpq.json.serializer;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.codec.*;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by panqian on 2017/6/8.
 */

public class JsonSerializer {

    private static Logger logger = Logger.getLogger(JsonSerializer.class.getName());

    private ISerializer getSuitableHandler(Class c) {

        if (Collection.class.isAssignableFrom(c)) {
            c = Collection.class;
        }

        if (Map.class.isAssignableFrom(c)) {
            c = Map.class;
        }

        if (Number.class.isAssignableFrom(c)) {
            c = Number.class;
        }

        if (c.isArray()) {
            return new ArrayCodec();
        }

        switch (c.getTypeName()) {
            case Const.NUMBER_TYPE:
                return new NumberCodec();
            case Const.COLLECTION_TYPE:
                return new CollectionCodec();
            case Const.MAP_TYPE:
                return new MapCodec();
            case Const.STRING_TYPE:
                return new StringCodec();
            case Const.BOOLEAN_TYPE:
                return new BooleanCodec();
            case Const.CHAR_TYPE:
                return new CharCodec();
            case Const.DATE_TYPE:
                return new DateCodec();
            case Const.LOCALDATE_TYPE:
                return new LocalDateCodec();
            case Const.LOCALDATETIME_TYPE:
                return new LocalDateTimeCodec();
            case Const.LOCALTIME_TYPE:
                return new LocalTimeCodec();
            default:
                return new ObjectCodec();
        }
    }

    public String convertToJsonString(Object o) {
        Class<?> c = o.getClass();
        ISerializer suitableHandler = getSuitableHandler(c);
        return (String) suitableHandler.writeJsonString(o);
    }

}
