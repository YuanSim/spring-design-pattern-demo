package yuan.sim.vo.reflect;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 注释
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
@Data
public class Comment {

    private String description;

    private List<Tag> tags;

    public Tag getParamTagByName(String paramName) {

        if(CollectionUtils.isEmpty(tags)){
            return null;
        }

        for(Comment.Tag tag : tags){

            if(tag.getName() != null && tag.getName().equals(paramName)){
                return tag;
            }
        }

        return null;
    }

    @Data
    @Builder
    @NoArgsConstructor
    public static class Tag {

        private  String tagName;

        private String name;

        private String content;

        private Map<String, String> metaData;

    }
}
