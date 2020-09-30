package yuan.sim.vo.document;

import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public interface DocumentMethod extends Document {

    /**
     * 获取方法请求参数
     * @return
     */
    List<DocumentRequestParam> getRequestParams();

    /**
     * 获取方法返回值描述
     * @return
     */
    String getReturnDesc();

    /**
     * 获取方法名称
     * @return
     */
    String getMethodName();

    /**
     * 获取完整的类名称
     * @return
     */
    String getFullClsName();
}
