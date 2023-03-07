package org.leechiwi.happyseven.files.web.api.result;

import org.apache.commons.lang3.StringUtils;

public class Result<T> {
    /**
     * 状态码，比如1000代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public Result(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public Result(ResultCode code, T data) {
        this(code.getCode(), code.getMsg(), data);
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(StringUtils.EMPTY);
    }

    public static <T> Result success(T data) {
        return new Result(data);
    }

    public static <T> Result fail() {
        return new Result(ResultCode.FAILED, StringUtils.EMPTY);
    }

    public static <T> Result fail(T data) {
        return new Result(ResultCode.FAILED, data);
    }

    public static Result fail(String msg) {
        return new Result<String>(ResultCode.FAILED.getCode(), msg, StringUtils.EMPTY);
    }

    public static <T> Result fail(String msg, T data) {
        return new Result(ResultCode.FAILED.getCode(), msg, data);
    }

    public static <T> Result commonResult(ResultCode resultCode, T data) {
        return new Result(resultCode, data);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
