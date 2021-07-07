package service;


import dominio.Exame;
import dominio.ExameEnum;
import dominio.ExameGlicemia;
import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.GLICEMIA)
public class LaudoGlicemiaServiceImpl implements LaudoService {

    @Inject
    private Exame exame;

    @Override
    public Exame realizarExame() {
        Exame exame = new ExameGlicemia();
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
