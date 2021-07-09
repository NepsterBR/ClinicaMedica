package service;

import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameCovid;
import dominio.ExameEnum;

import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.Random;


@TipoExame(value = ExameEnum.COVID)
public class LaudoCovidServiceImpl implements LaudoService {

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Exame realizarExame(String cpf) {
        ExameCovid exame = new ExameCovid();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        }
        var random = new Random();
        exame.setNomeExame("Teste de Covid");
        exame.setIdExame("002");
        exame.setParametros("Exame qPCR para a detecção de SARS-CoV-2.");
        exame.setDataRealizacao(LocalDate.now());
        exame.setResultado(random.nextBoolean());
        System.out.println("Exame de Covid realizado com sucesso.");
        return exame;
    }

    @Override
    public Laudo emitirLaudo() {
        ExameCovid exame = new ExameCovid();
        return laudoDao.getLaudo(exame);
    }

}
