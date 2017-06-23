package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Type;

/**
 * String 解析器
 * Created by panqian on 2017/6/6.
 */
public class StringCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        String string = (String) o;
        sb = new StringBuffer(string.length() + 2);
        characterHandle(sb, string);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Type[] trueType) {
        return o;
    }
}
