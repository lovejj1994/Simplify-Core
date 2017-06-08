package cn.xxywithpq.json.serializer;

import cn.xxywithpq.Common.Const;
import cn.xxywithpq.json.serializer.codec.*;

/**
 * Created by panqian on 2017/6/6.
 */
public abstract class AbstractSerializer {
    protected void characterHandle(StringBuffer sb, CharSequence cs) {
        sb.append(Const.SINGLE_QUOTES).append(cs).append(Const.SINGLE_QUOTES);
    }

    protected void characterHandle(StringBuffer sb, Character cs) {
        sb.append(Const.SINGLE_QUOTES).append(cs).append(Const.SINGLE_QUOTES);
    }

    protected void numberHandle(StringBuffer sb, Number cs) {
        sb.append(cs);
    }



}
