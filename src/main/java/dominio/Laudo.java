package dominio;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Laudo {

    private Cliente cliente;
    private Exame[] exames;
    private LocalDate dataRealizacao;

    public Laudo(Cliente cliente, Exame[] exames) {
        this.cliente = cliente;
        this.exames = exames;
        this.dataRealizacao = LocalDate.now();
    }
}
