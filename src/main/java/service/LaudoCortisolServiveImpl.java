package service;

import dominio.Exame;
import dominio.ExameCortisol;
import dominio.ExameEnum;
import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.CORTISOL)
public class LaudoCortisolServiveImpl implements LaudoService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame() {
        Exame exame = new ExameCortisol();
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
