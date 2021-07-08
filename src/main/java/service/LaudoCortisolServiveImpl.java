package service;

import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameCortisol;
import dominio.ExameEnum;
import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

import java.util.Random;

@TipoExame(value = ExameEnum.CORTISOL)
public class LaudoCortisolServiveImpl implements LaudoService {

    @Inject
    private LaudoDao laudoDao;

    // Valores de referência para cortisol plasmático: manhã: 8,7 a 22 ug/dL; tarde: <10 ug/dL
    @Override
    public Exame realizarExame() {
        ExameCortisol exame = new ExameCortisol();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        } else {
            var random = new Random();
            exame.setNomeExame("Exame de Cortisol");
            exame.setIdExame("004");
            exame.setParametros("Exame de sangue para a medição dos níveis de cortisol.");
            exame.setResultado(random.nextFloat() * getRandomNumber(1, 30));
            return exame;
        }
    }

    @Override
    public Laudo emitirLaudo() {
        ExameCortisol exame = new ExameCortisol();
        return laudoDao.getLaudo(exame);
    }

    public int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }


}
