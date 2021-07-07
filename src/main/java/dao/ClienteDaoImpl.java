package dao;

import dominio.Cliente;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ClienteDaoImpl implements ClienteDao{

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
}
