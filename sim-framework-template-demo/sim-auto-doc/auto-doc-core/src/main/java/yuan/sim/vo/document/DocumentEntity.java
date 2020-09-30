package yuan.sim.vo.document;

import lombok.Data;
import yuan.sim.vo.SimClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档模型
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
public class DocumentEntity {

    /**
     *
     */
    private SimClass simClass;

    /**
     *
     */
    private List<DocumentField> fields;

    /**
     * 类型 java接口名称
     */
    private String type;

    /**
     * 完整类型 完整Java类名
     */
    private String fullType;

    /**
     * java注释
     */
    private String desc;

    public DocumentEntity(SimClass simClass) {
        this.simClass = simClass;
        this.type = simClass.getName();
        this.fullType = simClass.getPackageName()+"."+simClass.getName();
        this.desc = simClass.getCommentName();
        this.fields = new ArrayList<>();

    }
}
