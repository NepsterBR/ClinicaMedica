package dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exame {
    Paciente nome;
    Paciente cpf;
    String resultado;
}
