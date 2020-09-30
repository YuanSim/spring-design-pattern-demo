package yuan.sim.business;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import yuan.sim.config.AutoDocConfig;
import yuan.sim.exception.DocException;
import yuan.sim.parse.SimClassParser;
import yuan.sim.utils.FileUtil;
import yuan.sim.vo.SimClass;
import yuan.sim.vo.document.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/29
 */
public abstract class AbstractAutoDoc implements BaseAutoDoc{

    protected AutoDocConfig autoDocConfig;

    /**
     * 具体解析方法 根据不同类型进行解析
     * @return
     */
    protected abstract Document parse(SimClass simClass);


    /**
     * 获取java 文件
     */
    protected void parseJavaFile(){

        Stopwatch stopwatch  = Stopwatch.createStarted();

        String sourcePath = autoDocConfig.getSourcePath();
        System.out.println(String.format("【sim-auto-doc】扫描源文件配置 : %s",sourcePath));

        String outPath = autoDocConfig.getOutPath();
        System.out.println(String.format("【sim-auto-doc】api文件输出路径配置 : %s",outPath));

        if(StringUtils.isEmpty(sourcePath)){
            throw new DocException("扫描源文件配置为空");
        }
        if(StringUtils.isEmpty(outPath)){
            throw new DocException("api文件输出路径为空");
        }


        System.out.println(String.format(" 【sim-auto-doc】 开始解析文档"));

        List<String> files = FileUtil.javaFilesForAutoConfig(autoDocConfig);

        System.out.println(String.format(" 【sim-auto-doc】 扫描到接口文件【%s】个", files.size()));


        if(CollectionUtils.isEmpty(files)) {
            throw new DocException("api文件为空");
        }

        System.out.println(String.format(" 【sim-auto-doc】 开始解析 java文件"));

        int size = files.size();

        SimClassParser simClassParser = new SimClassParser(autoDocConfig);
        for(int i = 0 ; i < size; i++) {

            try {

                SimClass simClass = simClassParser.parse(new FileInputStream(files.get(i)));

                parse(simClass);

            } catch (FileNotFoundException e) {

                throw new DocException("file not found :" + e.getMessage());
            }
        }
    }
}
