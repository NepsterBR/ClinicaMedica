package dominio;

import exceptions.NoClientException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Exame {

    private String nomeExame;
    private String idExame;
    private String parametros;
    private Cliente cliente;
    private LocalDate dataRealizacao;
    private String resultado;

}
