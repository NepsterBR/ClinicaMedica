package view;

import dao.ClienteDao;
import dominio.Cliente;
import jakarta.inject.Inject;

import java.util.Scanner;

public class ClienteViewImpl implements ClienteView {

    @Inject
    private ClienteDao clienteDao;

    public void criarCliente(Cliente cliente){
        var input = new Scanner(System.in);
        System.out.print("Informe seu nome de usúario: ");
        cliente.setNome(input.next());
        System.out.printf("Informe a seu CPF %s: ", cliente.getNome());
        cliente.setCpf(input.next());
        clienteDao.criar(cliente);

    }
}
