package dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Cliente {
    String nome;
    String cpf;
    List<Laudo> laudos;
    List<Ficha> fichas;
}
