package service;

import dominio.Exame;
import dominio.ExameCovid;
import dominio.ExameEnum;

import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

import java.util.Random;


@TipoExame(value = ExameEnum.COVID)
public class LaudoCovidServiceImpl implements LaudoService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame() {
        ExameCovid exame = new ExameCovid();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        }
        var random = new Random();
        exame.setResultado(random.nextBoolean());
        System.out.println("Exame de Covid realizado com sucesso.");
        return exame;
    }

    @Override
    public Laudo emitirLaudo() {
        return null;
    }
}
