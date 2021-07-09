package service;

import dominio.Exame;
import dominio.Laudo;

public interface LaudoService {

    Exame realizarExame(String cpf);
    Laudo emitirLaudo();


}
