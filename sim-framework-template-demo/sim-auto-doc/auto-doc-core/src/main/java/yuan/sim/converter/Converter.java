package yuan.sim.converter;

import yuan.sim.vo.SimClass;
import yuan.sim.vo.document.Document;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public interface Converter {

    /**
     * 将java接口文件信息转换为文档
     * @param simClass
     * @return
     */
    Document convert(SimClass simClass);
}
