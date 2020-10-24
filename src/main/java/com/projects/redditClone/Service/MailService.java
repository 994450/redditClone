package com.projects.redditClone.Service;


import com.projects.redditClone.Exception.SpringRedditException;
import com.projects.redditClone.model.NoticationEmail;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    void sendMail(NoticationEmail noticationEmail) throws SpringRedditException {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("testgmail.com");
            messageHelper.setTo(noticationEmail.getRecipient());
            messageHelper.setSubject(noticationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(noticationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringRedditException("Exception occurred when sending mail to " + noticationEmail.getRecipient(), e);
        }
    }

}