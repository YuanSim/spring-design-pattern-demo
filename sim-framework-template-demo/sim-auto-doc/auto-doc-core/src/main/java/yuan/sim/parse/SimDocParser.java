package yuan.sim.parse;

import org.springframework.util.CollectionUtils;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.document.DocumentEntity;
import yuan.sim.vo.reflect.Comment;
import yuan.sim.vo.reflect.Structure;

import java.util.ArrayList;
import java.util.List;

import static yuan.sim.constants.ApiAnnotationConstants.TYPE_NAME;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SimDocParser {

    /**
     * 解析参数类型
     * @return
     */
    public static List<DocumentEntity> parseParamType(Comment.Tag tag, Structure structure) {

        List<DocumentEntity> entities = new ArrayList();
        if(tag == null) {
            SimClass paramClass = SimClassParser.autoParse(structure);
            if(paramClass != null){
                entities.add(new DocumentEntity(paramClass));
            }

            return entities;
        }

        if(CollectionUtils.isEmpty(tag.getMetaData()) || !tag.getMetaData().containsKey(TYPE_NAME)) {
            return entities;
        }
        String[] paramTypes = tag.getMetaData().get(TYPE_NAME).split(",");

        for(String paramType : paramTypes){

            SimClass paramClass = SimClassParser.autoParse(paramType.trim(), structure);

            if(paramClass != null){

               entities.add(new DocumentEntity(paramClass));
            }
        }

        return entities;

    }
}
