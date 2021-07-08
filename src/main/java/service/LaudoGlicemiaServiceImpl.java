package service;

import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameEnum;
import dominio.ExameGlicemia;
import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

import java.util.Random;

@TipoExame(value = ExameEnum.GLICEMIA)
public class LaudoGlicemiaServiceImpl implements LaudoService {

    @Inject
    private LaudoDao laudoDao;

    /** Valores de referência de glicemia em jejum: // LEVAR PARA O SERVLET
     *  hipoglicemia: valor < 70 mg/dL
     *  normal: valor < 99 mg/dL
     *  alterada: 100 mg/dL < valor < 125 mg/dL
     *  diabetes: 126 mg/dL <= valor
     */
    @Override
    public Exame realizarExame() {
        ExameGlicemia exame = new ExameGlicemia();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        } else {
            var random = new Random();
            exame.setNomeExame("Exame de Glicemia");
            exame.setIdExame("003");
            exame.setParametros("Exame de sangue para a medição de glicose.");
            exame.setResultado(random.nextFloat() * getRandomNumber(100, 600));
            return exame;
        }
    }

    @Override
    public Laudo emitirLaudo() {
        ExameGlicemia exame = new ExameGlicemia();
        return laudoDao.getLaudo(exame);
    }

    public int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
