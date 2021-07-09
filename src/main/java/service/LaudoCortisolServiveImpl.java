package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.*;
import exceptions.NoClientException;

import jakarta.inject.Inject;

import java.io.IOException;
import java.util.Random;

@TipoExame(value = ExameEnum.CORTISOL)
public class LaudoCortisolServiveImpl implements LaudoService {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    // Valores de referência para cortisol plasmático: manhã: 8,7 a 22 ug/dL; tarde: <10 ug/dL
    @Override
    public Exame realizarExame(String cpf) throws IOException {

        ExameCortisol exame = new ExameCortisol();
        exame.setCliente(clienteDao.findByCpf(cpf));
        var random = new Random();
        exame.setNomeExame("Exame de Cortisol");
        exame.setIdExame("004");
        exame.setParametros("Exame de sangue para a medição dos níveis de cortisol.");
        exame.setResultado(random.nextFloat() * getRandomNumber(1, 30));
        laudoDao.criar(exame);
        return exame;
    }


    public int getRandomNumber(int min, int max) {

    return (int) (Math.random() * (max - min)) + min;
    }


}
