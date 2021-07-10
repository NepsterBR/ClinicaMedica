package dao;

import dominio.Cliente;
import dominio.Exame;
import dominio.SexoEnum;
import jakarta.annotation.PostConstruct;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LaudoDaoImpl implements LaudoDao {

    private Path path;

    @Override
    public Exame criar(Exame exame) {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\laudos\\" + exame.getCliente().getCpf() + "_" + exame.getNomeExame() + ".txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, true)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s;%s;%s;%s;%s;%s;%s;%s\r\n",
                    exame.getNomeExame(),
                    exame.getIdExame(),
                    exame.getParametros(),
                    exame.getCliente().getNome(),
                    exame.getCliente().getCpf(),
                    exame.getCliente().getSexo(),
                    exame.getDataRealizacao(),
                    exame.getResultado()
                    );
           } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return exame;

    }



    }

