package yuan.sim.vo.document;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public interface Document {

    /**
     * 获取接口名称
     * @return
     */
    String getApiName();

    /**
     *
     * @return
     */
    String getNeedLogin();

    /**
     * 获取注释
     * @return
     */
    String getApiDescription();

    /**
     * 获取请求方法
     * @return
     */
    String getRequestMethod();

    /**
     * 获取请求路径
     * @return
     */
    String getRequestPath();

}
