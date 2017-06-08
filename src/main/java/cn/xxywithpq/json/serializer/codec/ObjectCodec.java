package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.Common.Const;
import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;
import cn.xxywithpq.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * Object 解析器
 * Created by panqian on 2017/6/8.
 */
public class ObjectCodec extends AbstractSerializer implements ISerializer {

    StringJoiner sj;

    private String serializerObject(Object o) {
        sj = new StringJoiner(",", "{", "}");
        Class<?> c = o.getClass();
        Field[] publicFields = c.getFields();

        if (null != publicFields && publicFields.length > 0) {
            for (Field f : publicFields) {
                Object fieldValue = ReflectionUtils.getFieldValue(f, o);
                if (Long.class == fieldValue.getClass()) {
                    ISerializer iSerializer = new LongCodec();
                    String value = (String) iSerializer.writeJsonString(fieldValue);
                    sj.add(Const.SINGLE_QUOTES + f.getName() + Const.SINGLE_QUOTES + Const.COLON + value);
                }
            }
        }
        return sj.toString();
    }

    @Override
    public Object writeJsonString(Object o) {
        String result = serializerObject(o);
        return result;
    }
}
