package cn.xxywithpq.utils;


import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * 反射的Utils函数集合. 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 *
 * @author panqian
 */
public class ReflectionUtils {

    private static final int ACCESS_MODIFIERS =
            Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
    private static Logger logger = Logger.getLogger(ReflectionUtils.class.getName());

    private ReflectionUtils() {
    }

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName) {

        Field field = getDeclaredField(object.getClass(), fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.severe("ReflectionUtils getFieldValue : " + e.getMessage());
            return result;
        }
        return result;
    }


    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(final Field field, final Object object) {

        if (field == null) {
            throw new IllegalArgumentException("field cannot be empty");
        }

        if (object == null) {
            throw new IllegalArgumentException("object cannot be empty");
        }

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.severe("ReflectionUtils getFieldValue : " + e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.severe("ReflectionUtils setFieldValue : " + e.getMessage());
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException("The object cannot be empty");
        }
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("unchecked")
    public static Field getValueFromDeclaredMethods(final Class clazz) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("The clazz cannot be empty");
        }


//        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method[] declaredMethods = clazz.getMethods();
        if (null != declaredMethods && declaredMethods.length > 0) {
            for (Method method : declaredMethods) {
                System.out.println(method.getName() + "  :  " + getModifier(method));
            }

        }

        return null;
    }

    public static String getModifier(Method m) {
        return getModifier(Modifier.methodModifiers(), m.getModifiers(), m.isDefault());
    }

    /**
     * 获得该方法是共有私有
     */
    @SuppressWarnings("unchecked")
    static String getModifier(int mask, int modifiers, boolean isDefault) {
        int mod = modifiers & mask;

        if (mod != 0 && !isDefault) {
            return Modifier.toString(mod);
        } else {
            int access_mod = mod & ACCESS_MODIFIERS;
            if (access_mod != 0) {
                return Modifier.toString(access_mod);
            }
            if (isDefault) {
                return "default ";
            }
            mod = (mod & ~ACCESS_MODIFIERS);
            if (mod != 0) {
                return Modifier.toString(mod);
            }
        }
        return null;
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("unchecked")
    protected static Field getDeclaredField(final Class clazz, final String fieldName) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("The clazz cannot be empty");
        }
        if (StringUtils.isEmpty(fieldName)) {
            throw new IllegalArgumentException("this String argument must have text; it must not be null, empty, or blank");
        }
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 循环向上转型,获取类的所有DeclaredMethods.除了Object
     */
    @SuppressWarnings("unchecked")
    public static List<Method> getAllDeclaredMethods(final Class clazz) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("The clazz cannot be empty");
        }
        ArrayList<Method> methods = new ArrayList<>();
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            Method[] declaredMethods = superClass.getDeclaredMethods();
            if (null != declaredMethods && declaredMethods.length > 0) {
                for (Method m : declaredMethods) {
                    methods.add(m);
                }
            }
        }
        return methods;
    }

    /**
     * 强制转换fileld可访问.
     */
    protected static void makeAccessible(final Field field) {
//        if (!isPublic(field.getModifiers()) || !isPublic(field.getDeclaringClass().getModifiers())) {
//            field.setAccessible(true);
//        }
    }

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
     * HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     * determined
     */

    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warning(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warning("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warning(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 提取集合中的对象的属性,组合成List.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static List fetchElementPropertyToList(final Collection collection, final String propertyName) throws Exception {

        List list = new ArrayList();

        for (Object obj : collection) {
            list.add(getFieldValue(obj, propertyName));
        }

        return list;
    }

    /**
     * 查找Type中的参数泛型
     *
     * @param t
     * @return
     */
    public static Type[] getActualTypeArguments(Type t) {
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            return ((ParameterizedType) t).getActualTypeArguments();
        }
        return null;
    }
}