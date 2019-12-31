package yuansim.service.exception;

import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Data
public class ServiceParamException extends RuntimeException {

    private String name;

    public ServiceParamException() {
        this.name = "参数验证不通过";
    }

    public ServiceParamException(String name) {
        this.name = name;
    }

    public ServiceParamException(String name, String message) {
        super(message);
        this.name = name;
    }

    public ServiceParamException(String name, String message, Throwable cause) {
        super(message, cause);
        this.name = name;
    }

    public ServiceParamException(String name, Throwable cause) {
        super(cause);
        this.name = name;
    }

    public ServiceParamException(String name, String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.name = name;
    }
}
