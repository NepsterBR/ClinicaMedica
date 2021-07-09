package service;

import dominio.Exame;
import dominio.ExameEnum;

public interface ExameService {

    void inserir(Exame exameRequest, String cpf, ExameEnum exameEnum);
}
