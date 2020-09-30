package yuan.sim.vo.document;

import lombok.Data;

import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
public class DocumentField {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段描述
     */
    private String desc;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 对象类型的参数文档模型
     */
    private List<DocumentEntity> docs;

    /**
     * 是否必须
     */
    private boolean require;

    /**
     * 默认值
     */
    private String defaultValue;
}
