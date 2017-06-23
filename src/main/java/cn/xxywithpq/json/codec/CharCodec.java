package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.serializer.AbstractSerializer;

import java.lang.reflect.Type;

/**
 * Char 解析器
 * Created by panqian on 2017/6/6.
 */
public class CharCodec extends AbstractSerializer implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Character c = (Character) o;
        sb = new StringBuffer(1);
        characterHandle(sb, c);
        return sb.toString();
    }

    @Override
    public Object parse(Object o,Type[] trueType) {
        return null;
    }
}
