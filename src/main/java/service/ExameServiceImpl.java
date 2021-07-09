package service;

import dao.ExameDao;
import dominio.Exame;
import jakarta.inject.Inject;
import service.ExameService;

public class ExameServiceImpl implements ExameService {

    @Inject
    private ExameDao exameDao;

    @Inject
    private LaudoService laudoService;

    @Override
    public void inserir(Exame exameRequest, String cpf)
    {
        //TODO chamar o metodo realizarExame
        laudoService.realizarExame();
        exameDao.criar(exameRequest,cpf);
    }
}
