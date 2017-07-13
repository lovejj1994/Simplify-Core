package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;

/**
 * Char 解析器
 * Created by panqian on 2017/6/6.
 */
public class CharCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Character c = (Character) o;
        sb = new StringBuffer(1);
        characterHandle(sb, c);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        return ((String) o).toCharArray()[0];
    }
}
