package yuan.sim.vo.reflect;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 函数
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Method extends Structure{

    /**
     *
     */
    private List<MethodParam> methodParams;
}
