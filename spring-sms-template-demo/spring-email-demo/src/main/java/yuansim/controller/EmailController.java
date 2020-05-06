package yuansim.controller;

import yuansim.constants.MailTemplate;
import yuansim.constants.VerificationType;
import yuansim.entity.MailRequest;
import yuansim.entity.VerificationCodeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yuansim.service.EmailService;


/**
 * @QQ交流群: 648741281
 * @Email: 177300883312@163.com
 * @微信: londu19930418
 * @Author: Simon.Mr
 * @Created 2020/5/6
 */
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮件验证码
     * @param form
     * @return
     */
    @PostMapping(value = "/sys/verification/code-mail/send")
    public String verificationCodeSendMail(@RequestBody VerificationCodeForm form) {

        VerificationType codeType = form.getCodeType();
        MailRequest mailRequest = MailRequest.builder().subject("Sim-UU-" + codeType.getTypeName())
                .receiverMail(form.getMail())
                .mailTemplate(MailTemplate.VERIFICATION_ONE)
                .receiverUsername(form.getMailReceiverUsername())
                .operation(codeType.getTypeName())
                .build();
        return emailService.sendVerification(mailRequest);
    }

}
