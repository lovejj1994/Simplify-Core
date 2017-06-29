package cn.xxywithpq.json.codec;

import cn.xxywithpq.SimpleDate;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.utils.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Date 解析器
 * Created by panqian on 2017/6/6.
 */
public class DateCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Date d = (Date) o;
        sb = new StringBuffer(((Long) d.getTime()).toString().length());
        dateHandle(sb, d);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        String date = o.toString();
        if (StringUtils.isNumeric(date)) {
            return SimpleDate.objectToDate(((BigDecimal) o).longValue());
        } else {
            return SimpleDate.objectToDate(date);
        }
    }
}
