package dao;

import dominio.Cliente;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ClienteDao {


    Cliente criar(Cliente usuario);

    public Cliente findByCpf(String cpf) throws IOException;

    Cliente inserirNoArquivo(Cliente cliente) throws IOException;
}
