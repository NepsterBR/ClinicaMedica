package service;

import dao.ExameDao;
import dominio.Exame;
import dominio.ExameEnum;
import factory.ExameViewFactory;
import jakarta.inject.Inject;

public class ExameServiceImpl implements ExameService {

    @Inject
    private ExameDao exameDao;

    @Inject
    private LaudoService laudoService;

    @Inject
    private ExameViewFactory exameViewFactory;


    @Override
    public void inserir(Exame exameRequest, String cpf, ExameEnum exameEnum)
    {
        //TODO chamar o metodo realizarExame
        LaudoService laudoService = exameViewFactory.realizarExame(exameEnum);
        laudoService.realizarExame(cpf);

        exameDao.criar(exameRequest,cpf);
    }
}
