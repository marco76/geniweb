package ch.genidea.geniweb.base.service;

import ch.genidea.geniweb.base.domain.SecurityCode;
import ch.genidea.geniweb.base.domain.User;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    private static String CONFIRMATION_TEMPLATE = "confirm_account.vm";
    @Value("#{appProperties['mail.from']}")
    private String from;

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Async
    public void sendAuthorizationMail(final User user, final SecurityCode securityCode) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setSubject("MyOwnVenture Activation");
                message.setTo(user.getUsername());
                message.setFrom(from);
                Map model = new HashMap();
                model.put("user", user);
                model.put("securityCode", securityCode);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, CONFIRMATION_TEMPLATE, model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
