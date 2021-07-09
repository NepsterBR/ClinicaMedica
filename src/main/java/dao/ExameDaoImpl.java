package dao;

import dominio.Exame;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ExameDaoImpl implements ExameDao{

    @Override
    public Exame criar(Exame exame, String cpf) {
        final var caminhoDoArquivo = "C:\\Users\\55329\\IdeaProjects\\ClinicaMedica\\src\\main\\java\\arquivos\\exames\\"+ cpf +"EXAME.txt";
        try (var arquivo = new FileWriter(caminhoDoArquivo, false)) {
            var escreverArquivo = new PrintWriter(arquivo);
            escreverArquivo.printf("%s%n%s", exame.getNomeExame(),cpf);
        } catch (Exception ex) {
            System.out.println("Não foi possivel criar o arquivo");
        }
        return exame;
    }
}
