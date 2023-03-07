package org.leechiwi.happyseven.files.web.api.advice;

import org.leechiwi.happyseven.files.web.api.result.Result;
import org.leechiwi.happyseven.files.web.exception.APIException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BindException.class)
    public Result<String> MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return Result.fail("参数校验失败", objectError.getDefaultMessage());
    }
    @ExceptionHandler(APIException.class)
    public Result<String> APIExceptionHandler(APIException e) {
        // 注意哦，这里传递的响应码枚举
        return Result.fail("接口异常");
    }
}
