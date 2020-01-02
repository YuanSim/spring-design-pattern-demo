package yuansim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import yuansim.service.DemoService;
import yuansim.view.exception.BusinessException;

import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * 测试非自定义异常
     */
    @GetMapping("/demo/number/format/exception")
    public List<String> demo() {

        throw new NumberFormatException();
    }

    /**
     * 测试service 抛出异常 并查看 @ResponseBody的情况
     * @param id
     * @return
     */
    @GetMapping("/service/{id}")
    @ResponseBody
    public List<String> findUsers(@PathVariable Long id){

        return demoService.demo();
    }

    /**
     * 测试controller 抛出异常
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public List<String> getUserById(@PathVariable Long id){
        throw new BusinessException("1001", "根据ID查询用户异常");
    }
}
