package yuansim.entity;

import yuansim.constants.MailTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 接收人邮箱
     */
    private String receiverMail;

    /**
     * 邮件模板
     */
    private MailTemplate mailTemplate;

    /**
     * 模板参数, 非必填: 接收人名称
     */
    private String receiverUsername;

    /**
     * 模板参数, 非必填: 操作名称, 如: 密码找回, 绑定邮箱等
     */
    private String operation;

    /**
     * 模板参数, 非必填: 邮件主要内容
     */
    private String content;
}
