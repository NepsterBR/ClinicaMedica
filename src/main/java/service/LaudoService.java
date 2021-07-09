package service;

import dominio.Exame;
import dominio.Laudo;

import java.io.IOException;

public interface LaudoService {

    Exame realizarExame(String cpf) throws IOException;



}
