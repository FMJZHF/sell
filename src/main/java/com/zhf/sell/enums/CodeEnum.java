package com.zhf.sell.enums;

/**
 * 根据传递的不同类型
 * 返回不同的枚举
 * @param <T>
 */
public interface CodeEnum<T> {
    Integer getCode();
   // T getCode(); //如果类型多的话
}
