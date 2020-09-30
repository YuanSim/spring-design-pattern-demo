package yuan.sim.business;

import yuan.sim.config.AutoDocConfig;
import yuan.sim.converter.Converter;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.document.Document;

/**
 * 基于MVC框架解析文档
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/29
 */
public class SpringMvcAutoDoc extends AbstractAutoDoc{

    private Converter converter;


    public SpringMvcAutoDoc(AutoDocConfig autoDocConfig) {
      super.autoDocConfig = autoDocConfig;
    }


    /**
     * 生成文档
     */
    @Override
    public void generateDoc() {


    }


    /**
     * 具体解析方法 根据不同类型进行解析
     *
     * @param simClass
     * @return
     */
    @Override
    protected Document parse(SimClass simClass) {
        // TODO: 2020/9/30 这里设置过滤

        if(!simClass.contains(autoDocConfig.getScanAnnotationNames())) {
            return null;
        }
        return converter.convert(simClass);
    }
}
