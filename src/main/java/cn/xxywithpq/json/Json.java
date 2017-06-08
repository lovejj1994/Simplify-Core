package cn.xxywithpq.json;

import cn.xxywithpq.json.serializer.JsonSerializer;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * 提供方便的Json转换和解析服务
 * Created by panqian on 2017/4/8.
 */
public class Json {

    private Json() {
    }

    private static Logger logger = Logger.getLogger(Json.class.getName());

    private static JsonSerializer jsonSerializer;

    public static String toJsonString(Object t) {
        if (Objects.isNull(t)) {
            return null;
        }
        jsonSerializer = new JsonSerializer();

        return jsonSerializer.convertToJsonString(t);
    }
}
