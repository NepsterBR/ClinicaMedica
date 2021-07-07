package service;

import dominio.Exame;
import dominio.Laudo;

public interface LaudoService {

    Exame realizarExame();
    Laudo emitirLaudo(Laudo laudo);

}
