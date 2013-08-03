package ch.genidea.geniweb;

import ch.genidea.geniweb.base.domain.SecurityCode;
import ch.genidea.geniweb.base.domain.User;
import ch.genidea.geniweb.base.service.MailSenderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/spring-data.xml", "classpath:META-INF/spring/spring-service.xml"})
public class Mail {

    @Autowired
    protected MailSenderService mailSenderService;

    @Before
    public void setup() {

    }

    @Test
    public void simple() throws Exception {
        User user = new User();
        //user.setEmail("mail@mail.com");
        user.setSecurityCode(new SecurityCode());
        user.getSecurityCode().setCode("123");
        mailSenderService.sendAuthorizationMail(user, user.getSecurityCode());
    }
}
