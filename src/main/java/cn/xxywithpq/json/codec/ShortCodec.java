package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;

/**
 * Short 解析器
 * Created by panqian on 2017/6/6.
 */
public class ShortCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Short s = (Short) o;
        sb = new StringBuffer(1);
        numberHandle(sb, s);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        return null;
    }
}
