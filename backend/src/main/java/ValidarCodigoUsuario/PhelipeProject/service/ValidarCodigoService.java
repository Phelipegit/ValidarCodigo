package ValidarCodigoUsuario.PhelipeProject.service;

import ValidarCodigoUsuario.PhelipeProject.dto.RequestCodigo;
import ValidarCodigoUsuario.PhelipeProject.dto.ResponseCodigo;
import ValidarCodigoUsuario.PhelipeProject.entity.EntityUser;
import ValidarCodigoUsuario.PhelipeProject.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ValidarCodigoService {

    private final RepositoryUser repositoryUser;

    public ValidarCodigoService(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public ResponseCodigo validarCodigo(RequestCodigo requestCodigo) {
        Optional<EntityUser> exist = repositoryUser.findByEmail(requestCodigo.getEmail());

        EntityUser user = exist.get();

        try {
            if (LocalDateTime.now().isAfter(user.getCreateAt().plusMinutes(2))) {
                return new ResponseCodigo(false, "Código expirado, tente novamente");
            }


            if (!user.getCodigo().equals(requestCodigo.getCodigoUsuario())) {
                return new ResponseCodigo(false, "Código inválido, tente novamente");
            }

            return new ResponseCodigo(true, "Código validado com sucesso, parabéns");
        }finally {
            repositoryUser.deleteById(user.getId());
        }
    }
}
