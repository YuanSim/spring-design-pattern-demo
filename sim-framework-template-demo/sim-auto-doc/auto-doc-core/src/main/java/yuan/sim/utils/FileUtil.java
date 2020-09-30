package yuan.sim.utils;

import org.springframework.util.CollectionUtils;
import yuan.sim.config.AutoDocConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static yuan.sim.constants.DefaultPackageName.PACKAGE_SUFFIX;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/9/29
 */
public class FileUtil {


    /**
     * 获取api接口文件
     * 这里指的是指定包  yuan.sim.config.AutoDocConfig#parsePackageNames 里面.java文件
     * @param autoDocConfig
     * @return
     */
    public static List<String> javaFilesForAutoConfig(AutoDocConfig autoDocConfig) {

        File file = new File(autoDocConfig.getCurrentPath());
        if(!file.exists()) {
            System.out.println("目录不存在");
            return null;
        }

        File[] childFiles = file.listFiles();
        if(childFiles == null || childFiles.length == 0) {
            System.out.println("目录下不存在文件");
            return null;
        }

        List<String> files = new ArrayList<String>();
        for(File childFile : childFiles){

            // 如果他是目录 继续解析
            if(childFile.isDirectory()) {

                autoDocConfig.setCurrentPath(childFile.getPath());

                List<String> subFiles = javaFilesForAutoConfig(autoDocConfig);
                if(!CollectionUtils.isEmpty(subFiles)){
                    files.addAll(subFiles);
                }
                continue;
            }

            if(!childFile.isDirectory()){

                String childFilePath = childFile.getPath();
                // 获取java文件
                if(!childFilePath.endsWith(PACKAGE_SUFFIX)) {
                    continue;
                }
                files.add(childFilePath);
            }
        }

        return files;

    }



}
