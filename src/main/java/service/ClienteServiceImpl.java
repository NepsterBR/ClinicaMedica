package service;

import dao.ClienteDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.UUID;

public class ClienteServiceImpl implements ClienteService{


    @Inject
    private ClienteDao clienteDao;

    @Override
    public Cliente inserir(Cliente cliente) throws IOException {
        cliente.setCpf(UUID.randomUUID().toString());
        return clienteDao.inserirArquivo(cliente);
    }
}
