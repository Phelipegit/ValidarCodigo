package ValidarCodigoUsuario.PhelipeProject.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EnviarEmailService {

    @Value("${MAIL_PASSWORD}")
    private String apiKey;

    public void enviar(String destinatario, String mensagem) {
        SendGrid sg = new SendGrid(apiKey);

        Email from = new Email("phelipegithub@gmail.com");
        Email to = new Email(destinatario);
        Content content = new Content("text/plain", mensagem);
        Mail mail = new Mail(from, "Validação", to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println("Status: " + response.getStatusCode());
        } catch (IOException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}
