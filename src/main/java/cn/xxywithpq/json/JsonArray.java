package cn.xxywithpq.json;

import cn.xxywithpq.Json;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by panqian on 2017/6/20.
 */
public class JsonArray<T extends Object> extends ArrayList<T> {

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }
}
