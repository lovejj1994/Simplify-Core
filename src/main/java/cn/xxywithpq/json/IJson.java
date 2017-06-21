package cn.xxywithpq.json;

import java.io.Serializable;

/**
 * Created by panqian on 2017/6/6.
 */
public interface IJson extends Serializable {
    public Object writeJsonString(Object o);

    public Object parse(Object o);
}
