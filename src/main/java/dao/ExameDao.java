package dao;

import dominio.Cliente;
import dominio.Exame;

public interface ExameDao {

    Exame criar(Exame exame, String cpf);
}
