package yuan.sim.converter;

import org.springframework.util.CollectionUtils;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.document.Document;
import yuan.sim.vo.document.DocumentMethod;
import yuan.sim.vo.spring.SpringMvcMethod;
import yuan.sim.vo.reflect.Annotation;
import yuan.sim.vo.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import static yuan.sim.constants.ApiAnnotationConstants.*;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SpringMvcConverter extends AbstractConverter {
    /**
     * 将java接口文件信息转换为文档
     *
     * @param simClass
     * @return
     */
    @Override
    public Document convert(SimClass simClass) {

        List<DocumentMethod> methodList = new ArrayList();

        if(CollectionUtils.isEmpty(methodList)) {
            return null;
        }

        for(Method method : simClass.getMethods()){

            if(method.isPublic() && isSpringAction(method)){

                methodList.add(new SpringMvcMethod(method));
            }
        }

        return null;
    }

    private static boolean  isSpringAction(Method method){

        List<Annotation> annotations = method.getAnnotations();

        if(CollectionUtils.isEmpty(annotations)) {
            return false;
        }

        for(Annotation annotation:annotations){
            if(annotation.getName().equals(GET_MAPPING) ||
                    annotation.getName().equals(POST_MAPPING) ||
                    annotation.getName().equals(PUT_MAPPING) ||
                    annotation.getName().equals(DELETE_MAPPING) ||
                    annotation.getName().equals(REQUEST_MAPPING)){
                return true;
            }
        }
        return false;
    }
}
