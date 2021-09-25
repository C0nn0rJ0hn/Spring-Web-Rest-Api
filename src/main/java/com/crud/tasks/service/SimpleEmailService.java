package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    private final MailCreatorService mailCreatorService;

    public void send(final Mail mail, final String emailTemplate) {
        log.info("Starting email preparation...");
        try {
            if (emailTemplate == null || emailTemplate.equals("")) {
                javaMailSender.send(createMailMessage(mail));
            }
            else {
                javaMailSender.send(createMimeMessage(mail, emailTemplate));
            }
            log.info("Email has been sent");
        }
        catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, final String emailTemplate) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildEmailUsingTemplate(mail.getMessage(), emailTemplate), true);
        };
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        Optional.ofNullable(mail.getToCc()).ifPresent(mailMessage::setCc);

        return mailMessage;
    }
}
