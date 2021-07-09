package service;

import dao.ExameDao;
import dominio.Exame;
import dominio.ExameEnum;
import factory.LaudoServiceFactory;
import jakarta.inject.Inject;

import java.io.IOException;

public class ExameServiceImpl implements ExameService {

    @Inject
    private ExameDao exameDao;

//    @Inject
//    private LaudoService laudoService;

    @Inject
    private LaudoServiceFactory laudoServiceFactory;


    @Override
    public void inserir(Exame exameRequest, String cpf, ExameEnum exameEnum) throws IOException {
        //TODO chamar o metodo realizarExame
        LaudoService laudoService = laudoServiceFactory.realizarExame(exameEnum);
        laudoService.realizarExame(cpf);

        exameDao.criar(exameRequest,cpf);
    }
}
