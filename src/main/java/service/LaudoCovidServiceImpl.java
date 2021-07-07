package service;

import dominio.Exame;
import dominio.ExameCovid;
import dominio.ExameEnum;

import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.COVID)
public class LaudoCovidServiceImpl implements LaudoService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame() {
        Exame exame = new ExameCovid();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        }

        return null;
    }

    @Override
    public Laudo emitirLaudo(Laudo laudo) {
        return null;
    }
}
