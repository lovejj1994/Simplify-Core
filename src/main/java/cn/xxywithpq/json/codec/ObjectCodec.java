package cn.xxywithpq.json.codec;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.json.JsonException;
import cn.xxywithpq.json.JsonObject;
import cn.xxywithpq.json.serializer.JsonSerializer;
import cn.xxywithpq.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.Collator;
import java.util.*;
import java.util.logging.Logger;

/**
 * Object 解析器
 * Created by panqian on 2017/6/8.
 */
public class ObjectCodec extends AbstractJson implements IJson {

    private static Logger logger = Logger.getLogger(JsonSerializer.class.getName());

    StringJoiner sj;

    private String serializerObject(Object o) {
        sj = new StringJoiner(Const.COMMA, Const.PRE_BRACE, Const.POST_BRACE);
        Class<?> cClass = o.getClass();

        //查找该类所有声明的方法（除Object）
        List<Method> allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(cClass);

        //筛选public get方法
        ArrayList<Method> publicGetMethods = new ArrayList<>();
        if (null != allDeclaredMethods && allDeclaredMethods.size() > 0) {
            for (Method m : allDeclaredMethods) {
                String modifier = ReflectionUtils.getModifier(m);
                if (modifier.contains(Const.PUBLIC) && (m.getName().startsWith(Const.GET) || m.getName().startsWith(Const.IS))) {
                    publicGetMethods.add(m);
                }
            }
        }

        if (null != publicGetMethods && publicGetMethods.size() > 0) {

            Collections.sort(publicGetMethods, (x, y) -> Collator.getInstance().compare(x.getName().startsWith(Const.IS) ? x.getName().replace(Const.IS, Const.GET) : x.getName(), y.getName().startsWith(Const.IS) ? y.getName().replace(Const.IS, Const.GET) : y.getName()));
            for (Method method : publicGetMethods) {
                String name = method.getName();
                String substring;
                if (name.startsWith(Const.IS)) {
                    substring = name.substring(2, name.length());
                } else {
                    substring = name.substring(3, name.length());
                }
                char c = substring.charAt(0);
                if (c >= 'A' && c <= 'Z') {
                    Character b = (char) (c + 32);
                    String key = b.toString().concat(substring.substring(1, substring.length()));
                    try {
                        Object invoke = method.invoke(o);
                        if (Objects.nonNull(invoke)) {
                            sj.add(Const.SINGLE_QUOTES + key + Const.SINGLE_QUOTES + Const.COLON + JsonSerializer.getInstance().convertToJsonString(invoke));
                        }
                    } catch (IllegalAccessException e) {
                        logger.severe(e.getMessage());
                        throw new JsonException("serializerObject fail", e);
                    } catch (InvocationTargetException e) {
                        logger.severe(e.getMessage());
                        throw new JsonException("serializerObject fail", e);
                    }
                }
            }
        }

        return sj.toString();
    }


    @Override
    public Object writeJsonString(Object o) {
        String result = serializerObject(o);
        return result;
    }

    @Override
    public Object parse(Object o, Method m) {
        JsonObject jo = (JsonObject) o;
        Type[] genericParameterTypes = m.getGenericParameterTypes();
        Type t = null;
        for (Type type : genericParameterTypes) {
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                for (Type t1 : ((ParameterizedType) type).getActualTypeArguments()) {
                    t = t1;
                }
            }
        }
        try {
            Class<?> aClass;
            if (Objects.isNull(t)) {
                aClass = m.getParameterTypes()[0].getComponentType();
            } else {
                aClass = Class.forName(t.getTypeName());
            }
            Object o1 = aClass.newInstance();
            //查找该类所有声明的方法（除Object）
            List<Method> allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(aClass);

            //筛选public set方法
            ArrayList<Method> publicSetMethods = new ArrayList<>();
            if (null != allDeclaredMethods && allDeclaredMethods.size() > 0) {
                for (Method md : allDeclaredMethods) {
                    String modifier = ReflectionUtils.getModifier(md);
                    if (modifier.contains(Const.PUBLIC) && md.getName().startsWith(Const.SET)) {
                        publicSetMethods.add(md);
                    }
                }
            }

            if (null != publicSetMethods && publicSetMethods.size() > 0) {
                for (Method md : publicSetMethods) {
                    String methodName = md.getName();
                    String variable = methodName.substring(3, methodName.length());
                    Class<?>[] parameterTypes = md.getParameterTypes();
                    Class parameterType = null;
                    if (null != parameterTypes && parameterTypes.length == 1) {
                        parameterType = parameterTypes[0];
                    }
                    variable = variable.substring(0, 1).toLowerCase() + variable.substring(1, variable.length());
                    if (jo.containsKey(variable)) {
                        Object oo = jo.get(variable);
                        IJson suitableHandler = getSuitableHandler(parameterType);
                        Object parse = suitableHandler.parse(oo, md);
                        try {
                            md.invoke(o1, parse);
                        } catch (IllegalAccessException e) {
                            logger.severe(e.getMessage());
                            throw new JsonException("ObjectCodec fail", e);
                        } catch (InvocationTargetException e) {
                            logger.severe(e.getMessage());
                            throw new JsonException("ObjectCodec fail", e);
                        }
                    }
                }
            }
            return o1;
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            throw new JsonException(e);
        } catch (IllegalAccessException e) {
            logger.severe(e.getMessage());
            throw new JsonException(e);
        } catch (InstantiationException e) {
            logger.severe(e.getMessage());
            throw new JsonException(e);
        }
    }
}
