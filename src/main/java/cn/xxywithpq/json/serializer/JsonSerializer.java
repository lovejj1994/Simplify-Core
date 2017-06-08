package cn.xxywithpq.json.serializer;

import cn.xxywithpq.Common.Const;
import cn.xxywithpq.json.serializer.codec.*;

/**
 * Created by panqian on 2017/6/8.
 */
public class JsonSerializer {

    private ISerializer getSuitableHandler(Class c) {
        switch (c.getTypeName()) {
            case Const.STRING_TYPE:
                return new StringCodec();
            case Const.BOOLEAN_TYPE:
                return new BooleanCodec();
            case Const.BYTE_TYPE:
                return new ByteCodec();
            case Const.DOUBLE_TYPE:
                return new DoubleCodec();
            case Const.FLOAT_TYPE:
                return new FloatCodec();
            case Const.CHAR_TYPE:
                return new CharCodec();
            case Const.SHORT_TYPE:
                return new ShortCodec();
            case Const.INTEGER_TYPE:
                return new IntegerCodec();
            case Const.LONG_TYPE:
                return new LongCodec();
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
