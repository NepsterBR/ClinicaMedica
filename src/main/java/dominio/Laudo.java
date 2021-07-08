package dominio;

import exceptions.NoClientException;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class Laudo {

    private Cliente cliente;
    private ArrayList<Exame> exames;

    public Laudo(Cliente cliente, ArrayList<Exame> exames) {
        this.cliente = cliente;
        this.exames = exames;
    }

    private Laudo getLaudo(Exame exame) {
        Cliente cliente = new Cliente();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        } else {
            if (cliente.equals(exame.getCliente())) {
                ArrayList<Exame> exames = new ArrayList<Exame>();
                Laudo laudo = new Laudo(cliente, exames);
                exames.add(exame);
                System.out.println("Exame adicionado ao laudo com sucesso.");
                return laudo;
            }
        }
        throw new NoClientException("Cliente não encontrado");
    }

}
