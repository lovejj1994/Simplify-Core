package cn.xxywithpq;

import cn.xxywithpq.json.Bean.BaseEntity;
import cn.xxywithpq.json.Bean.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.logging.Logger;


/**
 * 反射的Utils函数集合. 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 *
 * @author panqian
 */
public class ReflectionTest {

    private static Logger logger = Logger.getLogger(ReflectionTest.class.getName());

    private ReflectionTest() {
    }


    @Test
    void test() {

        System.out.println("==================ReflectionTest====================");

        System.out.println(Arrays.toString(BaseEntity.class.getDeclaredMethods()));
        //不分 public private，不包括超类
        System.out.println(Arrays.toString(User.class.getDeclaredMethods()));
        //不分 public private，包括超类
        System.out.println(Arrays.toString(User.class.getMethods()));
    }

}