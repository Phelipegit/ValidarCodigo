package ValidarCodigoUsuario.PhelipeProject.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EnviarEmailService {

    @Value("${MAIL_PASSWORD}")
    private String password;

    public void enviar(String destinatario, String mensagem) {
        String remetente = "sideenvalorant@gmail.com";
        String senha = password;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject("Validação");
            message.setText(mensagem);

            Transport.send(message);
            System.out.println("E-mail enviado!");


        } catch (MessagingException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
