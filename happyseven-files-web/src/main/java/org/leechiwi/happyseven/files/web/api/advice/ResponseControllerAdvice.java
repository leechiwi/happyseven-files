package org.leechiwi.happyseven.files.web.api.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leechiwi.happyseven.files.web.api.result.Result;
import org.leechiwi.happyseven.files.web.api.result.ResultCode;
import org.leechiwi.happyseven.files.web.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

@RestControllerAdvice(basePackages = {"org.leechiwi.happyseven.files.web"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 日志记录对象
     */
    private static final Logger logger = LoggerFactory.getLogger(ResponseControllerAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        /*// 如果接口返回的类型本身就是Result那就没有必要进行额外的操作，返回false
        if(returnType.getMethod().isAnnotationPresent(WithoutOtherPro.class)){
            return false;
        }*/
       /* String name = returnType.getParameterType().getName();
        if(name.contains("ResultVO")){
            return false;
        }*/
        return !returnType.getParameterType().isAssignableFrom(Result.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getParameterType().isAssignableFrom(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String str=((String)data);
                if( str.startsWith("FailMsg:")){
                    return objectMapper.writeValueAsString(new Result<>(ResultCode.FAILED.getCode(), str.split(":", -1)[1], ""));
                }
                // 将数据包装在Result里后，再转换为json字符串返回
                return objectMapper.writeValueAsString(new Result<>(data));
            } catch (JsonProcessingException e) {
                logger.error("返回String类型异常", e);
                throw new APIException("返回String类型错误");
            }
        }
        // 将原本的数据包装在Result里
        return Result.success(data);
    }
}
