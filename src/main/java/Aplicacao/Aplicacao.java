package Aplicacao;


import jakarta.inject.Inject;
import view.ClienteView;

import java.util.Scanner;

public class Aplicacao {

    @Inject
    private ClienteView clienteView;

    public void createUsuario(Scanner input) {
        clienteView.criarCliente(input);
    }
}
