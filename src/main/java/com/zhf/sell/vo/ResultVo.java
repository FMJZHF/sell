package com.zhf.sell.vo;

import lombok.Data;

/**
 * http 请求最外层对象
 */
@Data
public class ResultVo<T> {

    /** 错误码 */
    private Integer code;
    /** 提示信息 */
    private String msg;
    /** 具体内容 */
    private T data;

}
