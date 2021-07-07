package service;

import dominio.Exame;
import dominio.Laudo;
import dominio.SexoEnum;

public interface LaudoService {

    Exame realizarExame();
    Laudo emitirLaudo(Laudo laudo);

}
