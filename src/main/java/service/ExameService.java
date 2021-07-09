package service;

import dominio.Exame;
import dominio.ExameEnum;

import java.io.IOException;

public interface ExameService {

    void inserir(Exame exameRequest, String cpf, ExameEnum exameEnum) throws IOException;
}
