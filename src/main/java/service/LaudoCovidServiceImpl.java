package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameCovid;
import dominio.ExameEnum;

import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import jakarta.inject.Qualifier;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;


@TipoExame(value = ExameEnum.COVID)
public class LaudoCovidServiceImpl implements LaudoService {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Exame realizarExame(String cpf) throws IOException {

        ExameCovid exame = new ExameCovid();
        exame.setCliente(clienteDao.findByCpf(cpf));
        var random = new Random();
        exame.setNomeExame("Teste de Covid");
        exame.setIdExame("002");
        exame.setParametros("Exame qPCR para a detecção de SARS-CoV-2.");
        exame.setDataRealizacao(LocalDate.now());
        exame.setResultado(String.valueOf(random.nextBoolean()));
        laudoDao.criar(exame);
        return exame;
    }





}
