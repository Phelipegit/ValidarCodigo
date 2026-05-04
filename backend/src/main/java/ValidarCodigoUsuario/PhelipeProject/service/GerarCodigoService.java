package ValidarCodigoUsuario.PhelipeProject.service;

import ValidarCodigoUsuario.PhelipeProject.dto.Codigo;
import ValidarCodigoUsuario.PhelipeProject.dto.RequestEmail;
import ValidarCodigoUsuario.PhelipeProject.entity.EntityUser;
import ValidarCodigoUsuario.PhelipeProject.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GerarCodigoService {

    private final RepositoryUser repositoryUser;
    private final EnviarEmailService enviarEmailService;

    public GerarCodigoService(RepositoryUser repositoryUser,EnviarEmailService enviarEmailService) {
        this.repositoryUser = repositoryUser;
        this.enviarEmailService = enviarEmailService;
    }

    public void gerarESalvar(RequestEmail requestEmail) {

        Codigo codigo = new Codigo();

        EntityUser entityUser = new EntityUser(requestEmail.getEmail(),codigo.getCodigo());

        repositoryUser.save(entityUser);

        enviarEmailService.enviar(requestEmail.getEmail(),"Seu código de verificação é " + codigo.getCodigo() + "\n" + "\n" + "\n" + LocalDate.now());
    }
}
