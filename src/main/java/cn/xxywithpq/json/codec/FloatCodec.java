package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;

/**
 * Float 解析器
 * Created by panqian on 2017/6/6.
 */
public class FloatCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Float f = (Float) o;
        sb = new StringBuffer(1);
        numberHandle(sb, f);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        return null;
    }
}
