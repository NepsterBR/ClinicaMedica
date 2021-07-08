package view;

import dao.ClienteDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.Scanner;

public class ClienteViewImpl implements ClienteView {

    @Inject
    private ClienteDao clienteDao;

    public void criarCliente(Scanner input) throws IOException {
        var cliente = new Cliente();
//        System.out.print("Informe seu nome de usúario: ");
//        cliente.setNome(input.next());
//        System.out.printf("Informe a seu CPF %s: ", cliente.getNome());
//        cliente.setCpf(input.next());
        clienteDao.InserirNoArquivo(cliente);
    }
}
