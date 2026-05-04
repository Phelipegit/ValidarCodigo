package ValidarCodigoUsuario.PhelipeProject.dto;

import lombok.Getter;

@Getter
public class RequestCodigo {

    private String email;

    private Integer codigoUsuario;

    public RequestCodigo(String email, Integer codigoUsuario) {
        this.email = email;
        this.codigoUsuario = codigoUsuario;
    }
}
