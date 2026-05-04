package ValidarCodigoUsuario.PhelipeProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class EntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    private Integer codigo;


    public EntityUser(String email, Integer codigo) {
        this.email = email;
        this.codigo = codigo;
    }


    public EntityUser() {

    }
}
