package yuansim.view.entity;

import lombok.Data;

@Data
public final class CommonResult<T> {

    /**
     * 状态码，标识请求成功与否，如 [1:成功；-1:失败]
     */
    private int status = 1;

    /**
     * 错误码，给出明确错误码，更好的应对业务异常；请求成功该值可为空
     */
    private String errorCode = "";

    /**
     * 错误消息，与错误码相对应，更具体的描述异常信息
     */
    private String errorMsg = "";

    private T resultBody;

    /**
     * 返回结果，通常是 Bean 对象对应的 JSON 数据, 通常为了应对不同返回值类型，将其声明为泛型类型
     */
    public CommonResult() {
    }

    public CommonResult(T resultBody) {
        this.resultBody = resultBody;
    }
}