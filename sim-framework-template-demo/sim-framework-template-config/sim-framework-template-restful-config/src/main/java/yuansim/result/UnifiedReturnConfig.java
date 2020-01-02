package yuansim.result;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import yuansim.view.entity.CommonResult;
import yuansim.view.exception.BusinessException;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {

    /**
     * 统一 异常配置
     */
    @RestControllerAdvice
    static class UnifiedExceptionHandler{

        @ExceptionHandler(BusinessException.class)
        public CommonResult<Void> handleBusinessException(BusinessException be){
            return CommonResult.errorResult(be.getErrorCode(), be.getErrorMsg());
        }
    }


    /**
     * 返回值 @ResponseBody 统一配置
     */
    @RestControllerAdvice
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {

        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                      ServerHttpRequest request, ServerHttpResponse response) {

            if(body instanceof CommonResult) {
                return body;
            }
            return new CommonResult<Object>(body);
        }
    }
}
