package cn.xxywithpq.json.parse;

import cn.xxywithpq.common.Const;
import cn.xxywithpq.json.AbstractJson;
import cn.xxywithpq.json.IJson;
import cn.xxywithpq.utils.ReflectionUtils;
import cn.xxywithpq.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JsonParser
 * Created by panqian on 2017/6/20.
 */
public class JsonParser extends AbstractJson {

    private static JsonParser jsonParser = new JsonParser();

    private JsonParser() {
    }

    public static JsonParser getInstance() {
        return jsonParser;
    }

    public <T> T parseObject(String text, Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        JsonObject jsonObject = parseObject(text);
        return parseObject(jsonObject, clazz);
    }

    private <T> T parseObject(JsonObject jsonObject, Class<T> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (Objects.isNull(jsonObject)) {
            return null;
        }

        T t = clazz.newInstance();

        Set<String> keys = jsonObject.keySet();
        if (keys.size() > 0) {
            //查找该类所有声明的方法（除Object）
            List<Method> allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(clazz);

            //筛选public set方法
            ArrayList<Method> publicSetMethods = new ArrayList<>();
            if (null != allDeclaredMethods && allDeclaredMethods.size() > 0) {
                for (Method m : allDeclaredMethods) {
                    String modifier = ReflectionUtils.getModifier(m);
                    if (modifier.contains(Const.PUBLIC) && m.getName().contains(Const.SET)) {
                        publicSetMethods.add(m);
                    }
                }
            }
            if (null != publicSetMethods && publicSetMethods.size() > 0) {
                for (Method m : publicSetMethods) {
                    String methodName = m.getName();
                    String variable = methodName.substring(3, methodName.length());
                    Class<?>[] parameterTypes = m.getParameterTypes();
                    Class parameterType = null;
                    if (null != parameterTypes && parameterTypes.length == 1) {
                        parameterType = parameterTypes[0];
                    }
                    variable = variable.substring(0, 1).toLowerCase() + variable.substring(1, variable.length());
                    if (jsonObject.containsKey(variable)) {
                        Object oo = jsonObject.get(variable);
                        IJson suitableHandler = getSuitableParseHandler(parameterType);
                        Object parse = suitableHandler.parse(oo, m);
                        m.invoke(t, parse);
                    }
                }
            }
        }
        return t;
    }

    public JsonObject parseObject(String text) {
        Stack<Object> stacks = new Stack();

        String status = Const.BEGIN;

        char[] chars = text.toCharArray();

        if (chars.length > 0) {
            if (!(Const.PRE_BRACE_CHAR == chars[0])) {
                throw new RuntimeException("The structure of jsonString is wrong ");
            }

            for (int i = 0; i < chars.length; i++) {
                switch (chars[i]) {
                    //碰到 {
                    case Const.PRE_BRACE_CHAR: {
                        status = Const.KEY;
                        JsonObject jo = new JsonObject();
                        stacks.push(jo);
                        break;
                    }
                    //碰到 }
                    case Const.POST_BRACE_CHAR: {
                        groupJsonObject(stacks);
                        break;
                    }
                    //碰到 "
                    case Const.SINGLE_QUOTES_CHAR: {
                        if (Const.KEY.equals(status)) {
//                            status = Const.READING;
                            StringBuffer sb = new StringBuffer();
                            stacks.push(sb);
                        } else if (Const.VALUE.equals(status)) {
                            StringBuffer sb = new StringBuffer();
                            stacks.push(sb);
                            status = Const.READING;
                        }
                        break;
                    }
                    //碰到 :
                    case Const.COLON_CHAR: {
                        status = Const.VALUE;
                        break;
                    }
                    //碰到 ,
                    case Const.COMMA_CHAR: {
                        groupJsonObject(stacks);
                        status = Const.KEY;
                        break;
                    }
                    //碰到 [
                    case Const.PRE_BRACKET_CHAR: {
                        JsonArray jsonArray = new JsonArray();
                        stacks.push(jsonArray);
                        break;
                    }
                    //碰到 ]
                    case Const.POST_BRACKET_CHAR: {
                        if (JsonObject.class == stacks.peek().getClass()) {
                            JsonObject jsonObject = (JsonObject) stacks.pop();
                            if (JsonArray.class == stacks.peek().getClass()) {
                                JsonArray jsonArray = (JsonArray) stacks.pop();
                                jsonArray.add(jsonObject);
                                stacks.push(jsonArray);
                            }
                        }
                        break;
                    }
                    default: {
                        if (Const.VALUE.equals(status)) {
                            StringBuffer sb = new StringBuffer();
                            stacks.push(sb);
                        }
                        status = Const.READING;
                        StringBuffer sb = (StringBuffer) stacks.pop();
                        sb.append(chars[i]);
                        stacks.push(sb);
                        break;
                    }
                }
            }
        }
        if (stacks.size() != 1) {
            throw new RuntimeException("The structure of jsonString is wrong ");
        } else {
            return (JsonObject) stacks.pop();
        }
    }

    private int readValue(Character[] chars, int begin) {

        List<Character> characters = Arrays.asList(chars).subList(begin, chars.length);
        characters.toString();
        Pattern pattern = Pattern.compile("(.*)[.|,|\"|:*].*");
        Matcher isNum = pattern.matcher(characters.toString());
        if (isNum.matches()) {
            return begin + isNum.end(1);
        }

        return 1;
    }

    private void groupJsonObject(Stack stacks) {

        if (StringBuffer.class == stacks.peek().getClass()) {

            StringBuffer value = (StringBuffer) stacks.pop();

            if (StringBuffer.class == stacks.peek().getClass()) {
                StringBuffer key = (StringBuffer) stacks.pop();

                if (JsonObject.class == stacks.peek().getClass()) {
                    JsonObject jsonObject = (JsonObject) stacks.pop();
                    String s = value.toString();
                    if (StringUtils.isNumeric(s)) {
                        if (StringUtils.isIntegerNumeric(s)) {
                            jsonObject.put(key.toString(), new Integer(s));
                        } else {
                            jsonObject.put(key.toString(), new BigDecimal(s));
                        }
                    } else {
                        jsonObject.put(key.toString(), s);
                    }
                    stacks.push(jsonObject);
                } else {
                    throw new RuntimeException("The structure of jsonString is wrong ");
                }

            } else {
                throw new RuntimeException("The structure of jsonString is wrong ");
            }
            //整合StringBuffer&jsonObject 到jsonObject
        } else if (JsonObject.class == stacks.peek().getClass()) {

            JsonObject value = (JsonObject) stacks.pop();

            if (stacks.isEmpty()) {
                stacks.push(value);
            } else if (StringBuffer.class == stacks.peek().getClass()) {
                StringBuffer key = (StringBuffer) stacks.pop();

                groupJsonObject(stacks, key, value);
            } else if (JsonArray.class == stacks.peek().getClass()) {
                JsonArray array = (JsonArray) stacks.pop();
                array.add(value);
                stacks.push(array);
            } else {
                throw new RuntimeException("The structure of jsonString is wrong ");
            }
            //整合StringBuffer&JsonArray 到jsonObject
        } else if (JsonArray.class == stacks.peek().getClass()) {
            JsonArray value = (JsonArray) stacks.pop();

            if (StringBuffer.class == stacks.peek().getClass()) {
                StringBuffer key = (StringBuffer) stacks.pop();

                groupJsonObject(stacks, key, value);
            }
        }
    }

    private void groupJsonObject(Stack stacks, Object key, Object value) {
        if (JsonObject.class == stacks.peek().getClass()) {
            JsonObject jsonObject = (JsonObject) stacks.pop();
            jsonObject.put(key.toString(), value);
            stacks.push(jsonObject);
        } else {
            throw new RuntimeException("The structure of jsonString is wrong ");
        }
    }
}
