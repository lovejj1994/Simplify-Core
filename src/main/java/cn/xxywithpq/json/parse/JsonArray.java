package cn.xxywithpq.json.parse;

import cn.xxywithpq.Json;

import java.util.ArrayList;

/**
 * Created by panqian on 2017/6/20.
 */
public class JsonArray extends ArrayList<JsonObject> {

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }
}
