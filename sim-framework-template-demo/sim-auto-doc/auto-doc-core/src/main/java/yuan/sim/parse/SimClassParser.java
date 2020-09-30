package yuan.sim.parse;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import org.springframework.util.CollectionUtils;
import yuan.sim.config.AutoDocConfig;
import yuan.sim.visitor.SimVoidVisitorAdapter;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.reflect.Annotation;
import yuan.sim.vo.reflect.Comment;
import yuan.sim.vo.reflect.Structure;
import yuan.sim.vo.reflect.annotation.MarkerAnnotation;
import yuan.sim.vo.reflect.annotation.NormalAnnotation;
import yuan.sim.vo.reflect.annotation.SingleAnnotation;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SimClassParser {

    private static ConcurrentHashMap<AutoDocConfig, ConcurrentHashMap<String, Class>> classCache = new ConcurrentHashMap<>();

    private static final SimClass cls = new SimClass();

    private static final HashMap<String,SimClass> simClassHashMap = new HashMap<>();

    private AutoDocConfig autoDocConfig;

    public SimClassParser(AutoDocConfig autoDocConfig) {
        this.autoDocConfig = autoDocConfig;
    }

    public static SimClass autoParse(Structure structure) {
        return autoParse(structure.getType(), structure);
    }

    public static SimClass autoParse(String type, Structure structure) {

        return null;

    }

    public SimClass parse(InputStream inputStream) {

        CompilationUnit compilationUnit = JavaParser.parse(inputStream, this.autoDocConfig.getEncoding());

        SimVoidVisitorAdapter simVoidVisitorAdapter = new SimVoidVisitorAdapter(cls, simClassHashMap, autoDocConfig);

        compilationUnit.accept(simVoidVisitorAdapter,null);

        return cls;
    }

    /**
     * 修饰语解析
     * @param nodeWithModifiers
     * @return
     */
    public static List<String> parseModifiers(NodeWithModifiers<? extends Node> nodeWithModifiers) {

        EnumSet<Modifier> enumSet = nodeWithModifiers.getModifiers();

        if(enumSet == null || enumSet.isEmpty()) {
            return null;
        }

        List<String> modifiers = enumSet.stream().map(Modifier::asString).collect(Collectors.toList());
        return modifiers;
    }
    /**
     * 解析注解
     * @param nodeWithAnnotations
     * @param innerCls
     */
    public static List<Annotation> parseAnnotation(NodeWithAnnotations<? extends Node> nodeWithAnnotations, Structure innerCls) {

        NodeList<AnnotationExpr> annotationExprs = nodeWithAnnotations.getAnnotations();
        if(CollectionUtils.isEmpty(annotationExprs)) {
            return null;
        }

        List<Annotation> annotations = new ArrayList();
        annotationExprs.forEach(annotationExpr -> {

            Annotation annotation = getAnnotationForExpr(annotationExpr,innerCls);
            if(null != annotation) {
                annotations.add(annotation);
            }
        });

        return annotations;
    }

    /**
     * 解析注解
     * @param annotationExpr
     */
    private static Annotation getAnnotationForExpr(AnnotationExpr annotationExpr,Structure innerCls) {


        if (annotationExpr instanceof MarkerAnnotationExpr) {
            MarkerAnnotation annotation = new MarkerAnnotation();
            setBaseAnnotationMessage(annotation,annotationExpr,innerCls);
            return annotation;
        }

        if(annotationExpr instanceof SingleMemberAnnotationExpr){

            SingleMemberAnnotationExpr singleMemberAnnotationExpr = (SingleMemberAnnotationExpr) annotationExpr;
            SingleAnnotation annotation = new SingleAnnotation();
            annotation.setValue(annotationValueTrim(singleMemberAnnotationExpr.getMemberValue().toString()));
            setBaseAnnotationMessage(annotation,annotationExpr,innerCls);
            return annotation;
        }

        if(annotationExpr instanceof NormalAnnotationExpr){
            NormalAnnotationExpr normalAnnotationExpr = (NormalAnnotationExpr) annotationExpr;

            NodeList<MemberValuePair> pairs = normalAnnotationExpr.getPairs();
            if( CollectionUtils.isEmpty(pairs)){
                return null;
            }

            List<NormalAnnotation.Pair> pairList = new ArrayList<NormalAnnotation.Pair>();
            pairList.forEach(pair -> {
                NormalAnnotation.Pair build = NormalAnnotation.Pair.builder().name(pair.getName()).value(pair.getValue()).build();
                pairList.add(build);
            });
            NormalAnnotation annotation = new NormalAnnotation();
            annotation.setPairList(pairList);
            setBaseAnnotationMessage(annotation,annotationExpr,innerCls);
            return annotation;
        }

        return null;

    }

    /**
     * 设置注解基础信息
     * @param annotation
     * @param annotationExpr
     * @param innerCls
     */
    private static void setBaseAnnotationMessage(Annotation annotation, AnnotationExpr annotationExpr, Structure innerCls) {
        if (annotation != null) {
            annotation.setName(annotationExpr.getNameAsString());
            annotation.setStructure(innerCls);
        }
    }

    private static String annotationValueTrim(String val){
        if(val.startsWith("\"") && val.endsWith("\"")){
            return val.substring(1, val.length() - 1);
        }

        return val;
    }

    /**
     * 解析注释
     * @param node
     * @return
     */
    public static Comment parseComment(com.github.javaparser.ast.Node node) {

        Optional<com.github.javaparser.ast.comments.Comment> comment = node.getComment();

        if(!comment.isPresent()) {
            return null;
        }

        Javadoc javadoc = JavaParser.parseJavadoc(comment.get().getContent());

        Comment simComment = new Comment();
        simComment.setDescription(javadoc.getDescription().toText());

        List<JavadocBlockTag> javadocBlockTags = javadoc.getBlockTags();
        if(CollectionUtils.isEmpty(javadocBlockTags)){
            return simComment;
        }

        List<Comment.Tag> tags = new ArrayList<Comment.Tag>();
        javadocBlockTags.forEach(javadocBlockTag -> {
            Comment.Tag build = Comment.Tag.builder().tagName(javadocBlockTag.getTagName())
                    .content(javadocBlockTag.getContent().toText())
                    .name(javadocBlockTag.getName().orElse(null)).build();
            tags.add(build);

        });

        simComment.setTags(tags);

        return simComment;
    }
}
