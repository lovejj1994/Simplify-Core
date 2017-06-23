package cn.xxywithpq.json;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by panqian on 2017/6/6.
 */
public interface IJson extends Serializable {
    Object writeJsonString(Object o);

    Object parse(Object o,Type[] trueType);
}
