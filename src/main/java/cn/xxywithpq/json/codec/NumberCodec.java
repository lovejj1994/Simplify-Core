package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Number 解析器
 * Created by panqian on 2017/6/6.
 */
public class NumberCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Number n = (Number) o;
        sb = new StringBuffer(n.toString().length());
        numberHandle(sb, n);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Type[] trueType) {
        BigDecimal bd = (BigDecimal) o;
        return bd.doubleValue();
    }
}
