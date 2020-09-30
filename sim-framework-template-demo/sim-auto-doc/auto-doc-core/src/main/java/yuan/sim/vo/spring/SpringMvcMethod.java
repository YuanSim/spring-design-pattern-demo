package yuan.sim.vo.spring;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import yuan.sim.parse.SimDocParser;
import yuan.sim.vo.document.DocumentEntity;
import yuan.sim.vo.document.DocumentMethod;
import yuan.sim.vo.document.DocumentRequestParam;
import yuan.sim.vo.reflect.*;

import java.util.List;

import static yuan.sim.constants.ApiAnnotationConstants.*;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SpringMvcMethod extends SpringMvcStructure implements DocumentMethod {

    /**
     * 入参
     */
    private List<DocumentRequestParam> requestParams;

    /**
     * 返回值
     */
    private String returnDesc;

    /**
     * 返回值
     */
    private List<DocumentEntity> returnTypes;

    private List<String> skipMethodParam = Lists.newArrayList(
            HTTP_SERVLET_RESPONSE,
            HTTP_SERVLET_REQUEST,
            MODEL,
            MODEL_MAP,
            MODEL_AND_MAP,
            BINDING_RESULT
    );

    public SpringMvcMethod(Structure structure) {
        super(structure);
        this.requestParams = parseRequestParams();

        this.returnDesc = parseReturnDesc();

        this.returnTypes = parseReturnType();
    }


    /**
     * 解析请求参数
     * @return
     */
    private List<DocumentRequestParam> parseRequestParams() {

        return null;

    }

    /**
     * 解析返回数据描述
     * @return
     */
    private String parseReturnDesc() {

        return null;
    }

    /**
     * 解析返回数据类型
     * @return
     */
    private List<DocumentEntity> parseReturnType() {

        return null;
    }

    /**
     * 获取方法请求参数
     *
     * @return
     */
    @Override
    public List<DocumentRequestParam> getRequestParams() {

        List<MethodParam> params = ((Method)structure).getMethodParams();
        if(CollectionUtils.isEmpty(params)) {
            return null;
        }

        for(MethodParam param : params) {


            // TODO: 2020/9/30 解析未看到ReqeustBody
            Annotation annotation  =  param.getAnnotationByName(REQUEST_PARAM);
            if(annotation == null){
                annotation = param.getAnnotationByName(PATH_VARIABLE);
            }

            Comment.Tag paramTag =  structure.getComment() != null ? structure.getComment().getParamTagByName(structure.getName()) : null;

            if(annotation == null && skipMethodParam(param, paramTag)){
                //跳过 非api参数的解析
                continue;
            }

            DocumentRequestParam requestParam = new DocumentRequestParam();
            requestParam.setType(param.getType());
            requestParam.setDocs(SimDocParser.parseParamType(paramTag, param));
            if(annotation == null) {

            }

        }


        return null;
    }

    /**
     * 获取方法返回值描述
     *
     * @return
     */
    @Override
    public String getReturnDesc() {
        return null;
    }

    /**
     * 获取方法名称
     *
     * @return
     */
    @Override
    public String getMethodName() {
        return null;
    }

    /**
     * 获取完整的类名称
     *
     * @return
     */
    @Override
    public String getFullClsName() {
        return null;
    }

    /**
     * 达成条件 将会跳过，不对其进行解析
     * @param param
     * @param tag
     * @return
     */
    private boolean skipMethodParam(MethodParam param, Comment.Tag tag) {

        // TODO: 2020/9/30 这里可以处理不需要解析的参数
        if(skipMethodParam.contains(param.getType())){
            return true;
        }

        return tag != null && !StringUtils.isEmpty(tag.getContent()) && tag.getContent().contains("#ignore#");
    }


}
