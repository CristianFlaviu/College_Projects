package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }
    public void sendNotification(String message,String subject,String Destinatar) throws MailException
    {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(Destinatar);
        mailMessage.setFrom("bancatahaiengi@gmail.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
