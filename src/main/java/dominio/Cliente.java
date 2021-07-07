package dominio;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cliente {

    private String nome;
    private String cpf;
    private SexoEnum sexo;

}
