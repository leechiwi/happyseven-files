package org.leechiwi.happyseven.files.web.exception;

/**
 * APIException
 * @author lenovo
 *
 */
public class APIException extends RuntimeException {
    private int code;
    private String msg;
    private Exception exc;
    public APIException() {
        this(1001, "接口错误");
    }
    public APIException(String msg) {
        this(1001, msg);
    }
    public APIException(String msg, Exception exc) {
        this(1001, msg);
        this.exc = exc;
    }
    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public APIException(int code, String msg, Exception exc) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.exc = exc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
   /**
    * @return the exc
    */
   public Exception getExc() {
      return exc;
   }
   /**
    * @param exc the exc to set
    */
   public void setExc(Exception exc) {
      this.exc = exc;
   }
    
}
