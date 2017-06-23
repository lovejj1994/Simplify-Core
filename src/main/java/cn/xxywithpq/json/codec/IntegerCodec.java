package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Integer 解析器
 * Created by panqian on 2017/6/6.
 */
public class IntegerCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Integer i = (Integer) o;
        sb = new StringBuffer(1);
        numberHandle(sb, i);
        return sb.toString();
    }

    @Override
    public Object parse(Object o,Type[] trueType) {
        if (Objects.isNull(o)) {
            return null;
        }
        BigDecimal bd = (BigDecimal) o;
        return bd.intValue();
    }
}
