package cn.xxywithpq.json.serializer;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;

import java.util.logging.Logger;

/**
 * Created by panqian on 2017/6/8.
 */

public class JsonSerializer extends AbstractJson {

    private static Logger logger = Logger.getLogger(JsonSerializer.class.getName());

    private static JsonSerializer jsonSerializer = new JsonSerializer();

    private JsonSerializer() {
    }

    public static JsonSerializer getInstance() {
        return jsonSerializer;
    }

    public String convertToJsonString(Object o) {
        Class<?> c = o.getClass();
        IJson suitableHandler = getSuitableHandler(c);
        return (String) suitableHandler.writeJsonString(o);
    }

}
