package yuansim.entity;

import yuansim.constants.VerificationType;
import lombok.Data;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
@Data
public class VerificationCodeForm {


    /**
     * 接收验证码的邮箱
     */
    private String mail;

    /**
     * 系统用户名，可以不传
     */
    private String mailReceiverUsername;

    /**
     * 接收到的验证码, 验证时用
     */
    private String code;


    /**
     * 使用类型: 注册, 忘记密码, 绑定手机号, 修改手机号, 绑定邮箱, 修改邮箱等
     * register, forgetPassword, mobileBind, mobileChange, mailBind, mailChange
     */
    private VerificationType codeType;
}
