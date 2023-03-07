package org.leechiwi.happyseven.files.web.api.result;

public enum ResultCode {
    SUCCESS(1001, "操作成功"),

    FAILED(1002, "响应失败"),

    VALIDATE_FAILED(1003, "参数校验失败"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
