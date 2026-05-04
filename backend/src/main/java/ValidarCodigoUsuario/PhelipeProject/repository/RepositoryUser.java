package ValidarCodigoUsuario.PhelipeProject.repository;

import ValidarCodigoUsuario.PhelipeProject.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryUser extends JpaRepository<EntityUser,UUID> {
    Optional<EntityUser> findByEmail(String email);
}
