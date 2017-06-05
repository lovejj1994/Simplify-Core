package cn.xxywithpq.json;

import cn.xxywithpq.ReflectionUtils;
import cn.xxywithpq.json.Bean.User;
import com.alibaba.fastjson.JSON;

import java.time.Instant;
import java.util.logging.Logger;

/**
 * Created by panqian on 2017/4/8.
 */
public class JsonTest {

    private static Logger logger = Logger.getLogger(Json.class.getName());

    public static void main(String[] args) {

        String string;

        //String型
        String s = new String("hello json");
        string = Json.toJsonString(s);
        logger.info("String :" + string);

        //Long型
        long l = 2l;
        string = Json.toJsonString(l);
        logger.info("Long :" + string);

        Long l1 = 2l;
        string = JSON.toJSONString(l1);
        System.out.println(string);

        User user = new User();
        user.id = 2l;
        user.setName("dsds");

        long l2 = System.currentTimeMillis();
        System.out.println("alibaba:" + JSON.toJSONString(null));
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l2));
        long l3 = System.currentTimeMillis();
        System.out.println("Simplify:" + cn.xxywithpq.json.Json.toJsonString(null));
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l3));
//        user.setId(2l);
//        user.setName("ldklasd");
//        System.out.println(Json.toJsonString(user));
//        System.out.println(JSON.toJSONString(user));
    }
}
