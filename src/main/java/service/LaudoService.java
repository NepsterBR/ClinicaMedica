package service;

import dominio.Exame;
import dominio.ExameCovid;
import dominio.Laudo;

public interface LaudoService {

    Exame realizarExame();
    Laudo emitirLaudo();


}
