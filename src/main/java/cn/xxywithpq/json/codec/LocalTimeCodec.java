package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.lang.reflect.Method;
import java.time.LocalTime;

/**
 * LocalTimeCodec 解析器
 * Created by panqian on 2017/6/6.
 */
public class LocalTimeCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        LocalTime ld = (LocalTime) o;
        sb = new StringBuffer(23);
        localTimeHandle(sb, ld);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        return null;
    }
}
