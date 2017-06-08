package cn.xxywithpq.json.serializer.codec;

import cn.xxywithpq.json.serializer.AbstractSerializer;
import cn.xxywithpq.json.serializer.ISerializer;

/**
 * String 解析器
 * Created by panqian on 2017/6/6.
 */
public class StringCodec extends AbstractSerializer implements ISerializer {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        String string = (String) o;
        sb = new StringBuffer(string.length() + 2);
        characterHandle(sb,string);
        return sb.toString();
    }
}
