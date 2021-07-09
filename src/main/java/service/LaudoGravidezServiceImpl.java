package service;

import dao.ClienteDao;
import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameEnum;
import dominio.ExameGravidez;
import dominio.SexoEnum;
import exceptions.WrongSexException;

import jakarta.inject.Inject;

import java.io.IOException;
import java.util.Random;

@TipoExame(value = ExameEnum.GRAVIDEZ)
public class LaudoGravidezServiceImpl implements LaudoService {

    @Inject
    private ClienteDao clienteDao;

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Exame realizarExame(String cpf) throws IOException {
       ExameGravidez exame = new ExameGravidez();
       exame.setCliente(clienteDao.findByCpf(cpf));
           if (exame.getCliente().getSexo() != SexoEnum.FEMININO) {
               throw new WrongSexException ("O cliente não pode realizar o exame indicado.");
            }
            var random = new Random();
            exame.setResultado(random.nextBoolean());
            laudoDao.criar(exame);
            return exame;
    }

}
