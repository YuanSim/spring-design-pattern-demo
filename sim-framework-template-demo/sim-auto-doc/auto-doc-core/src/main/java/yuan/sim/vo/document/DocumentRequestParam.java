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
public class DocumentRequestParam {

    private String  name;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 文档模型
     */
    private List<DocumentEntity> docs;

    /**
     * 描述
     */
    private String description;

    /**
     * 默认值
     */
    private String  defaultValue;

    /**
     * 参数是否必须
     */
    private boolean isRequired;

}
