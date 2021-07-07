package service;

import dominio.Exame;
import dominio.Laudo;

public interface LaudoService {

    Exame realizarExame(Exame exame, Laudo laudo);
    Laudo emitirLaudo(Laudo laudo);

}
