package yuansim.constants;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
public enum  MailTemplate {


    /**
     * 验证码模板1
     */
    VERIFICATION_ONE("mail/mail-verification");

    private final String templatePath;

    MailTemplate(String templatePath) {
        this.templatePath = templatePath;
    }

    /**
     * @return templatePath
     */
    public String getTemplatePath() {
        return templatePath;
    }

}
