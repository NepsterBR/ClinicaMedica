package Servlet;

import com.google.gson.Gson;
import dao.LaudoDao;
import dominio.*;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ExameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "exameServlet" , urlPatterns = "/exame")
public class ExameServlet extends HttpServlet {

    public static final String CLIENTES_SESSION = "exames";

    @Inject
    private ExameService exameService;

    private Gson gson;

    @Inject
    private LaudoDao laudoDao;


    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();
    }
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder conteudo = getBody(request);

        SolicitaExame solicitacao = gson.fromJson(conteudo.toString(), SolicitaExame.class);

        Exame exameRequest;
        ExameEnum exameEnum = null;
        switch (solicitacao.getNomeExame()) {
            case "COVID" :
                exameRequest = gson.fromJson(conteudo.toString(), ExameCovid.class);
                exameEnum = ExameEnum.COVID;
                printResponse(request, response, solicitacao.getCpf(), solicitacao.getNomeExame(), exameRequest, exameEnum);
                break;
            case "GRAVIDEZ" :
                exameEnum = ExameEnum.GRAVIDEZ;
                exameRequest = gson.fromJson(conteudo.toString(), ExameGravidez.class);
                printResponse(request, response, solicitacao.getCpf(), solicitacao.getNomeExame(), exameRequest, exameEnum);
                break;
            case "CORTISOL" :
                exameEnum = ExameEnum.CORTISOL;
                exameRequest = gson.fromJson(conteudo.toString(), ExameCortisol.class);
                printResponse(request, response, solicitacao.getCpf(), solicitacao.getNomeExame(), exameRequest, exameEnum);
                break;
           case "GLICEMIA" :
                exameEnum = ExameEnum.GLICEMIA;
                exameRequest = gson.fromJson(conteudo.toString(), ExameGlicemia.class);
                printResponse(request, response, solicitacao.getCpf(), solicitacao.getNomeExame(), exameRequest, exameEnum);
                break;
            default:
                System.exit(0);

        }


    }

    private void printResponse(HttpServletRequest request, HttpServletResponse response, String cpf, String exame, Exame exameRequest, ExameEnum exameEnum) throws IOException {
        PrintWriter print = prepareResponse(response);
        String resposta = "";
        if (null == exame || null == cpf) {
            CustomMessage message = new CustomMessage(HttpServletResponse.SC_BAD_REQUEST, "Invalid Parameters");
            response.setStatus(message.getStatus());
            resposta = gson.toJson(message);

        } else {
            try {
                HttpSession sessao = request.getSession(true);
                exameService.inserir(exameRequest, cpf, exameEnum);
                resposta = gson.toJson(exameRequest);
            } catch (NoClientException noClientException) {
                response.setStatus(400);
                resposta = gson.toJson(new CustomMessage(400, noClientException.getMessage()));

            }
        }
        print.write(resposta);
        print.close();
    }

    private PrintWriter prepareResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return response.getWriter();
    }

    private StringBuilder getBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String line = "";
        StringBuilder conteudo = new StringBuilder();

        while (null != (line = br.readLine())) {
            conteudo.append(line);
        }
        return conteudo;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String cpf = request.getParameter("cpf");
        final String exame = request.getParameter("exame");

        PrintWriter printWriter = prepareResponse(response);
        if (null != cpf && null != exame) {
            Exame pesquisaExame = new Exame();
            Cliente cliente = new Cliente();
            cliente.setCpf(cpf);
            pesquisaExame.setCliente(cliente);

            switch (exame) {
                case "COVID" :
                    pesquisaExame.setNomeExame("Teste de Covid");
                    break;
                case "GLICEMIA" :
                    pesquisaExame.setNomeExame("Exame de Glicemia");
                    break;
                case "CORTISOL" :
                    pesquisaExame.setNomeExame("Exame de Cortisol");
                    break;
                case "GRAVIDEZ" :
                    pesquisaExame.setNomeExame("Teste de Gravidez");
                    break;
                default:
                    System.exit(0);
            }

               printWriter.write(gson.toJson(laudoDao.lerLaudo(pesquisaExame)));

            } else {
                CustomMessage message = new CustomMessage(404, "Conteúdo não encontrado");
                response.setStatus(404);
                printWriter.write(gson.toJson(message));
            }

        printWriter.close();
    }





}
