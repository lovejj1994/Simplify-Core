package cn.xxywithpq.json.parse;

import cn.xxywithpq.Json;

import java.util.HashMap;

/**
 * Created by panqian on 2017/6/20.
 */
public class JsonObject extends HashMap<String, Object> {

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }
}
