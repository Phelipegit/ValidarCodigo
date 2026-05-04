package ValidarCodigoUsuario.PhelipeProject.controller;

import ValidarCodigoUsuario.PhelipeProject.dto.RequestCodigo;
import ValidarCodigoUsuario.PhelipeProject.dto.RequestEmail;
import ValidarCodigoUsuario.PhelipeProject.dto.ResponseCodigo;
import ValidarCodigoUsuario.PhelipeProject.repository.service.GerarCodigoService;
import ValidarCodigoUsuario.PhelipeProject.repository.service.ValidarCodigoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CodigoController {

    private final GerarCodigoService gerarCodigoService;

    private final ValidarCodigoService validarCodigoService;

    public CodigoController(GerarCodigoService gerarCodigoService, ValidarCodigoService validarCodigoService) {
        this.gerarCodigoService = gerarCodigoService;
        this.validarCodigoService = validarCodigoService;
    }

    @PostMapping("/enviar")
    public void gerarCodigoEnviar(@RequestBody RequestEmail requestEmail) {
        gerarCodigoService.gerarESalvar(requestEmail);
    }

    @PostMapping("/validar")
    public ResponseCodigo validarCodigo(@RequestBody RequestCodigo requestCodigo) {
        return validarCodigoService.validarCodigo(requestCodigo);
    }
}
