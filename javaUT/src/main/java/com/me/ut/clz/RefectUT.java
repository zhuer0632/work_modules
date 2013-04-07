package com.me.ut.clz;

import com.me.ut.string.StringUT;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RefectUT
{

    public static <T> T createObject(Class<T> clz)
    {
        T t = null;
        try
        {
            t = clz.getConstructor().newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return t;
    }

    public static List<String> getFieldsName(Class clz)
    {

        List<String> out = new ArrayList<String>();
        Field[] fs = clz.getDeclaredFields();
        if (fs == null || (fs != null && fs.length == 0))
        {
            return out;
        }

        for (int i = 0; i < fs.length; i++)
        {
            Field f = fs[i];
            out.add(f.getName());
        }
        return out;
    }

    /**
     * 指定的对象上设置值
     *
     * @param target
     * @param fieldName
     * @param value     [可以是任意类型]
     */
    public static void write(Object target, String fieldName, Object value)
    {

        Field field = FieldUtils.getDeclaredField(target.getClass(), fieldName,
                true);
        try
        {
            // 检查指定类型
            value = cast(value, field.getDeclaringClass());
            FieldUtils.writeField(field, target, value, true);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * //指定类型[其实只能是String]转换成日期类型的时候，可以接受3种字符串格式 [ "yyyy/MM/dd","yyyy-MM-dd",
     * "yyyy-MM-dd HH:mm:ss"] <br>
     * <br>
     * <p/>
     * //日期类型转换成其他类型的时候[其实只能是String],默认只是 "yyyy-MM-dd" 格式
     *
     * @param <T>
     * @param value
     * @param clz
     * @return
     */
    @SuppressWarnings(
            {
                    "unchecked"
            })
    public static <T> T cast(Object value,
                             Class<T> clz)
    {
        T t = null;
        try
        {
            if (StringUT.isEmpty(value))
            {
                return null;
            }

            String toType_str = clz.getName();

            if (clz.isPrimitive())
            {
                if (toType_str.equals("int"))
                    return (T) Integer.valueOf(String.valueOf(value));
                else if (toType_str.equals("long"))
                    return (T) Long.valueOf(String.valueOf(value));
                else if (toType_str.equals("byte"))
                    return (T) Byte.valueOf(String.valueOf(value));
                else if (toType_str.equals("short"))
                    return (T) Short.valueOf(String.valueOf(value));
                else if (toType_str.equals("float"))
                    return (T) Float.valueOf(String.valueOf(value));
                else if (toType_str.equals("double"))
                    return (T) Double.valueOf(String.valueOf(value));
                else if (toType_str.equals("boolean"))
                {
                    return (T) Boolean.valueOf(String.valueOf(value));
                } else if (toType_str.equals("char"))
                    return (T) Character.valueOf(String.valueOf(value).charAt(0));
                throw new RuntimeException("未知类型");
            }

            // 基本数据类型over
            Class toType = null;
            try
            {
                toType = Class.forName(toType_str);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            if (toType.getSimpleName().equals("Date"))
            {
                return (T) DateUtils.parseDate(value.toString(),
                        "yyyy/MM/dd",
                        "yyyy-MM-dd",
                        "yyyy-MM-dd HH:mm:ss");
            }
            t = (T) ConvertUtils.convert(value,
                    toType);
        } catch (Exception e)
        {
            throw new RuntimeException("字段转换类型异常");
        }
        return t;
    }


}
