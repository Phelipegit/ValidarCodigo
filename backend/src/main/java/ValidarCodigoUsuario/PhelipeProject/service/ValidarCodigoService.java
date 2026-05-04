package ValidarCodigoUsuario.PhelipeProject.service;

import ValidarCodigoUsuario.PhelipeProject.dto.RequestCodigo;
import ValidarCodigoUsuario.PhelipeProject.dto.ResponseCodigo;
import ValidarCodigoUsuario.PhelipeProject.entity.EntityUser;
import ValidarCodigoUsuario.PhelipeProject.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidarCodigoService {

    private final RepositoryUser repositoryUser;

    public ValidarCodigoService(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public ResponseCodigo validarCodigo(RequestCodigo requestCodigo) {
        Optional<EntityUser> exist = repositoryUser.findByEmail(requestCodigo.getEmail());

        if(exist.isEmpty()) {
            return new ResponseCodigo(false, "Email inexistente");
        }

        EntityUser user = exist.get();


        if(!user.getCodigo().equals(requestCodigo.getCodigoUsuario())) {
            repositoryUser.delete(user);
            return new ResponseCodigo(false, "Código inválido, tente novamente");
        }

        repositoryUser.delete(user);
        return new ResponseCodigo(true, "Código validado com sucesso, parabéns");


    }
}
