package dao;

import dominio.Cliente;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClienteDaoImpl implements ClienteDao{

    private Path path;

    @PostConstruct
    public void init() {
        try {
            String caminho = "clientes.csv";
            path = Paths.get(caminho);
            if (path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public Cliente criar(Cliente cliente) {

        final var caminhoDoArquivo = "src\\main\\java\\app\\br\\com\\letscode\\aplicacao\\arquivos\\ " + cliente.getCpf() + ".txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, false)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s%n%s", cliente.getCpf(), cliente.getNome(), cliente.getSexo());
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return cliente;
    }

    @Override
    public List<Cliente> getAll() throws IOException {
        List<Cliente> clientes;
        try (BufferedReader br = Files.newBufferedReader(path)) {
            clientes = br.lines().filter(s -> s.isEmpty()).map(this::convert).collect(Collectors.toList());
            // List<String> identificadores = clientes.stream().map(Cliente::getIdentificador).collect(Collectors.toList());
        }
        return clientes;
    }

    private Cliente convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        Cliente cliente = new Cliente();
        cliente.setCpf(token.nextToken());
        cliente.setNome(token.nextToken());
        //TODO dar um jeito de tokenizar o sexo da pessoa
//        cliente.setSexo(token.nextToken());
        return cliente;
    }
}
