package yuansim.service;

import org.springframework.stereotype.Service;
import yuansim.view.exception.BusinessException;

import java.util.List;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
@Service
public class DemoService {

    public List<String> demo() {

        throw new BusinessException("1003", "因为处理异常");
    }
}
