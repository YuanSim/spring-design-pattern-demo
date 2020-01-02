package yuansim.view.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/1/2
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{

    private String errorCode;

    private String errorMsg;
}
