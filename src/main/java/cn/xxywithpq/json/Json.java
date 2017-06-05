package cn.xxywithpq.json;

import cn.xxywithpq.Common.Const;
import cn.xxywithpq.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.logging.Logger;

/**
 * 提供方便的Json转换和解析服务
 * Created by panqian on 2017/4/8.
 */
public class Json {

    private static Logger logger = Logger.getLogger(Json.class.getName());

    public static <T> String toJsonString(T t) {
        if (Objects.isNull(t)){
            return null;
        }
        Class<?> c = t.getClass();
        if (c.getTypeName().equals(Const.STRING_TYPE)) {
            String string = (String) t;
            StringBuffer sb = new StringBuffer(string.length() + 2);

            sb.append("\"").append(string).append("\"");
            return sb.toString();
        } else if (c.getTypeName().equals(Const.LONG_TYPE)) {
            Long l = (Long) t;
            String string = String.valueOf(l);
            StringBuffer sb = new StringBuffer(string.length());
            sb.append(string);
            return sb.toString();
        } else {
            StringJoiner stringJoiner = new StringJoiner(",", "{", "}");
            Field[] declaredFields = c.getDeclaredFields();
            if (null != declaredFields && declaredFields.length > 0) {
                for (Field f : declaredFields) {
                    Object fieldValue = ReflectionUtils.getFieldValue(f, t);
                    stringJoiner.add("\"" + f.getName() + "\":\"" + fieldValue + "\"");
                }
            }
            return stringJoiner.toString();
        }
    }
}
