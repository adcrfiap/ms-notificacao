package br.com.fiap.arremate.msnotificacao.email;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.subject}")
    private String subject;

    @Value("${spring.mail.to}")
    private String to;

    @Value("${spring.mail.from}")
    private String from;

    public void send(String text){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);

        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}