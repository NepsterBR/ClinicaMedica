package dao;

import dominio.Cliente;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClienteDaoImpl implements ClienteDao{

    private Path path;

    @PostConstruct
    public void init() {
        try {
            String caminho = "C:\\Users\\gabri\\IdeaProjects\\clinica-medica\\src\\main\\java\\arquivo\\clientes.csv";
            path = Paths.get(caminho);
            if (path.toFile().exists()) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public Cliente InserirNoArquivo(Cliente cliente) throws IOException {
        write(format(cliente), StandardOpenOption.APPEND);
        return cliente;
    }

    //escreve o arquivo ou adiciona um conteudo junto do mesmo
    private void write(String clienteStr, StandardOpenOption option) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(path, option)) {
            bf.write((clienteStr));
        }
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

    @Override
    public Optional<Cliente> findByCpf(String cpf) throws IOException {
        List<Cliente> clientes = getAll();
        return clientes.stream().filter(cliente -> cliente.getCpf().equals(cpf)).findFirst();

    }

    @Override
    public Cliente alterarArquivo(Cliente cliente, String identificador) throws IOException {
        //procurar o cliente pelo identificador
        List<Cliente> clientes = getAll();
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter(clienteSearch -> clienteSearch.getCpf().equals(identificador)).findFirst();
        if (optionalCliente.isPresent()) {
            //alterar este cliente com os dados do objeto
            //pegar todos os registros
            optionalCliente.get().setNome(cliente.getNome());

            StringBuilder builder = new StringBuilder();
            for (Cliente clienteBuilder:clientes){
                builder.append(format(clienteBuilder));
            }

            //reescrever all file
            write(builder.toString(), StandardOpenOption.CREATE_NEW);

        }

        return cliente;
    }

    private String format(Cliente cliente) {
        return String.format("%s;%s;%s \r\n", cliente.getCpf(), cliente.getNome(), cliente.getSexo());
    }

    private Cliente convert(String linha) {
        StringTokenizer token = new StringTokenizer(linha, ";");
        Cliente cliente = new Cliente();
        cliente.setCpf(token.nextToken());
        cliente.setNome(token.nextToken());
        //todo dar um jeito de setar o sexo
        //cliente.setSexo(token.nextToken());
        return cliente;
    }
}
