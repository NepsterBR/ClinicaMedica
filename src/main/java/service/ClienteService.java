package service;

import dominio.Cliente;

import java.io.IOException;

public interface ClienteService {
    Cliente inserir(Cliente cliente) throws IOException;
}
