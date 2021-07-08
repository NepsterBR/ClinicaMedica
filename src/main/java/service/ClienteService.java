package service;

import dominio.Cliente;

import java.io.IOException;
import java.util.List;

public interface ClienteService {
    Cliente inserir(Cliente cliente) throws IOException;

    List<Cliente> listAll() throws IOException;
}
