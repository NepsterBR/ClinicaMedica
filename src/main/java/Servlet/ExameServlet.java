package Servlet;

import com.google.gson.Gson;
import dominio.Cliente;
import dominio.CustomMessage;
import dominio.Exame;
import dominio.ExameCovid;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ClienteService;
import service.ExameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        ExameCovid exameRequest = gson.fromJson(conteudo.toString(), ExameCovid.class);
        PrintWriter print = prepareResponse(response);
        String resposta = "";
        if (null == exameRequest.getNomeExame() || null == cpf) {
            CustomMessage message = new CustomMessage(HttpServletResponse.SC_BAD_REQUEST, "Invalid Parameters");
            response.setStatus(message.getStatus());
            resposta = gson.toJson(message);
        } else {
            try {
                HttpSession sessao = request.getSession(true);
                exameService.inserir(exameRequest, cpf);
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
}
