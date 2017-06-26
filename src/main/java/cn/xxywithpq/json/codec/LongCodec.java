package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Char 解析器
 * Created by panqian on 2017/6/6.
 */
public class LongCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Long l = (Long) o;
        sb = new StringBuffer(1);
        numberHandle(sb, l);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        if (Objects.isNull(o)) {
            return null;
        }
        BigInteger bi = new BigInteger(o + "");
        return bi.longValue();
    }
}
