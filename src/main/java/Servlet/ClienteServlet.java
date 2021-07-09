package Servlet;

import com.google.gson.Gson;
import dominio.Cliente;
import dominio.CustomMessage;
import exceptions.NoClientException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ClienteService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "clienteServlet" , urlPatterns = "/cliente")
public class ClienteServlet extends HttpServlet {

    public static final String CLIENTES_SESSION = "clientes";

    @Inject
    private ClienteService clienteService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();
    }
    @Override
//    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder conteudo = getBody(request);
        Cliente clienteRequest = gson.fromJson(conteudo.toString(), Cliente.class);
        PrintWriter print = prepareResponse(response);
        String resposta = "";
        if (null == clienteRequest.getNome() || null == clienteRequest.getCpf()) {
            CustomMessage message = new CustomMessage(HttpServletResponse.SC_BAD_REQUEST, "Invalid Parameters");
            response.setStatus(message.getStatus());
            resposta = gson.toJson(message);
        } else {
            try {
                HttpSession sessao = request.getSession(true);
                clienteService.inserir(clienteRequest);
                List<Cliente> clientes = clienteService.listAll();
                clientes.add(clienteRequest);
                sessao.setAttribute(CLIENTES_SESSION, clientes);
                resposta = gson.toJson(clientes);
            } catch (NoClientException noClientException) {
                response.setStatus(400);
                resposta = gson.toJson(new CustomMessage(400, noClientException.getMessage()));
            }
        }
        print.write(resposta);
        print.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String cpfPesquisa = request.getParameter("cpf");
        HttpSession sessao = request.getSession();
        List<Cliente> clientes = new ArrayList<>();
        if (Objects.nonNull((sessao.getAttribute(CLIENTES_SESSION)))) {
            clientes.addAll((List<Cliente>) sessao.getAttribute(CLIENTES_SESSION));
        }
        PrintWriter printWriter = prepareResponse(response);
        if (null != cpfPesquisa && Objects.nonNull(clientes)) {
            Optional<Cliente> optionalCliente = clientes.stream().filter(cliente -> cliente.getCpf().equals(cpfPesquisa)).findFirst();
            if (optionalCliente.isPresent()) {
                printWriter.write(gson.toJson(optionalCliente.get()));
            } else {
                CustomMessage message = new CustomMessage(404, "Conteúdo não encontrado");
                response.setStatus(404);
                printWriter.write(gson.toJson(message));
            }
        } else {
            printWriter.write(gson.toJson(clientes));
        }
        printWriter.close();
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
