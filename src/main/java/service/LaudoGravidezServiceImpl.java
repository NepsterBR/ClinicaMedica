package service;

import dao.LaudoDao;
import dominio.Exame;
import dominio.ExameEnum;
import dominio.ExameGravidez;
import dominio.Laudo;
import dominio.SexoEnum;
import exceptions.NoClientException;
import exceptions.WrongSexException;

import jakarta.inject.Inject;

import java.util.Random;

@TipoExame(value = ExameEnum.GRAVIDEZ)
public class LaudoGravidezServiceImpl implements LaudoService {

    @Inject
    private LaudoDao laudoDao;

    @Override
    public Exame realizarExame() {
        ExameGravidez exame = new ExameGravidez();
        if (null == exame.getCliente()) {
            throw new NoClientException("Cliente não cadastrado");
        } else {
            if (exame.getCliente().getSexo() != SexoEnum.FEMININO) {
                throw new WrongSexException("O cliente não pode realizar o exame indicado.");
            }
            var random = new Random();
            exame.setResultado(random.nextBoolean());
            System.out.println("Exame de gravidez realizado com sucesso");
            return exame;
        }
    }

    @Override
    public Laudo emitirLaudo() {
        ExameGravidez exame = new ExameGravidez();
        return laudoDao.getLaudo(exame);
    }
}
