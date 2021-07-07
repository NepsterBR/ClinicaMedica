package service;

import dao.LaudoDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.UUID;

public class ClienteServiceImpl implements ClienteService{


    @Inject
    private LaudoDao laudoDao;

    @Override
    public Cliente inserir(Cliente cliente) throws IOException {
        cliente.setCpf(UUID.randomUUID().toString());
        return laudoDao.inserirArquivo(cliente);
    }
}
