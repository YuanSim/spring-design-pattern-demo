package yuan.sim.vo.reflect;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yuan.sim.config.AutoDocConfig;
import yuan.sim.constants.ModifierConstants;
import yuan.sim.vo.SimClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
public abstract class Structure {

    /**
     * 基础配置
     */
    protected AutoDocConfig config;

    /**
     * 导入的包
     */
    protected List<String> imports;

    /**
     * 多态之上结构体结构
     */
    protected Structure parent;

    /**
     * 内部类
     */
    protected List<SimClass> innerClass;

    /**
     * 包名
     */
    protected String packageName;

    /**
     * 名称
     */
    protected String name;

    /**
     * 类型
     */
    protected String type;

    /**
     * 注释
     */
    private Comment comment;

    /**
     * 注解
     */
    private List<Annotation> annotations;

    /**
     * 修饰语
     * @see yuan.sim.constants.ModifierConstants
     */
    private List<String> modifier;

    public String getPackageName() {

        return packageName != null ? packageName : ( parent != null ? parent.getPackageName() : null );
    }

    /**
     * 根据注解名称获取注解
     * @param name
     * @return
     */
    public Annotation getAnnotationByName(String name){

        List<Annotation> annotations = getAnnotations();

        if(CollectionUtils.isEmpty(annotations) || StringUtils.isEmpty(name)){
            return null;
        }

        Optional<Annotation> first = annotations.stream().filter(annotation -> annotation.getName().equals(name)).findFirst();

        return first.get();

    }

    /**
     * 根据注解名称计划获取注解
     * @param names
     * @return
     */
    public List<Annotation> getAnnotationByNames(List<String> names){
        List<Annotation> annotations = getAnnotations();
        if(CollectionUtils.isEmpty(annotations) || CollectionUtils.isEmpty(names)){
            return null;
        }

        List<Annotation> newAnnotations = new ArrayList<>();
        names.forEach(name->{
            Optional<Annotation> first = annotations.stream().filter(annotation -> annotation.getName().equals(name)).findFirst();
            if(first.isPresent()) {
                newAnnotations.add(first.get());
            }
        });

        return newAnnotations;
    }

    /**
     * 判断注解集合中是否包含此名称的注解
     * @param names
     * @return
     */
    public boolean contains(List<String> names) {

        List<Annotation> annotations = getAnnotationByNames(names);
        if(CollectionUtils.isEmpty(annotations)) {
            return false;
        }
        return true;
    }

    /**
     * 获取注释中名称
     * @return
     */
    public String getCommentName(){

        Comment comment = getComment();

        if(comment == null || StringUtils.isEmpty(comment.getDescription())){
            return null;
        }

        return comment.getDescription().split("\n")[0];

    }

    /**
     * 获取描述
     * @return
     */
    public  String getCommentDesc() {

        Comment comment = getComment();
        if(comment == null || StringUtils.isEmpty(comment.getDescription())){
            return null;
        }
        String[] strArr = comment.getDescription().split("\n");
        return strArr.length > 1 ? strArr[1].trim() : "";

    }

    /**
     * 修饰符是否是public
     * @return
     */
    public boolean isPublic() {

        return !CollectionUtils.isEmpty(modifier) && modifier.contains(ModifierConstants.PUBLIC_MODIFIER);

    }
}
