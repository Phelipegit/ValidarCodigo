package ValidarCodigoUsuario.PhelipeProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class EntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    private String email;

    @NotNull
    private Integer codigo;

    private LocalDateTime createAt;


    public EntityUser(String email, Integer codigo) {
        this.email = email;
        this.codigo = codigo;
        this.createAt = LocalDateTime.now();
    }


    public EntityUser() {

    }
}
