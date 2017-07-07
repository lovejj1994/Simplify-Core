package cn.xxywithpq.json.codec;

import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.JsonException;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * Number 解析器
 * Created by panqian on 2017/6/6.
 */
public class NumberCodec extends AbstractJson implements IJson {

    StringBuffer sb;

    @Override
    public Object writeJsonString(Object o) {
        Number n = (Number) o;
        sb = new StringBuffer(n.toString().length());
        numberHandle(sb, n);
        return sb.toString();
    }

    @Override
    public Object parse(Object o, Method m) {
        Class<?> parameterTypes = getParameterTypes(m);
        if (Map.class.isAssignableFrom(parameterTypes)) {
            Type actualTypeArguments = getActualTypeArguments(m);
            return numberParse(o, (Class) actualTypeArguments);
        }
        return numberParse(o, parameterTypes);
    }

    private Object numberParse(Object o, Class parameterTypes) {
        if (parameterTypes == int.class || parameterTypes == Integer.class) {
            if (o instanceof Integer) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).intValue();
            } else if (o instanceof BigInteger) {
                return ((BigInteger) o).intValue();
            }
            return o;
        }

        if (parameterTypes == long.class || parameterTypes == Long.class) {
            if (o instanceof Long) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).longValue();
            } else if (o instanceof Integer) {
                return ((Integer) o).longValue();
            }
        }

        if (parameterTypes == short.class || parameterTypes == Short.class) {
            if (o instanceof Short) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).shortValue();
            } else if (o instanceof Integer) {
                return ((Integer) o).shortValue();
            }
        }

        if (parameterTypes == double.class || parameterTypes == Double.class) {
            if (o instanceof Double) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).doubleValue();
            } else if (o instanceof Integer) {
                return ((Integer) o).doubleValue();
            }
        }
        if (parameterTypes == float.class || parameterTypes == Float.class) {
            if (o instanceof Float) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).floatValue();
            } else if (o instanceof Integer) {
                return ((Integer) o).floatValue();
            }
        }
        if (parameterTypes == byte.class || parameterTypes == Byte.class) {
            if (o instanceof Byte) {
                return o;
            } else if (o instanceof BigDecimal) {
                return ((BigDecimal) o).byteValue();
            } else if (o instanceof Integer) {
                return ((Integer) o).byteValue();
            }
        }

        throw new JsonException("unsupport type " + parameterTypes);
    }
}
