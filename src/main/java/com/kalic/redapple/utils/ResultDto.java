package com.kalic.redapple.utils;
/**
 * 后端和前端交互格式:
 *    code : 状态码
 *    msg: 提示消息
 *    data: 传输的数据
 * Dto : Data Transfer Object
 */
public class ResultDto<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultDto() {
    }

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
