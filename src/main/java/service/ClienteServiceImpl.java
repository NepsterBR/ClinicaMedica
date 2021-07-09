package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

public class ClienteServiceImpl implements ClienteService{

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Cliente inserir(Cliente cliente) throws IOException {
        return clienteDao.criar(cliente);
    }

    @Override
    public List<Cliente> listAll() throws IOException {
        return clienteDao.getAll();
    }
}
