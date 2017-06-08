package cn.xxywithpq.json.serializer;

import java.io.Serializable;

/**
 * Created by panqian on 2017/6/6.
 */
public interface ISerializer extends Serializable {
    public Object writeJsonString(Object o);
}
