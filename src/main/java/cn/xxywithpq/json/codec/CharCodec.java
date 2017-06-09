package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * Char 解析器
 * Created by panqian on 2017/6/6.
 */
public class CharCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Character c = (Character) o;
        sb = new StringBuffer(1);
        characterHandle(sb, c);
        return sb.toString();
    }
}
