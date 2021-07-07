package dominio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Laudo {
    Exame resultado;
    String descricao;
    Cliente nome;
    Cliente cpf;
}
