package dao;

import dominio.Cliente;

import java.io.IOException;
import java.util.List;

public interface ClienteDao {

    Cliente criar(Cliente cliente);

    List<Cliente> getAll() throws IOException;

}
