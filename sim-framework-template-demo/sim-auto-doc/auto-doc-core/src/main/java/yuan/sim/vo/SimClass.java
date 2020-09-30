package yuan.sim.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import yuan.sim.vo.reflect.Field;
import yuan.sim.vo.reflect.Method;
import yuan.sim.vo.reflect.Structure;

import java.util.List;

/**
 * 存储解析java文件之后的数据
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimClass extends Structure {

    /**
     * 属性值
     */
    private List<Field> fields;

    /**
     * 函数
     */
    private List<Method> methods;

    /**
     * 内部类
     */
    private List<SimClass> innerClass;

    /**
     * 是否是接口
     */
    private boolean isInterface;
}
