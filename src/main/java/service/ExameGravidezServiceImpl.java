package service;

import dominio.Exame;
import dominio.ExameEnum;
import dominio.ExameGravidez;
import dominio.Laudo;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import view.TipoExame;

@TipoExame(value = ExameEnum.GRAVIDEZ)
public class ExameGravidezServiceImpl implements ExameService {

    @Inject
    //private Exame exame;
    private Laudo laudo;

    @Override
    public Exame realizarExame(Exame exame) {
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        }
        System.out.println("Exame de gravidez realizado com sucesso");
        return exame;
    }

    @Override
    public Laudo emitirLaudo(Laudo laudo) {
        Exame exame = new ExameGravidez();
        // TODO implement rules
        //((ExameGravidez) exame).isResultado()
        return laudo;
    }
}
