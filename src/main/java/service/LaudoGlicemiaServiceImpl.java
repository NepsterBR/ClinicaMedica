package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.*;
import exceptions.NoClientException;
import jakarta.inject.Inject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;


@TipoExame(value = ExameEnum.GLICEMIA)
public class LaudoGlicemiaServiceImpl implements LaudoService {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    /** Valores de referência de glicemia em jejum: // LEVAR PARA O SERVLET
     *  hipoglicemia: valor < 70 mg/dL
     *  normal: valor < 99 mg/dL
     *  alterada: 100 mg/dL < valor < 125 mg/dL
     *  diabetes: 126 mg/dL <= valor
     * @param cpf
     */
    @Override
    public Exame realizarExame(String cpf) throws IOException {

        ExameGlicemia exame = new ExameGlicemia();
        exame.setCliente(clienteDao.findByCpf(cpf));
        var random = new Random();
            exame.setNomeExame("Exame de Glicemia");
            exame.setIdExame("003");
            exame.setParametros("Exame de sangue para a medição de glicose.");
            exame.setDataRealizacao(LocalDate.now());
            exame.setResultado(String.valueOf(random.nextFloat() * getRandomNumber(100, 600)));
            laudoDao.criar(exame);
            return exame;
        }

    public int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
