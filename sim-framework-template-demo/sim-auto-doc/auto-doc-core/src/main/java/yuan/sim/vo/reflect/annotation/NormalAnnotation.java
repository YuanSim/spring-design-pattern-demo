package yuan.sim.vo.reflect.annotation;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import yuan.sim.vo.reflect.Annotation;

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
public class NormalAnnotation extends Annotation {

    private List<Pair> pairList;

    @Data
    @Builder
    public static class Pair{
        private String name;
        private String value;
    }

    public Pair getPair(String name){

        if(CollectionUtils.isEmpty(this.pairList)){
            return null;
        }

        Optional<Pair> first = pairList.stream().filter(pair -> pair.getName().equals(name)).findFirst();
        if(!first.isPresent()) {
            return null;
        }
        return first.get();
    }

    public String getValue(String name){

        Pair pair = getPair(name);

        return pair != null ? pair.getValue() : null;
    }

}
