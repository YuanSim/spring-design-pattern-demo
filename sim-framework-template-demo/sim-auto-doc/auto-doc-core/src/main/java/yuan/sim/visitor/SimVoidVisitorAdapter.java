package yuan.sim.visitor;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.util.CollectionUtils;
import yuan.sim.config.AutoDocConfig;
import yuan.sim.parse.SimClassParser;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.reflect.Field;
import yuan.sim.vo.reflect.Method;
import yuan.sim.vo.reflect.MethodParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/30
 */
public class SimVoidVisitorAdapter<A> extends VoidVisitorAdapter<A> {

    private SimClass cls;

    private HashMap<String,SimClass> simClassHashMap;

    private AutoDocConfig autoDocConfig;

    public SimVoidVisitorAdapter(SimClass simClass,HashMap<String,SimClass> simClassHashMap,AutoDocConfig autoDocConfig) {
        this.cls = simClass;
        this.simClassHashMap = simClassHashMap;
        this.autoDocConfig = autoDocConfig;
    }
    /**
     * 解析包名
     * @param n
     * @param arg
     */
    @Override
    public void visit(final PackageDeclaration n, final A arg) {

        cls.setPackageName(n.getNameAsString());
    }

    /**
     * 解析类
     * @param n
     * @param arg
     */
    @Override
    public void visit(final ClassOrInterfaceDeclaration n, final A arg) {

        Optional<Node> parentNode = n.getParentNode();
        if(!parentNode.isPresent()) {
            super.visit(n, arg);
            return;
        }

        Node node = parentNode.get();

        boolean isInstanceof = node instanceof ClassOrInterfaceDeclaration;

        if(isInstanceof) {
            ClassOrInterfaceDeclaration parentNodeClass = (ClassOrInterfaceDeclaration) node;

            SimClass innerCls = new SimClass();
            innerCls.setName(n.getNameAsString());
            innerCls.setComment(SimClassParser.parseComment(n));
            innerCls.setAnnotations(SimClassParser.parseAnnotation(n,innerCls));
            innerCls.setModifier(SimClassParser.parseModifiers(n));
            innerCls.setConfig(autoDocConfig);
            innerCls.setInterface(n.isInterface());

            SimClass parentClass = simClassHashMap.get(parentNodeClass.getNameAsString());
            innerCls.setPackageName(parentClass.getPackageName());
            innerCls.setParent(parentClass);


            List<SimClass> innerClassList =  parentClass.getInnerClass();
            if(CollectionUtils.isEmpty(innerClassList)) {
                parentClass.setInnerClass(new ArrayList<>());
            }

            innerClassList.add(innerCls);
            simClassHashMap.put(n.getNameAsString(), innerCls);
        }

        if(!isInstanceof) {

            //最外层的cls
            cls.setName(n.getNameAsString());
            cls.setComment(SimClassParser.parseComment(n));
            cls.setAnnotations(SimClassParser.parseAnnotation(n, cls));
            cls.setInterface(n.isInterface());

            Optional<Node> optionalNode = n.getParentNode();
            if(optionalNode.isPresent() && optionalNode.get() instanceof CompilationUnit) {
                CompilationUnit compilationUnit = (CompilationUnit) n.getParentNode().get();
                List<String> imports = compilationUnit.getImports().stream().map(ImportDeclaration::getNameAsString).collect(Collectors.toList());
                cls.setImports(imports);
            }

            cls.setModifier(SimClassParser.parseModifiers(n));
            cls.setConfig(autoDocConfig);
            simClassHashMap.put(n.getNameAsString(), cls);
        }

        super.visit(n, arg);
    }


    /**
     * 解析属性
     * @param n
     * @param arg
     */
    @Override
    public void visit(final FieldDeclaration n, final A arg) {

        NodeList<VariableDeclarator> nodeList =  n.getVariables();
        Optional<Node> parentNode = n.getParentNode();
        if(CollectionUtils.isEmpty(nodeList) || !parentNode.isPresent()
        || !(parentNode.get() instanceof ClassOrInterfaceDeclaration) ){
            super.visit(n, arg);
            return;
        }

        ClassOrInterfaceDeclaration parentCls = (ClassOrInterfaceDeclaration) n.getParentNode().get();
        SimClass fieldClass = simClassHashMap.get(parentCls.getNameAsString());

        Field field = new Field();
        VariableDeclarator variableDeclarator = nodeList.get(0);
        field.setName(variableDeclarator.getNameAsString());
        field.setType(variableDeclarator.getType().asString());
        field.setComment(SimClassParser.parseComment(n));
        field.setAnnotations(SimClassParser.parseAnnotation(n, field));
        field.setModifier(SimClassParser.parseModifiers(n));
        field.setParent(fieldClass);
        field.setConfig(autoDocConfig);


        List<Field> fields = fieldClass.getFields();
        if(CollectionUtils.isEmpty(fields)){
            fieldClass.setFields(new ArrayList());
        }

        fields.add(field);
        super.visit(n, arg);
    }


    /**
     * 解析函数体
     * @param n
     * @param arg
     */
    @Override
    public void visit(final MethodDeclaration n, final A arg) {

        Optional<Node> parentNode = n.getParentNode();
        if(!parentNode.isPresent() || !(parentNode.get() instanceof ClassOrInterfaceDeclaration)) {
            super.visit(n, arg);
            return;
        }

        ClassOrInterfaceDeclaration parentCls = (ClassOrInterfaceDeclaration) n.getParentNode().get();
        SimClass fieldClass = simClassHashMap.get(parentCls.getNameAsString());

        Method method = new Method();
        method.setName(n.getNameAsString());
        method.setType(n.getTypeAsString());
        method.setComment(SimClassParser.parseComment(n));
        method.setAnnotations(SimClassParser.parseAnnotation(n, method));
        method.setModifier(SimClassParser.parseModifiers(n));
        method.setParent(fieldClass);

        NodeList<Parameter> nodeList = n.getParameters();
        if(!CollectionUtils.isEmpty(nodeList)) {
            List<MethodParam> methodParamList = new ArrayList<MethodParam>();
            nodeList.forEach(node-> {
                MethodParam methodParam = new MethodParam();
                methodParam.setType(node.getTypeAsString());
                methodParam.setName(node.getNameAsString());
                methodParam.setAnnotations(SimClassParser.parseAnnotation(node, methodParam));
                methodParam.setParent(method);
                methodParamList.add(methodParam);
            });
            method.setMethodParams(methodParamList);
        }

        List<Method> methods = fieldClass.getMethods();
        if(methods == null){
            fieldClass.setMethods(new ArrayList());
        }
        methods.add(method);

        super.visit(n, arg);
    }
}
