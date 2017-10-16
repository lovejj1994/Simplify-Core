package cn.xxywithpq.utils;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by panqian on 2017/6/5.
 */
public class StringUtils {

    private static Pattern IS_NUMERIC_PATTERN = Pattern.compile("(\\d*)(\\.?)(\\d*)");
    private static Pattern IS_INTEGER_NUMERIC = Pattern.compile("(\\d+)(\\.{1})(\\d*)");
    private static Pattern IS_BOOLEAN = Pattern.compile("true|false");
    // Empty checks
    //-----------------------------------------------------------------------

    /**
     * <p>Checks if a String is empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * <p>
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     * <p>
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }


    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     * <p>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is
     * not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }


    /**
     * 判断字符串是不是数字（包括小数）
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher isNum = IS_NUMERIC_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        } else {
            //如果 是 "123." 这种会返回false
            String group = isNum.group(2);
            String group1 = isNum.group(3);
            if (StringUtils.isNotBlank(group) && StringUtils.isBlank(group1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是不是Integer, 先经过isNumeric(String str)判断是不是数字  再使用此方法
     *
     * @param str
     * @return
     */
    public static boolean isIntegerNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher isNum = IS_INTEGER_NUMERIC.matcher(str);
        if (!isNum.matches()) {
            BigInteger bigDecimal = new BigInteger(str);
            if (Integer.MIN_VALUE <= bigDecimal.longValue() && bigDecimal.longValue() <= Integer.MAX_VALUE) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * 判断字符串是不是boolean, 先经过isNumeric(String str)判断是不是数字  再使用此方法
     *
     * @param str
     * @return
     */
    public static boolean isBoolean(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        Matcher isNum = IS_BOOLEAN.matcher(str);
        if (!isNum.matches()) {
            return false;
        } else {
            return true;
        }
    }
}
