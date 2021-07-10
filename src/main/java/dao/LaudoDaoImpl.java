package dao;

import dominio.Cliente;
import dominio.Exame;
import dominio.Laudo;
import dominio.SexoEnum;
import jakarta.annotation.PostConstruct;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
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

    @Override
    public Laudo lerLaudo(Exame exame) throws IOException {

        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\laudos\\" + exame.getCliente().getCpf() + "_" + exame.getNomeExame() + ".txt";
        Path caminhoArquivo = Paths.get(caminhoDoArquivo);
        ArrayList<Exame> exames = new ArrayList<Exame>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                Exame exame1 = new Exame();
                StringTokenizer token = new StringTokenizer(linha, ";");
                exame1.setNomeExame(token.nextToken());
                exame1.setIdExame(token.nextToken());
                exame1.setParametros(token.nextToken());
                Cliente cliente = new Cliente();
                cliente.setNome(token.nextToken());
                cliente.setCpf(token.nextToken());
                String sexoTemp = token.nextToken();
                if (sexoTemp.equalsIgnoreCase("feminino")) {
                    cliente.setSexo(SexoEnum.FEMININO);
                } else {
                    cliente.setSexo(SexoEnum.MASCULINO);
                }
                exame1.setCliente(cliente);
                exame1.setDataRealizacao(LocalDate.parse(token.nextToken()));
                exame1.setResultado(token.nextToken());

                exames.add(exame1);

            }
            Laudo laudo = new Laudo();
            laudo.setExames(exames);

            return laudo;
        }


    }


}

