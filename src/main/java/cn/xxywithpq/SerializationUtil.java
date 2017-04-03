package cn.xxywithpq;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 序列化对象工具类
 * V1.0
 * Created by panqian on 2017/4/1.
 */
public class SerializationUtil {
    private static Logger logger = Logger.getLogger(SerializationUtil.class.getName());

    /**
     * 序列化对象 对象应实现Serializable接口
     * @param obj 要序列化的对象
     * @param <T> 对象类型
     * @return byte数组
     */
    public static <T extends Serializable> byte[] serialize(T obj) {
        if (Objects.isNull(obj)) {
            throw new NullPointerException("传入对象不能为空");
        }
        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream)
        ) {

            objectOutputStream.writeObject(obj);

            return arrayOutputStream.toByteArray();
        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param bytes byte数组
     * @param t 要反序列化的对象
     * @param <T> 对象的类型
     * @return 对象
     */
    public static <T> T deserialize(byte[] bytes,Class<T> t) {
        if (Objects.isNull(bytes)) {
            throw new NullPointerException("传入数组不能为空");
        }
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)
        ) {

            return (T) objectInputStream.readObject();

        } catch (IOException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
