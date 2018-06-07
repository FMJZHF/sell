package com.zhf.sell.utils;

import com.zhf.sell.enums.CodeEnum;

public class EnumUtil {

    /**
     * 根据状态进行对比枚举，在前台更好的获取到值
     * 这种方法更适合前台遍历
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
