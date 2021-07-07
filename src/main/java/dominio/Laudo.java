package dominio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Laudo {
    private Exame exame;
    private String resultado;
    private Cliente cliente;
    private Date data;
}
