package service;

import dominio.Exame;
import dominio.ExameEnum;
import dominio.Laudo;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.CORTISOL)
public class ExameCortisolServiveImpl implements ExameService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame(Exame exame) {
        return null;
    }

    @Override
    public Laudo emitirLaudo(Laudo laudo) {
        return null;
    }
}
