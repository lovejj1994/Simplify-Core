package cn.xxywithpq;


import java.util.Arrays;
import java.util.logging.Logger;

/**
 * 序列化对象工具类
 * V1.0
 * Created by panqian on 2017/4/1.
 */
public class SerializationUtilTest {
    private static Logger logger = Logger.getLogger(SerializationUtilTest.class.getName());

    public static void main(String[] args) {
        String str = "i am here";
        //序列化对象，对象应实现了Serializable接口
        byte[] serialize = SerializationUtil.serialize(str);
        logger.info("序列化：" + Arrays.toString(serialize));

        //反序列化对象
        String deserialize = SerializationUtil.deserialize(serialize, String.class);
        logger.info("反序列化：" + deserialize);

    }
}
