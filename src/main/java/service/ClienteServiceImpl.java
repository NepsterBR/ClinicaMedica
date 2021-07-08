package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ClienteServiceImpl implements ClienteService {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Cliente inserir(Cliente cliente) throws IOException {
        cliente.setCpf(UUID.randomUUID().toString());
        return laudoDao.inserirArquivo(cliente);
    }

    @Override
    public List<Cliente> listAll() throws IOException {
        return clienteDao.getAll();
    }

}
