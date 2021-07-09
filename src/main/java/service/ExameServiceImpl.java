package service;

import dao.ExameDao;
import dominio.Exame;
import jakarta.inject.Inject;
import service.ExameService;

public class ExameServiceImpl implements ExameService {

    @Inject
    private ExameDao exameDao;

    @Override
    public void inserir(Exame exameRequest, String cpf) {
        exameDao.criar(exameRequest,cpf);
    }
}
