package dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exame {

    private String nomeExame;
    private String idExame;
    private String parametros;
    private Cliente cliente;

}
