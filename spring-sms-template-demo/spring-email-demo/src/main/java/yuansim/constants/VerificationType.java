package yuansim.constants;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
public enum  VerificationType {


    /**
     * 注册
     */
    register("注册账号"),

    /**
     * 忘记密码
     */
    forgetPassword("找回密码"),

    /**
     * 绑定手机号
     */
    mobileBind("绑定手机号"),

    /**
     * 修改手机号
     */
    mobileChange("修改手机号"),

    /**
     * 绑定邮箱
     */
    mailBind("绑定邮箱"),

    /**
     * 修改邮箱
     */
    mailChange("修改邮箱"),

    ;

    private final String typeName;

    VerificationType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 获取验证码redis key, 拼接手机号或邮箱等
     *
     * @return
     */
    public String ofCodeKey() {
        return "verification-code-" + this.toString() + "-%s";
    }

    /**
     * 获取验证码redis key, 拼接手机号或邮箱等
     *
     * @param type
     * @return
     */
    public static String ofCodeKey(String type) {
        return "verification-code-" + valueOf(type).toString() + "-%s";
    }
}
