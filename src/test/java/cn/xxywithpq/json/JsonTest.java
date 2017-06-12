package cn.xxywithpq.json;

import cn.xxywithpq.Json;
import cn.xxywithpq.json.Bean.User;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by panqian on 2017/4/8.
 */
@DisplayName("the jsonTest test case")
public class JsonTest {

    private static Logger logger = Logger.getLogger(JsonTest.class.getName());

    @BeforeAll
    static void initAll() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    @BeforeEach
    void init() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void jsonTest() {
        //String型
        System.out.println("String型 test：");
        String test = "hello world";
        long l2 = System.currentTimeMillis();
        String alibaba = JSON.toJSONString(test);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l2));
        long l3 = System.currentTimeMillis();
        String simplify = Json.toJsonString(test);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l3));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Boolean型
        System.out.println("Boolean型 test：");
        boolean test1 = true;
        long l4 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test1);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l4));
        long l5 = System.currentTimeMillis();
        simplify = Json.toJsonString(test1);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l5));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Byte型
        System.out.println("Byte型 test：");
        byte test2 = 'a';
        long l6 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test2);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l6));
        long l7 = System.currentTimeMillis();
        simplify = Json.toJsonString(test2);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l7));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Double型
        System.out.println("Double型 test：");
        double test3 = 52.325d;
        long l8 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test3);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l8));
        long l9 = System.currentTimeMillis();
        simplify = Json.toJsonString(test3);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l9));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Float型
        System.out.println("Float型 test：");
        float test4 = 52.325f;
        long l10 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test4);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l10));
        long l11 = System.currentTimeMillis();
        simplify = Json.toJsonString(test4);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l11));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Char型
        System.out.println("Char型 test：");
        char test5 = 'a';
        long l12 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test5);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l12));
        long l13 = System.currentTimeMillis();
        simplify = Json.toJsonString(test5);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l13));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Short型
        System.out.println("Short型 test：");
        short test6 = '1';
        long l14 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test6);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l14));
        long l15 = System.currentTimeMillis();
        simplify = Json.toJsonString(test6);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l15));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Integer型
        System.out.println("Integer型 test：");
        Integer test7 = 1;
        long l16 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test7);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l16));
        long l17 = System.currentTimeMillis();
        simplify = Json.toJsonString(test7);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l17));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Long型
        System.out.println("Long型 test：");
        long test8 = 1000l;
        long l18 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(test8);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l18));
        long l19 = System.currentTimeMillis();
        simplify = Json.toJsonString(test8);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l19));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //带基本类型的Bean
        System.out.println("带基本类型的Bean test：");
        User user = new User();
        user.id = 2l;
        user.setName("panqian");
        long l20 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(user);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l20));
        long l21 = System.currentTimeMillis();
        simplify = Json.toJsonString(user);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l21));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //List
        System.out.println("List test：");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        long l22 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(strings);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l22));
        long l23 = System.currentTimeMillis();
        simplify = Json.toJsonString(strings);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l23));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //空List
        System.out.println("空List test：");
        ArrayList<String> strings1 = new ArrayList<>();
        long l24 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(strings1);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l24));
        long l25 = System.currentTimeMillis();
        simplify = Json.toJsonString(strings1);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l25));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Set
        System.out.println("Set test：");
        Set<String> strings2 = new HashSet<>();
        strings2.add("a");
        strings2.add("b");
        strings2.add("c");
        long l26 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(strings2);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l26));
        long l27 = System.currentTimeMillis();
        simplify = Json.toJsonString(strings2);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l27));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //空Set
        System.out.println("空Set test：");
        Set<String> strings3 = new HashSet<>();
        long l28 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(strings3);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l28));
        long l29 = System.currentTimeMillis();
        simplify = Json.toJsonString(strings3);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l29));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //Map
        System.out.println("Map test：");
        Map<String, Object> maps = new HashMap<>();
        maps.put("name", "panqian");
        maps.put("age", 23);
        long l30 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(maps);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l30));
        long l31 = System.currentTimeMillis();
        simplify = Json.toJsonString(maps);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l31));

        assertEquals(alibaba, simplify);

        System.out.println("============================分割线=================================");

        //空Map
        System.out.println("空Map test：");
        Map<String, Object> maps1 = new HashMap<>();
        long l32 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(maps1);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l32));
        long l33 = System.currentTimeMillis();
        simplify = Json.toJsonString(maps1);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l33));

        assertEquals(alibaba, simplify);

        //Date
        System.out.println("Date test：");
        Date date = new Date();
        LocalDate now = LocalDate.now();
        long l34 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(date);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l34));
        long l35 = System.currentTimeMillis();
        simplify = Json.toJsonString(date);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l35));

        assertEquals(alibaba, simplify);

        //LocalDate
        System.out.println("LocalDate test：");
        LocalDate ld = LocalDate.now();
        long l36 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(ld);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l36));
        long l37 = System.currentTimeMillis();
        simplify = Json.toJsonString(ld);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l37));

        assertEquals(alibaba, simplify);

        //LocalDateTime
        System.out.println("LocalDateTime test：");
        LocalDateTime ldt = LocalDateTime.now();
        long l40 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(ldt);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l40));
        long l41 = System.currentTimeMillis();
        simplify = Json.toJsonString(ldt);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l41));

        assertEquals(alibaba, simplify);

        //LocalTime
        System.out.println("LocalTime test：");
        LocalTime lt = LocalTime.now();
        long l42 = System.currentTimeMillis();
        alibaba = JSON.toJSONString(lt);
        System.out.println("alibaba:" + alibaba);
        System.out.println("alibaba ==============" + (System.currentTimeMillis() - l42));
        long l43 = System.currentTimeMillis();
        simplify = Json.toJsonString(lt);
        System.out.println("Simplify:" + simplify);
        System.out.println("Simplify ==============" + (System.currentTimeMillis() - l43));

        assertEquals(alibaba, simplify);

    }
}
