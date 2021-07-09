package dao;

import dominio.Cliente;
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

public class ClienteDaoImpl implements ClienteDao {

    private Path path;

    @Override
    public Cliente criar(Cliente cliente) {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\usuarios\\"+ cliente.getCpf()+".txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, false)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s%n%s", cliente.getNome(), cliente.getCpf(), cliente.getSexo());
            init(cliente);
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return cliente;
    }

    public void init(Cliente cliente) {
        try {
            String caminho = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\usuarios\\"+ cliente.getCpf()+".txt";
            path = Paths.get(caminho);
            if (path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public Cliente inserirNoArquivo(Cliente cliente) throws IOException {
        write(format(cliente), StandardOpenOption.APPEND);
        return cliente;
    }

    //escreve o arquivo ou adiciona um conteudo junto do mesmo
    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.write((clienteStr));
        }
    }

    private String format(Cliente cliente) {
        return String.format("%s%n%s%n%s", cliente.getCpf(), cliente.getNome(), cliente.getSexo());
    }

    private Cliente convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, "\n");
        Cliente cliente = new Cliente();
        cliente.setCpf(token.nextToken());
        cliente.setNome(token.nextToken());
        String sexoTemp = token.nextToken();
        //TODO arrumar o case do input
        if(sexoTemp.equalsIgnoreCase("feminino")){
            cliente.setSexo(SexoEnum.FEMININO);
        } else {
            cliente.setSexo(SexoEnum.MASCULINO);
        }
        return cliente;
    }

    @Override
    public Cliente findByCpf(String cpf) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\usuarios\\" + cliente.getCpf()+".txt";
        try (var lerArquivo = new BufferedReader(new FileReader(caminhoDoArquivo))){
            cliente.setNome(lerArquivo.readLine());
            cliente.setCpf(lerArquivo.readLine());
            String sexoTemp = lerArquivo.readLine();
            if(sexoTemp.equalsIgnoreCase("feminino")){
                cliente.setSexo(SexoEnum.FEMININO);
            } else {
                cliente.setSexo(SexoEnum.MASCULINO);
            }
        } catch (Exception ex) {
            System.out.println("Cliente não encontrado findByCpf");
        }

        return cliente;

    }


}
