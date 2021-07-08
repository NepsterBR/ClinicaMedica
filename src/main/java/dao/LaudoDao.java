package dao;

import dominio.Cliente;
import dominio.Exame;
import dominio.Laudo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LaudoDao {

    Cliente inserirArquivo(Cliente cliente) throws IOException;
    List<Cliente> getAll() throws IOException;
    Optional<Cliente> findByCpf(String cpf) throws IOException;
    Laudo getLaudo(Exame exame);

}
