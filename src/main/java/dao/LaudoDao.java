package dao;

import dominio.Cliente;
import dominio.Exame;
import dominio.Laudo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LaudoDao {

    public Exame criar(Exame exame);

}
