package yuansim.service;

import yuansim.entity.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static yuansim.utils.SmsUtils.*;

/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
@Slf4j
@Service
public class EmailService {

    /**
     *
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     *
     */
    @Autowired
    private JavaMailSender sender;

    /**
     *
     */
    @Value("${spring.mail.username}")
    private String from;


    public String sendVerification(MailRequest mailRequest) {

        // 生成验证码
        String code = generateVerification(NUMBER_SEED, VERIFICATION_LENGTH);
        mailRequest.setContent(code);
        return send(mailRequest);
    }

    public String send(MailRequest mailRequest) {

        Context context = new Context();
        context.setVariable("username", mailRequest.getReceiverUsername());
        context.setVariable("content", mailRequest.getContent());
        context.setVariable("operation", mailRequest.getOperation());

        String content = templateEngine.process(mailRequest.getMailTemplate().getTemplatePath(),  context);

        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom("SimUU" + "<" + from + ">");
            message.setSubject(mailRequest.getSubject());
            message.setTo(mailRequest.getReceiverMail());
            message.setText(content, true);
            sender.send(mimeMessage);
        } catch (MessagingException e) {

            return "邮件发送失败";
        }
        return "邮件发送成功";
    }
}
