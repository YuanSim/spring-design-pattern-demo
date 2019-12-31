package yuansim.view.exception;

import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Data
public class DataException extends RuntimeException{

    private String name;

    public DataException() {
        this.name = "数据不存在";
    }

    public DataException(String name) {
        this.name = name;
    }

    public DataException(String name, String message) {
        super(message);
        this.name = name;
    }

    public DataException(String name, String message, Throwable cause) {
        super(message, cause);
        this.name = name;
    }

    public DataException(String name, Throwable cause) {
        super(cause);
        this.name = name;
    }

    public DataException(String name, String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.name = name;
    }
}
