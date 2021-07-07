package service;

import dominio.Exame;
import dominio.ExameEnum;
import dominio.Laudo;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.CORTISOL)
public class LaudoCortisolServiveImpl implements LaudoService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame(Exame exame, Laudo laudo) {
        return null;
    }

    @Override
    public Laudo emitirLaudo(Laudo laudo) {
        return null;
    }
}
