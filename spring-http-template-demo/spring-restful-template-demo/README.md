

### 统一返回值demo

> 通过实现ResponseBodyAdvice，并重写beforeBodyWrite方法

```aidl
    /**
     * 返回值 responseBody 统一配置
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
```
[配置位置](../../sim-framework-template-demo/sim-framework-template-config/sim-framework-template-restful-config)