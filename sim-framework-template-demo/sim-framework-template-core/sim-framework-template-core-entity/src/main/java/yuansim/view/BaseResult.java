package yuansim.view;

import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2019/12/31
 */
@Data
public class BaseResult<T> {

    /**
     * 响应码
     */
    private int result;

    /**
     * 异常描述
     */
    private String message;

    /**
     *
     */
    private T body;

    public static <T> BaseResult<T> fromError(String message) {
        return fromError(500, message);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> fromError(int result, String message) {
        BaseResult<T> resource = new BaseResult<>();
        resource.result = result;
        resource.message = message;
        return resource;
    }

    /**
     * 默认返回200
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> fromSuccess(T body) {
        BaseResult<T> resource = new BaseResult<>();
        resource.result = 200;
        resource.body = body;
        return resource;
    }
}
