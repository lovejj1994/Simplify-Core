package cn.xxywithpq;

import cn.xxywithpq.json.JsonObject;
import cn.xxywithpq.json.parse.JsonParser;
import cn.xxywithpq.json.serializer.JsonSerializer;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 提供方便的Json转换和解析服务
 * Created by panqian on 2017/4/8.
 */
public class Json {

    private static Logger logger = Logger.getLogger(Json.class.getName());
    private static JsonSerializer jsonSerializer;
    private static JsonParser jsonParser;

    private Json() {
    }

    public static String toJsonString(Object t) {
        if (Objects.isNull(t)) {
            return null;
        }
        jsonSerializer = JsonSerializer.getInstance();

        return jsonSerializer.convertToJsonString(t);
    }

    public static JsonObject parseObject(String text) {
        if (Objects.isNull(text)) {
            return null;
        }
        jsonParser = JsonParser.getInstance();

        return jsonParser.parseObject(text);
    }

    public static <T> T parseObject(String text, Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (Objects.isNull(text)) {
            return null;
        }
        jsonParser = JsonParser.getInstance();

        return jsonParser.parseObject(text, clazz);
    }
}
