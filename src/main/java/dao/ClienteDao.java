package dao;

import dominio.Cliente;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ClienteDao {


    Cliente criar(Cliente usuario);

    List<Cliente> getAll() throws IOException;

    Cliente alterarArquivo(Cliente cliente, String identificador) throws IOException;

    Optional<Cliente> findByCpf(String cpf) throws IOException;

    Cliente inserirNoArquivo(Cliente cliente) throws IOException;
}
