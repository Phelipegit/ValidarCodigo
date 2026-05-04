package ValidarCodigoUsuario.PhelipeProject.dto;

import lombok.Getter;

@Getter
public class RequestEmail {

    private String email;

    public RequestEmail(String email) {
        this.email = email;
    }
}
