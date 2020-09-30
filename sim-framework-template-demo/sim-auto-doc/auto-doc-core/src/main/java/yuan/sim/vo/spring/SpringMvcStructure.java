package yuan.sim.vo.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import yuan.sim.constants.RequestDataType;
import yuan.sim.vo.document.Document;
import yuan.sim.vo.reflect.Annotation;
import yuan.sim.vo.reflect.Method;
import yuan.sim.vo.reflect.MethodParam;
import yuan.sim.vo.reflect.Structure;
import yuan.sim.vo.reflect.annotation.NormalAnnotation;
import yuan.sim.vo.reflect.annotation.SingleAnnotation;

import java.util.List;
import java.util.Optional;

import static yuan.sim.constants.ApiAnnotationConstants.*;
import static yuan.sim.constants.RequestDataType.REQUEST_FORM;
import static yuan.sim.constants.RequestDataType.REQUEST_JSON;
import static yuan.sim.constants.RequestDataType.RequestMethodType.JSON_CONTENT_TYPE;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SpringMvcStructure implements Document {

    protected Structure structure;

    private String apiName;

    private String apiDescription;

    private String requestMethod;

    private String requestPath;

    private boolean isPostJson;

    public boolean isPostJson() {
        return isPostJson;
    }


    public SpringMvcStructure(Structure structure) {

        this.structure = structure;
        this.apiName = parseApiName();
        this.apiDescription = parseApiDescription();
        String requestType = parseRequestType();
        if(REQUEST_JSON.equals(requestType)) {
            requestMethod = JSON_CONTENT_TYPE;
            isPostJson = true;
        }else {
            requestMethod = parseRequestMethod();
        }

        requestPath = parseRequestPath();
        //如果名称不存在，则进行兼容，尝试获取请求地址名称，如果没有设置则获取类名或者方法名作为名称
        if(StringUtils.isEmpty(apiName)){

            if(StringUtils.isEmpty(requestPath)) {
                apiName = structure.getName();
                return;
            }
            String[] pathArr = requestPath.split("/");
            apiName = pathArr[pathArr.length - 1];

        }
    }

    /**
     * 解析名称
     * @return
     */
    private String parseApiName() {
        //兼容swagger 优先返回swagger
        Annotation annotation = structure.getAnnotationByName(API_OPERATION_NAME);
        if(annotation != null) {
            if(annotation instanceof NormalAnnotation){
                NormalAnnotation normalAnnotation = (NormalAnnotation)annotation;
                return normalAnnotation.getValue(VALUE_NAME);
            }
        }
        return structure.getCommentName();
    }

    /**
     * 解析描述
     * @return
     */
    private String parseApiDescription() {
        Annotation annotation = structure.getAnnotationByName(API_OPERATION_NAME);
        if(annotation != null) {
            if(annotation instanceof NormalAnnotation){
                NormalAnnotation normalAnnotation = (NormalAnnotation)annotation;
                return normalAnnotation.getValue(VALUE_NAME);
            }
        }
        return structure.getCommentDesc();
    }

    /**
     * 解析请求类型
     * 默认给form
     * @return
     */
    private String parseRequestType() {

        if(!(structure instanceof Method)) {
            return REQUEST_FORM;
        }
        List<MethodParam> params = ((Method)structure).getMethodParams();
        if(CollectionUtils.isEmpty(params)) {
            return REQUEST_FORM;
        }

        Optional<MethodParam> optional = params.stream().filter(param -> param.getAnnotationByName(REQUEST_BODY_NAME) != null).findFirst();
        if(!optional.isPresent()){
            return REQUEST_FORM;
        }

        return REQUEST_JSON;

    }


    /**
     * 解析请求方法
     * @return
     */
    private String parseRequestMethod() {

        Annotation getAnnotation = structure.getAnnotationByName(GET_MAPPING);
        if (getAnnotation != null) {
            return RequestDataType.RequestMethodType.GET;
        }

        Annotation postAnnotation = structure.getAnnotationByName(POST_MAPPING);
        if (postAnnotation != null) {
            return RequestDataType.RequestMethodType.POST;
        }

        Annotation putAnnotation = structure.getAnnotationByName(PUT_MAPPING);
        if (putAnnotation != null) {
            return RequestDataType.RequestMethodType.PUT;
        }

        Annotation deleteMapping = structure.getAnnotationByName(DELETE_MAPPING);
        if (deleteMapping != null) {
            return RequestDataType.RequestMethodType.DELETE;
        }

        Annotation annotation = structure.getAnnotationByName(REQUEST_MAPPING);
        if(annotation != null && annotation instanceof NormalAnnotation){
            return RequestDataType.RequestMethodType.GET + "," + RequestDataType.RequestMethodType.POST;
        }

        NormalAnnotation normalAnnotation = (NormalAnnotation)annotation;
        String method = normalAnnotation.getValue(METHOD_NAME);
        if( StringUtils.isEmpty(method)){
            return RequestDataType.RequestMethodType.GET + "," + RequestDataType.RequestMethodType.POST;
        }

        return  method.replaceAll("(RequestMethod\\.)|\\{|\\}", "").trim();
    }


    /**
     * 解析请求路径
     * @return
     */
    private String parseRequestPath() {

        Annotation annotation = structure.getAnnotationByName(REQUEST_MAPPING);
        if (annotation == null) {
            annotation = structure.getAnnotationByName(GET_MAPPING);
        }

        if (annotation == null) {
            annotation = structure.getAnnotationByName(POST_MAPPING);
        }

        if (annotation == null) {
            annotation = structure.getAnnotationByName(PUT_MAPPING);
        }

        if (annotation == null) {
            annotation = structure.getAnnotationByName(DELETE_MAPPING);
        }

        if(annotation == null) {
            return "";
        }

        String path = "";
        if(annotation instanceof SingleAnnotation) {
            path = ((SingleAnnotation) annotation).getValue();
        }

        if(annotation instanceof NormalAnnotation){
            NormalAnnotation normalAnnotation = (NormalAnnotation)annotation;
            path = normalAnnotation.getValue(VALUE_NAME);
            if(path == null){
                path = normalAnnotation.getValue(PATH_NAME);
            }
            if(path.contains("\"")){
                path = path.substring(1).substring(0, path.length()-1).replace("\"","").trim();
                // TODO: 2020/9/30 多个请求路径
                if(path.contains(",")){
                    //说明有多个
                    path = "["+path+"]";
                }
            }

        }

        if(path.endsWith("/")){
            path =  path.substring(0, path.length()-1);
        }

        return path;
    }


    /**
     * 获取接口名称
     *
     * @return
     */
    @Override
    public String getApiName() {
        return apiName;
    }

    /**
     * @return
     */
    @Override
    public String getNeedLogin() {
        return "";
    }

    /**
     * 获取注释
     *
     * @return
     */
    @Override
    public String getApiDescription() {
        return apiDescription;
    }

    /**
     * 获取请求方法
     *
     * @return
     */
    @Override
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 获取请求路径
     *
     * @return
     */
    @Override
    public String getRequestPath() {
        return requestPath;
    }
}
