package ValidarCodigoUsuario.PhelipeProject.dto;

import lombok.Getter;

@Getter
public class ResponseCodigo {

    private boolean success;

    private String message;

    public ResponseCodigo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
