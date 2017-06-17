package cn.xxywithpq;

import cn.xxywithpq.json.Bean.BaseEntity;
import cn.xxywithpq.json.Bean.User;
import cn.xxywithpq.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println(Arrays.toString(BaseEntity.class.getDeclaredMethods()));
        //不分 public private，不包括超类
        System.out.println(Arrays.toString(User.class.getDeclaredMethods()));
        //不分 public private，包括超类
        System.out.println(Arrays.toString(User.class.getMethods()));

        //查找该类所有声明的方法（除Object）
        List<Method> allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(User.class);

        //筛选public get方法
        ArrayList<Method> publicGetMethods = new ArrayList<>();
        if (null != allDeclaredMethods && allDeclaredMethods.size() > 0) {
            for (Method m : allDeclaredMethods) {
                String modifier = ReflectionUtils.getModifier(m);
                if (modifier.contains("public") && m.getName().contains("get")) {
                    publicGetMethods.add(m);
                }
            }
        }

        if (null != publicGetMethods && publicGetMethods.size() > 0) {
            for (Method method : publicGetMethods) {
                String name = method.getName();
                String substring = name.substring(3, name.length());
                char c = substring.charAt(0);
                if (c >= 'A' && c <= 'Z') {
                    Character b = (char) (c - 32);
                    String concat = b.toString().concat(substring.substring(1, substring.length()));
//                    method.invoke()
                }
                System.out.println(substring.charAt(0));
            }
        }

//        for (Method m : User.class.getDeclaredMethods()) {
//        ReflectionUtils.getValueFromDeclaredMethods(User.class);
//        }
    }

}