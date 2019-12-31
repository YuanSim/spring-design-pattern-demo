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
public class BaseResource<T> {

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

    public static <T> BaseResource<T> fromError(String message) {
        return fromError(500, message);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResource<T> fromError(int result, String message) {
        BaseResource<T> resource = new BaseResource<>();
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
    public static <T> BaseResource<T> fromSuccess(T body) {
        BaseResource<T> resource = new BaseResource<>();
        resource.result = 200;
        resource.body = body;
        return resource;
    }
}
