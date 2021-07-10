package Servlet;

import com.google.gson.Gson;
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

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();
    }
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder conteudo = getBody(request);
        String cpf = request.getParameter("cpf");
        String exame = request.getParameter("exame");
        Exame exameRequest;
        ExameEnum exameEnum = null;
        switch (exame) {
            case "COVID" :
                exameRequest = gson.fromJson(conteudo.toString(), ExameCovid.class);
                exameEnum = ExameEnum.COVID;
                printResponse(request, response, cpf, exame, exameRequest, exameEnum);
                break;
            case "GRAVIDEZ" :
                exameEnum = ExameEnum.GRAVIDEZ;
                exameRequest = gson.fromJson(conteudo.toString(), ExameGravidez.class);
                printResponse(request, response, cpf, exame, exameRequest, exameEnum);
                break;
            case "CORTISOL" :
                exameEnum = ExameEnum.CORTISOL;
                exameRequest = gson.fromJson(conteudo.toString(), ExameCortisol.class);
                printResponse(request, response, cpf, exame, exameRequest, exameEnum);
                break;
           case "GLICEMIA" :
                exameEnum = ExameEnum.GLICEMIA;
                exameRequest = gson.fromJson(conteudo.toString(), ExameGlicemia.class);
                printResponse(request, response, cpf, exame, exameRequest, exameEnum);
                break;
            default:
                System.exit(0);

        }


    }

    private void printResponse(HttpServletRequest request, HttpServletResponse response, String cpf, String exame, Exame exameRequest, ExameEnum exameEnum) throws IOException {
        PrintWriter print = prepareResponse(response);
        String resposta = "";
        if (null == exame || null == cpf) {
            //CustomMessage message = new CustomMessage(HttpServletResponse.SC_BAD_REQUEST, "Invalid Parameters");
//            response.setStatus(message.getStatus());
//            resposta = gson.toJson(message);

            response.setStatus(404);
            resposta = gson.toJson("testeExame ");

        } else {
            try {
                HttpSession sessao = request.getSession(true);
                exameService.inserir(exameRequest, cpf, exameEnum);
                resposta = gson.toJson(exameRequest);
            } catch (NoClientException noClientException) {
                response.setStatus(400);
                //resposta = gson.toJson(new CustomMessage(400, noClientException.getMessage()));
                resposta = gson.toJson("teste exame Exame");
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


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final String cpf = request.getParameter("cpf");
//        final String exame = request.getParameter("exame");
//        List<Cliente> clientes = new ArrayList<>();
//        if (Objects.nonNull((sessao.getAttribute(CLIENTES_SESSION)))) {
//            clientes.addAll((List<Cliente>) sessao.getAttribute(CLIENTES_SESSION));
//        }
//        PrintWriter printWriter = prepareResponse(response);
//        if (null != cpfPesquisa && Objects.nonNull(clientes)) {
//            Optional<Cliente> optionalCliente = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfPesquisa)).findFirst();
//            if (optionalCliente.isPresent()) {
//                printWriter.write(gson.toJson(optionalCliente.get()));
//            } else {
//                CustomMessage message = new CustomMessage(404, "Conteúdo não encontrado");
//                response.setStatus(404);
//                printWriter.write(gson.toJson(message));
//            }
//        } else {
//            printWriter.write(gson.toJson(clientes));
//        }
//        printWriter.close();
//    }
//




}
