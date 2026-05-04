package ValidarCodigoUsuario.PhelipeProject.dto;

public class Codigo {

    private Integer codigo;

    public Codigo() {
        this.codigo = (int) (Math.random() * 40000 + 2000);
    }

    public Integer getCodigo() {
        return this.codigo;
    }
}
